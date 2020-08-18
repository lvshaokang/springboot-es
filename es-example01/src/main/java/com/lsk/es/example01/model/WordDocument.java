package com.lsk.es.example01.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * TODO
 *
 * @author lsk
 * @class_name WordDocument
 * @date 2020-06-28
 */
@Data
@Accessors(chain = true)
public class WordDocument {

    private String path;

    private String id;
}
