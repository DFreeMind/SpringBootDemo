package com.weduoo.web.model;

import java.math.BigInteger;

public class DataSet {
    private BigInteger id;
    private String name;

    public DataSet(BigInteger id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "DataSet [id=" + id + ", name=" + name + "]";
    }
}
