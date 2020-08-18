package com.lsk.es.example01;

import com.lsk.es.example01.model.WordDocument;
import com.lsk.es.example01.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author lsk
 * @class_name WordController
 * @date 2020-06-28
 */
@RestController
public class WordController {

    @Autowired
    DocumentService documentService;

    @PostMapping
    public ResponseEntity createProfile(@RequestBody WordDocument document) throws Exception {
        return new ResponseEntity(documentService.createWordDocument(document), HttpStatus.CREATED);
    }
}
