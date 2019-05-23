package com.weduoo.web.controller;

import com.weduoo.web.model.DataSet;
import com.weduoo.web.service.DataSetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class DataSetController {
    private final DataSetService dataSetService;

    public DataSetController(DataSetService dataSetService) {
        this.dataSetService = dataSetService;
    }

    @GetMapping(value = "/fetch-data-sets", produces = "application/json")
    public ResponseBodyEmitter fetchData(){
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            List<DataSet> dataSets = dataSetService.findAll();
            try {
                for (DataSet dataSet : dataSets) {
                    randomDelay();
                    emitter.send(dataSet);
                }
                emitter.complete();
            } catch (IOException e){
                emitter.completeWithError(e);
            }

        });
        executor.shutdown();
        return emitter;
    }

    @GetMapping(value = "/emit-data-sets")
    public SseEmitter fetchData2(){
        SseEmitter emitter = new SseEmitter();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() ->{
            List<DataSet> dataSets = dataSetService.findAll();
            try {
                for (DataSet dataSet : dataSets) {
                    randomDelay();
                    emitter.send(dataSet);
                }
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        });
        executor.shutdown();
        return emitter;
    }

    private void randomDelay(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
