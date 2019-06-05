package com.endava.jiracollector.JiraCollector.rest;

import com.endava.jiracollector.JiraCollector.service.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/collector")
public class CollectorResource {

    private final CollectorService collectorService;

    @Autowired
    public CollectorResource(@Qualifier("collectorServiceImpl") CollectorService collectorService) {
        this.collectorService = collectorService;
    }

    @PostMapping(value = "/save")
    public boolean saveContent(){
        return collectorService.saveContent();
    }
}
