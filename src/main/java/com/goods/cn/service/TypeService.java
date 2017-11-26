package com.goods.cn.service;

import com.goods.cn.po.Type;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TypeService")
public class TypeService extends BaseService{

    public List<Type>  findAllTypeByAppid(int appid)throws Exception{
        return typeDao.getAllType(appid);
    }
}
