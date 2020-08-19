package com.lsk.es.sdk;

import org.elasticsearch.common.collect.Tuple;
import org.elasticsearch.search.sort.SortOrder;

import java.util.LinkedList;
import java.util.List;

/**
 * ES排序
 *
 * @author Red
 * @class_name EsSort
 * @date 2020/08/19
 */
public class EsSort {

    private List<Tuple<String, SortOrder>> sortOrderFields = new LinkedList<>();

    private static SortOrder defaultSortOrder = SortOrder.ASC;

    public EsSort() {}

    public EsSort(String field, String sortOrder) {
        addSort(field, sortOrder);
    }

    public EsSort(List<Tuple<String, SortOrder>> sortOrderFields) {
        this.sortOrderFields = sortOrderFields;
    }

    public EsSort addSort(String field, String sortOrder) {
        SortOrder order;
        if (sortOrder == null) {
            order = SortOrder.ASC;
        } else {
            if ("desc".equals(sortOrder)) {
                order = SortOrder.DESC;
            } else {
                order = SortOrder.ASC;
            }
        }

        sortOrderFields.add(new Tuple<>(field, order));
        return this;
    }

    public static EsSort fromFields(SortOrder order, String... fields) {
        List<Tuple<String, SortOrder>> fieldSortOrders = new LinkedList<>();
        for (String field : fields) {
            fieldSortOrders.add(new Tuple<>(field, order == null ? defaultSortOrder : order));
        }
        return new EsSort(fieldSortOrders);
    }

    public List<Tuple<String, SortOrder>> getSortOrderFields() {
        return sortOrderFields;
    }
}
