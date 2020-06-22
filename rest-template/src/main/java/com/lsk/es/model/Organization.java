package com.lsk.es.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO:
 *
 * @author red
 * @class_name Organization
 * @date 2020-06-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organization {
    private Long id;
    private String name;
    private String address;
}
