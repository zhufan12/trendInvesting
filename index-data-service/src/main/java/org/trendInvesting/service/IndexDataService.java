package org.trendInvesting.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.trendInvesting.pojo.IndexData;

import java.util.Arrays;
import java.util.List;

@Service
@CacheConfig(cacheNames = "index_datas")
public class IndexDataService {


    @Cacheable(key = "'indexData-code-'+ #p0")
    public List<IndexData> get(String code){
        return Arrays.asList();
    }
}
