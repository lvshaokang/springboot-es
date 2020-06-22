package com.lsk.es.model;

import com.lsk.es.common.Constants;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * TODO:
 *
 * @author red
 * @class_name Employee
 * @date 2020-06-22
 */
@Data
@Document(indexName = Constants.EMPLOYEE_INDEX,type = Constants.EMPLOYEE_INDEX_TYPE)
public class Employee {

    @Id
    private Long id;

    @Field(type = FieldType.Object)
    private Organization organization;

    @Field(type = FieldType.Object)
    private Department department;

    private String name;

    private int age;

    private String position;
}
