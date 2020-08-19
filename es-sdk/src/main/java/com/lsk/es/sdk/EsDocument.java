package com.lsk.es.sdk;

import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author Red
 * @class_name EsDocument
 * @date 2020/08/19
 */
public class EsDocument {
    private static final Logger LOG = LoggerFactory.getLogger(EsDocument.class);

    private Map<String, Object> docMap = new HashMap<>();

    public EsDocument() {}

    public EsDocument(String id, Map<String, Object> docMap) {
        this.docMap = docMap;
        this.docMap.put("id", id);
    }

    public boolean containsKey(String key) {
        return docMap.containsKey(key);
    }

    public Object get(String key) {
        return docMap.get(key);
    }

    public int size() {
        return docMap.size();
    }

    public void put(String key, Object value) {
        docMap.put(key, value);
    }

    public String toJson() {
        try {
            XContentBuilder xContentBuilder = XContentFactory.jsonBuilder();
            xContentBuilder.startObject();
            for (Map.Entry<String, Object> entry : docMap.entrySet()) {
                xContentBuilder.field(entry.getKey(), entry.getValue());
            }
            xContentBuilder.endObject();
            return Strings.toString(xContentBuilder);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }

        return null;
    }

    public Map<String, Object> getDocMap() {
        return docMap;
    }

    public void setDocMap(Map<String, Object> docMap) {
        this.docMap = docMap;
    }
}
