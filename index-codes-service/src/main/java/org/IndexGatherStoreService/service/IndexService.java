package org.IndexGatherStoreService.service;


import org.IndexGatherStoreService.pojo.Index;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@CacheConfig(cacheNames = "indexes")
public class IndexService {


    private List<Index> indices;

    @Cacheable(key="'all_code'")
    public List<Index> get(){
        Index index = new Index();
        index.setName("invalid index code");
        index.setCode("000000");
        return Arrays.asList(index);
    }
}
