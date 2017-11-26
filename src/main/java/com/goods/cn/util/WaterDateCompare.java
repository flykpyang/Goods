package com.goods.cn.util;

import java.util.Comparator;

/**
 * Created by fly on 17/4/4.
 */
public class WaterDateCompare implements Comparator<String> {

    public int compare(String o1, String o2) {
        if(o1!=null&&o2!=null){
            return Long.valueOf(o1).compareTo(Long.valueOf(o2));
        }
        return 0;
    }
}
