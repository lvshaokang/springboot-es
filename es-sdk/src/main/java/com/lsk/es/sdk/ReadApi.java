package com.lsk.es.sdk;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.collect.Tuple;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.*;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.SearchModule;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * TODO
 *
 * @author Red
 * @class_name ReadApi
 * @date 2020/08/19
 */
public class ReadApi {

    private static final Logger LOG = LoggerFactory.getLogger(ReadApi.class);

    private EsClient client;

    private String indexName;
    private String indexType;

    private final SearchModule searchModule = new SearchModule(Settings.EMPTY, false, Collections.emptyList());

    /**
     * 超时时间
     */
    private long timeoutMills;

    public void close() {
        try {
            if (client != null) {
                client.close();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public EsReaderResult search(Integer from, Integer size, QueryBuilder queryBuilder, String[] includeSource, EsSort esSort,
                                 Map<String,String> scriptFields) throws IOException {
        return search(from, size, queryBuilder, includeSource, esSort, scriptFields, null);
    }

    /**
     * 获取满足查询条件的数据（search模式）
     * // TODO 可以使用建造者模式改进
     *
     * @author Red
     * @date 2020/08/19
     */
    public EsReaderResult search(Integer from, Integer size, QueryBuilder queryBuilder, String[] includeSource, EsSort esSort,
                                 Map<String,String> scriptFields, HighlightBuilder highlightBuilder) throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(queryBuilder);

        if (from != null) {
            sourceBuilder.from(from);
        }

        if (includeSource != null) {
            sourceBuilder.fetchSource(includeSource, new String[0]);
        }

        if (size != null) {
            sourceBuilder.size(size);
        }

        if (esSort != null) {
            sortFields(esSort, sourceBuilder);
        }

        // 支持script field
        if (scriptFields != null && !scriptFields.isEmpty()) {
            for (String field : scriptFields.keySet()) {
                sourceBuilder.scriptField(field, new Script(scriptFields.get(field)));
            }
        }

        // 支持高亮
        if (highlightBuilder != null) {
            sourceBuilder.highlighter(highlightBuilder);
        }
        return search(sourceBuilder);
    }

    private EsReaderResult search(SearchSourceBuilder sourceBuilder) throws IOException {
        if (timeoutMills != 0) {
            sourceBuilder.timeout(new TimeValue(timeoutMills));
        }

        SearchRequest request = new SearchRequest(indexName);
        request.types(indexType).source(sourceBuilder);

        LOG.debug(request.source().toString());

        SearchResponse response = client.getClient().search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.getHits();

        // TODO
        EsReaderResult result = new EsReaderResult();

        if (response.getFailedShards() != 0) {
            LOG.error("fail shards {}", response.getFailedShards());
            return result;
        }

        if (searchHits.length > 0) {
            List<EsDocument> docs = new ArrayList<>(searchHits.length);
            for (SearchHit searchHit : searchHits) {
                if (searchHit.getSourceAsMap() != null) {
                    EsDocument esDocument = new EsDocument(searchHit.getId(), searchHit.getSourceAsMap());

                    // 有source的情况可可能有field
                    if (sourceBuilder.scriptFields() != null && sourceBuilder.scriptFields().size() > 0) {
                        fetchScriptField(searchHit, esDocument);
                    }
                    docs.add(esDocument);
                } else {
                    // souce == null
                    Map<String, DocumentField> fields = searchHit.getFields();
                    Map<String, Object> docMap = new HashMap<>();
                    for (String field : fields.keySet()) {
                        DocumentField documentField = fields.get(field);
                        docMap.put(field, documentField.getValue());
                    }
                    docs.add(new EsDocument(searchHit.getId(), docMap));
                }
            }
            result.setEsDocs(docs);
        }
        return result;
    }

    public EsReaderResult search(String query) {
        SearchSourceBuilder builder;
        try(XContentParser parser = XContentFactory.xContent(XContentType.JSON)
                .createParser(new NamedXContentRegistry(searchModule.getNamedXContents()), new DeprecationHandler() {
                    @Override
                    public void usedDeprecatedName(String usedName, String modernName) {

                    }

                    @Override
                    public void usedDeprecatedField(String usedName, String replacedWith) {

                    }
                }query)) {
            builder = SearchSourceBuilder.fromXContent(new QueryParseContext(parser));
        }
    }

    private void fetchScriptField(SearchHit searchHit, EsDocument esDocument) {
        Map<String, DocumentField> fields = searchHit.getFields();
        if (fields != null && !fields.isEmpty()) {
            for (String field : fields.keySet()) {
                DocumentField documentField = fields.get(field);
                esDocument.put(field, documentField.getValue());
            }
        }
     }

    private void sortFields(EsSort esSort, SearchSourceBuilder sourceBuilder) {
        for (Tuple<String, SortOrder> pairs : esSort.getSortOrderFields()) {
            sourceBuilder.sort(pairs.v1(), pairs.v2());
        }
    }

}
