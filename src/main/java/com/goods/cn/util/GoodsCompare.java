package com.goods.cn.util;




import com.goods.cn.po.Type;

import java.util.Comparator;

/**
 * Created by fly on 17/3/26.
 */
public class GoodsCompare implements Comparator<Type> {

    public int compare(Type o1, Type o2) {
        if(o1!=null&&o2!=null) {
            Integer typeid = o1.getcOrder();
            Integer typeid2 = o2.getcOrder();
            return typeid.compareTo(typeid2);
        }
        return  0;
    }
}
