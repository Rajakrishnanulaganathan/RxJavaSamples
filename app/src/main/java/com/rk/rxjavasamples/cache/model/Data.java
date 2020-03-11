package com.rk.rxjavasamples.cache.model;

public class Data {
    public String source;

    @SuppressWarnings("CloneDoesntDeclareCloneNotSupportedException")
    @Override
    public Data clone() {
        return new Data();
    }
}
