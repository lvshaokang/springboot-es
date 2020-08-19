package com.lsk.es.sdk;

import org.elasticsearch.search.SearchHit;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author Red
 * @class_name EsReaderResult
 * @date 2020/08/19
 */
public class EsReaderResult {

    private boolean isEnd;
    private String scrollId;
    private SearchHit[] searchHits;
    private long totalHit;
    private List<EsDocument> esDocs = new ArrayList<>();

    public List<EsDocument> getEsDocs() {
        return esDocs;
    }

    public void setEsDocs(List<EsDocument> esDocs) {
        this.esDocs = esDocs;
    }
}
