package com.lsk.es.repository;

import com.lsk.es.model.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO:
 *
 * @author red
 * @class_name EmployeeRepository
 * @date 2020-06-22
 */
@Repository
public interface EmployeeRepository extends ElasticsearchRepository<Employee,Long> {

    List<Employee> findByOrganizationName(String name);

    List<Employee> findByName(String name);

}
