package org.IndexGatherStoreService.controller;

import org.IndexGatherStoreService.pojo.Index;
import org.IndexGatherStoreService.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    @Autowired
    private IndexService indexService;

    @GetMapping("/codes")
    public List<Index> getIndexCodes(){
        List<Index> indexList = indexService.get();
        System.out.println(indexList.toArray());
        return indexService.get();
    }
}
