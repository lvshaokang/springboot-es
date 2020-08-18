package com.lsk.es.example01.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsk.es.example01.model.WordDoc;
import com.lsk.es.example01.model.WordDocument;
import com.lsk.es.example01.util.WordUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

import static com.lsk.es.example01.constant.Constants.WORD_INDEX;

/**
 * TODO
 *
 * @author lsk
 * @class_name DocumentService
 * @date 2020-06-28
 */
@Service
public class DocumentService {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ObjectMapper objectMapper;

    public String createWordDocument(WordDocument wordDocument) {
        String path = wordDocument.getPath();
        IndexResponse indexResponse = null;
        try {
            WordDoc wordDoc = WordUtils.parseWord(path);
            IndexRequest indexRequest = new IndexRequest(WORD_INDEX)
                    .id(wordDocument.getId())
                    .source(convertWordDocumentToMap(wordDoc));
            indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexResponse == null ? "" : indexResponse.getResult().name();
    }

    private Map<String, Object> convertWordDocumentToMap(WordDoc profileDocument) {
        return objectMapper.convertValue(profileDocument, Map.class);
    }

}
