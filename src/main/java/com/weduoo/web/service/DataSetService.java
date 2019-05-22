package com.weduoo.web.service;

import com.weduoo.web.model.DataSet;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DataSetService {
    private final List<DataSet> dataSetList = new ArrayList<DataSet>();

    @PostConstruct
    public void setup(){
        createDataSets();
    }

    public List<DataSet> findAll(){
        return Collections.unmodifiableList(dataSetList);
    }

    private Iterable<DataSet> createDataSets(){
        String name = "dummy text_";
        for (int i = 0; i < 5; i++) {
            this.dataSetList.add(new DataSet(BigInteger.valueOf(i), name + i));
        }
        return dataSetList;
    }
}
