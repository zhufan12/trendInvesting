package org.IndexGatherStoreService.controller;

import org.IndexGatherStoreService.pojo.Index;
import org.IndexGatherStoreService.server.IndexServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    @Autowired
    private IndexServer indexServer;

    @GetMapping("/getCodes")
    public List<Index> get() throws Exception {
        return indexServer.fetch_indexes_from_third_part();
    }
}
