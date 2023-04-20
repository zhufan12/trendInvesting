package org.trendInvesting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trendInvesting.pojo.IndexData;

import java.util.Collections;
import java.util.List;

@Service
public class BackTestService {

    @Autowired
    private IndexDateService indexDateService;

    public List<IndexData> listIndexData(String code){
        List<IndexData> result = indexDateService.getIndexData(code);
        Collections.reverse(result);

        for (IndexData indexData : result) {
            System.out.println(indexData.getDate());
        }

        return result;
    }
}
