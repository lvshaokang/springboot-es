package com.lsk.es.sdk;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;

import java.io.IOException;

/**
 * TODO
 *
 * @author Red
 * @class_name IndexApi
 * @date 2020-08-18
 */
@Slf4j
public class IndexApi {

    private RestHighLevelClient client;

    public void f1() {

    }

    public void createIndex() {
        CreateIndexRequest request = new CreateIndexRequest("twitter");
        request.settings(null);
        request.mapping();
        request.alias();
    }

    public void existsIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("twitter");
        request.local(false);
        request.humanReadable(false);
        request.includeDefaults(false);
        request.indicesOptions(IndicesOptions.STRICT_SINGLE_INDEX_NO_EXPAND_FORBID_CLOSED);

        // sync
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);

        // async
        /*boolean exists = client.indices().existsAsync(request, RequestOptions.DEFAULT, new ActionListener<Boolean>() {
            @Override
            public void onResponse(Boolean aBoolean) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });*/
    }

    public void deleteIndex(String index) {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        request.timeout("");
        request.masterNodeTimeout("");
        request.indicesOptions(IndicesOptions.LENIENT_EXPAND_OPEN)

    }




}
