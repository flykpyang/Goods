package com.goods.cn.service;

import com.goods.cn.po.Template;
import org.springframework.stereotype.Service;

@Service("TemplateService")
public class TemplateService extends BaseService {

    public Template findTemplateByappidAndType(String appid, int type) throws Exception {
        return templateDao.findTemplateByappidAndType(appid, type);
    }
}
