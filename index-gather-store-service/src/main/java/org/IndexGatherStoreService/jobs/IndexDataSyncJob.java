package org.IndexGatherStoreService.jobs;


import org.IndexGatherStoreService.pojo.Index;
import org.IndexGatherStoreService.server.IndexDataService;
import org.IndexGatherStoreService.server.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndexDataSyncJob {

    private static final Logger log = LoggerFactory.getLogger(IndexDataSyncJob.class);

    @Autowired
    private IndexService indexService;

    @Autowired
    private IndexDataService indexDataService;


    @Scheduled(cron = "0 0/30 * * * *")
    public void fetchIndexData(){
        log.info("start fetchIndexData Scheduled");
        List<Index> indexes = indexService.fresh();
        for (Index index : indexes){
            indexDataService.fresh(index.getCode());
        }
        log.info("fetchIndexData Scheduled end");
    }
}
