package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.IExceptionlogDao;
import com.goods.cn.po.Exceptionlog;
import org.springframework.stereotype.Repository;

@Repository("ExceptionlogDaoImpl")
public class ExceptionlogDaoImpl extends BaseDao<Exceptionlog> implements IExceptionlogDao{
}
