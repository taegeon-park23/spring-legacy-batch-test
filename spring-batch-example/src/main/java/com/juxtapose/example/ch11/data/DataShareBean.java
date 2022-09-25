package com.juxtapose.example.ch11.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class DataShareBean <T> {

    private static Logger logger = LoggerFactory.getLogger(DataShareBean.class);
    private List<T> list;

    public DataShareBean () {
        this.list = Collections.synchronizedList(new ArrayList<T>());
    }

    public void putData(T data) {
        if (list ==  null) {
            logger.error("list is not initialize");
            return;
        }

        list.add(data);
    }

    public List<T> getDataList () {

        if (list == null) {
            return null;
        }

        return list;
    }

    public int getSize () {
        return list.size();
    }
}
