package org.trendInvesting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.trendInvesting.pojo.IndexData;
import org.trendInvesting.service.IndexDataService;

import java.util.List;

@RestController
public class IndexDataController {

    @Autowired
    private IndexDataService indexDataService;

    @GetMapping("/data/{code}")
    public List<IndexData> getIndexData(@PathVariable("code") String code){
        return  indexDataService.get(code);
    }
}
