package com.goods.cn.dao.impl;

import com.fly.cn.dao.BaseDao;
import com.goods.cn.dao.ITemplateDao;
import com.goods.cn.po.Template;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


@Repository("TemplateDaoImpl")
public class TemplateDaoImpl extends BaseDao<Template> implements ITemplateDao{
    public Template findTemplateByappidAndType(final String appid, final int type) throws Exception {
        final StringBuilder builder=new StringBuilder();
        builder.append("from Template t where t.cAppid=?");
        builder.append(" and t.cTemptype=?");
        builder.append(" and t.cStatus=1");
        return getHibernateTemplate().execute(new HibernateCallback<Template>() {
            public Template doInHibernate(Session session) throws HibernateException, SQLException {
                Query query=session.createQuery(builder.toString());
                query.setParameter(0,appid);
                query.setParameter(1,(byte)type);
                List<Template> templates=query.list();
                if(templates!=null&&templates.size()>0){
                    return templates.get(0);
                }
                return null;
            }
        });
    }
}
