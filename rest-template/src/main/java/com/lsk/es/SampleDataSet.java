package com.lsk.es;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.lsk.es.common.Constants;
import com.lsk.es.model.Department;
import com.lsk.es.model.Employee;
import com.lsk.es.model.Organization;
import com.lsk.es.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * TODO:
 *
 * @author red
 * @class_name SampleDataSet
 * @date 2020-06-22
 */
@Slf4j
public class SampleDataSet {

    @Autowired
    EmployeeRepository repository;

    @Autowired
    ElasticsearchRestTemplate template;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 10; i++) {
            bulk(i);
        }
    }

    public void bulk(int ii) {
        try {
            // check if the index is existed
            if (!template.indexExists(Constants.EMPLOYEE_INDEX)) {
                template.createIndex(Constants.EMPLOYEE_INDEX);
            }
            ObjectMapper mapper = new ObjectMapper();
            List<IndexQuery> queries = new ArrayList<>();
            List<Employee> employees = rndEmployees();

            for (Employee employee : employees) {

                IndexQuery indexQuery = new IndexQueryBuilder()
                        .withId(employee.getId().toString())
                        .withSource(mapper.writeValueAsString(employee))
                        .build();
                //Set the index name & doc type
//                indexQuery.setIndexName(Constants.EMPLOYEE_INDEX);
//                indexQuery.setType(Constants.EMPLOYEE_INDEX_TYPE);
                queries.add(indexQuery);
            }

            IndexCoordinates coordinates = IndexCoordinates.of(Constants.EMPLOYEE_INDEX)
                    .withTypes(IndexCoordinates.TYPE);
            if (queries.size() > 0) {
                template.bulkIndex(queries, coordinates);
            }

//            Constants.EMPLOYEE_INDEX
            template.indexOps(coordinates).refresh();
            log.info("BulkIndex completed: {}", ii);
        } catch (Exception e) {
            log.error("Error bulk index", e);
        }
    }

    private List<Employee> rndEmployees() {
        List<Employee> employees = new ArrayList<>();
        int id = (int) repository.count();
        log.info("Starting from id: {}", id);
        for (int i = id; i < 100 + id; i++) {
            Random r = new Random();
            Faker faker = new Faker();
            Employee employee = new Employee();
            employee.setId((long) i);
            employee.setName(faker.name().username());
            employee.setAge(r.nextInt(60));
            employee.setPosition(faker.job().position());
            int departmentId = r.nextInt(5000);
            employee.setDepartment(new Department((long) departmentId, faker.company().name()));
            int organizationId = departmentId % 100;
            employee.setOrganization(new Organization((long) organizationId, "TestO" + organizationId, "Test Street No. " + organizationId));
            employees.add(employee);
        }
        return employees;

    }

}
