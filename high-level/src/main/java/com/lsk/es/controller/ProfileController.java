package com.lsk.es.controller;

import com.lsk.es.document.ProfileDocument;
import com.lsk.es.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO:
 *
 * @author red
 * @class_name ProfileController
 * @date 2020-06-27
 */
@RestController
public class ProfileController {

    // ??? 构造函数的注解和Autowired的有什么区别
    @Autowired
    private ProfileService service;

    @GetMapping("/test")
    public String test() {
        return "Success";
    }

    // 创建文档
    @PostMapping("/es/create")
    public ResponseEntity createProfile(@RequestBody ProfileDocument document) throws Exception {
        return new ResponseEntity(service.createProfileDocument(document), HttpStatus.CREATED);
    }

    // 更新文档
    @PutMapping("/es/update")
    public ResponseEntity updateProfile(@RequestBody ProfileDocument document) throws Exception {
        return new ResponseEntity(service.updateProfile(document), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ProfileDocument findById(@PathVariable String id) throws Exception {
        return service.findById(id);
    }

    // 查询索引的所有文档
    @GetMapping
    public List<ProfileDocument> findAll() throws Exception {
        return service.findAll();
    }

    @GetMapping(value = "/search")
    public List<ProfileDocument> search(@RequestParam(value = "technology") String technology) throws Exception {
        return service.searchByTechnology(technology);
    }

    // /api/v1/profiles/name-search?name=yiming
    // 根据name查询文档
    @GetMapping(value = "/api/v1/profiles/name-search")
    public List<ProfileDocument> searchByName(@RequestParam(value = "name") String name) throws Exception {
        return service.findProfileByName(name);
    }

    @DeleteMapping("/{id}")
    public String deleteProfileDocument(@PathVariable String id) throws Exception {
        return service.deleteProfileDocument(id);
    }
}
