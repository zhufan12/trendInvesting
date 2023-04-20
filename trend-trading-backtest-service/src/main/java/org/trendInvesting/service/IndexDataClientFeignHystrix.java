package org.trendInvesting.service;

import org.springframework.stereotype.Component;
import org.trendInvesting.pojo.IndexData;

import java.util.Arrays;
import java.util.List;

@Component
public class IndexDataClientFeignHystrix implements IndexDateService{
    @Override
    public List<IndexData> getIndexData(String code) {
        IndexData indexData = new IndexData();
        indexData.setClosePoint(0);
        indexData.setDate("0000-00-00");
        return Arrays.asList(indexData);
    }
}
