package com.goods.cn.util;

import java.util.Comparator;

/**
 * Created by fly on 17/4/4.
 */
public class GiftCompare implements Comparator<Integer> {

    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}
