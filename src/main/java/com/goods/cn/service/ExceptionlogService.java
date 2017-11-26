package com.goods.cn.service;


import com.goods.cn.po.Exceptionlog;
import org.springframework.stereotype.Service;

@Service("ExceptionlogService")
public class ExceptionlogService extends BaseService{

    public void save(Exceptionlog exceptionlog)throws Exception{
        exceptionlogDao.save(exceptionlog);
    }
}
