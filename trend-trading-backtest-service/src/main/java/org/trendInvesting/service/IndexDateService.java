package org.trendInvesting.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.trendInvesting.pojo.IndexData;

import java.util.List;

@FeignClient(value = "INDEX-DATA-SERVICE", fallback = IndexDataClientFeignHystrix.class)
public interface IndexDateService {

    @GetMapping("/data/{code}")
    public List<IndexData> getIndexData(@PathVariable("code") String code);
}
