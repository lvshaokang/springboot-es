package com.lsk.es.example01.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * TODO
 *
 * @author lsk
 * @class_name WordDoc
 * @date 2020-06-28
 */
@Data
@Accessors(chain = true)
public class WordDoc {

    private String fileName;

    private String creator;

    private String created;

    private String content;
}
