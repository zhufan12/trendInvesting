package org.trendInvesting.server;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.trendInvesting.pojo.IndexData;
import org.trendInvesting.utils.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@CacheConfig(cacheNames = "index_datas")
public class IndexDataService {


    private Map<String, List<IndexData>> indexDatas= new HashMap<>();

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "third_part_not_connected")
    public List<IndexData> fresh(String code) {
        List<IndexData> indexeDatas =fetch_indexes_from_third_part(code);
        indexDatas.put(code, indexeDatas);
        IndexDataService indexDataService = SpringContextUtil.getBean(IndexDataService.class);
        indexDataService.remove(code);
        return indexDataService.store(code);
    }

    @CacheEvict(key="'indexData-code-'+ #p0")
    public void remove(String code){
    }

    @CachePut(key="'indexData-code-'+ #p0")
    public List<IndexData> store(String code){
        return indexDatas.get(code);
    }

    @Cacheable(key="'indexData-code-'+ #p0")
    public List<IndexData> get(String code){
        return Arrays.asList();
    }


    public List<IndexData> fetch_indexes_from_third_part(String code){
        List<Map> temp= restTemplate.getForObject("http://127.0.0.1:8090/indexes/"+code+".json",List.class);
        return map2IndexData(temp);
    }

    private List<IndexData> map2IndexData(List<Map> temp) {
        List<IndexData> indexDatas = new ArrayList<>();
        for (Map map : temp) {
            String date = map.get("date").toString();
            float closePoint = Float.valueOf(map.get("closePoint").toString());
            IndexData indexData = new IndexData();

            indexData.setDate(date);
            indexData.setClosePoint(closePoint);
            indexDatas.add(indexData);
        }

        return indexDatas;
    }

    public List<IndexData> third_part_not_connected(String code){
        IndexData index = new IndexData();
        index.setClosePoint(0);
        index.setDate("n/a");
        return Arrays.asList(index);
    }



}
