package org.trendInvesting.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.trendInvesting.pojo.Index;
import org.trendInvesting.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    @Autowired
    private IndexService indexService;

    @GetMapping("/codes")
    @CrossOrigin
    public List<Index> getIndexCodes(){
        List<Index> indexList = indexService.get();
        System.out.println(indexList.toArray());
        return indexService.get();
    }
}
