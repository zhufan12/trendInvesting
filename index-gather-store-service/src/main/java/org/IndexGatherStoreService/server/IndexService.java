package org.IndexGatherStoreService.server;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.IndexGatherStoreService.pojo.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.IndexGatherStoreService.utils.SpringContextUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "indexes")
public class IndexService {

    private List<Index> indexes;
    @Autowired
    private RestTemplate restTemplate;



    public List<Index> fetch_indexes_from_third_part(){
        List<Map> temp= restTemplate.getForObject("http://127.0.0.1:8090/indexes/codes.json",List.class);
        return map2Index(temp);
    }

    @HystrixCommand(fallbackMethod = "third_part_not_connected")
    public List<Index> fresh() {
        indexes = fetch_indexes_from_third_part();
        IndexService indexService = SpringContextUtil.getBean(IndexService.class);
        indexService.remove();
        return indexService.store();
    }

    public List<Index> third_part_not_connected(){
        Index index= new Index();
        index.setCode("000000");
        index.setName("無效股票代碼");
        return Arrays.asList(index);
    }

    @CacheEvict(key = "'all_code'")
    public void remove(){

    }


    @Cacheable(key = "'all_code'")
    public List<Index> store(){
        return indexes;
    }


    @Cacheable(key = "'all_code'")
    public List<Index> get(){
        return Arrays.asList();
    }

    private List<Index> map2Index(List<Map> temp) {
        List<Index> indexes = new ArrayList<>();
        for (Map map : temp) {
            String code = map.get("code").toString();
            String name = map.get("name").toString();
            Index index= new Index();
            index.setCode(code);
            index.setName(name);
            indexes.add(index);
        }

        return indexes;
    }

}
