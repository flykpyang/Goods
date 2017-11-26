package com.goods.cn.service;

import com.goods.cn.po.Discard;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by fly on 2017/7/19.
 */
@Service("discardservice")
public class DisCardService extends BaseService{


    public Discard findDisCardById(int id) throws Exception{
        Discard discard=disCardDao.findDisCardById(id);
        return discard;
    }

    public List<Discard> findIsChangeCard(int appid) throws Exception{
        List<Discard> list=disCardDao.findIsChangeCard(appid);
        return list;
    }
}
