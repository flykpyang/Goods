package com.goods.cn.service;

import com.goods.cn.po.Useraddress;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserAddressService")
public class UserAddressService extends BaseService {

    public void save(Useraddress useraddress)throws Exception{
        userAddressDao.save(useraddress);
    }

    public List<Useraddress> findAllAddress(String openid,int appid)throws Exception{
        return userAddressDao.findUserAddress(openid,appid);
    }

    public void  update(Useraddress useraddress)throws Exception{
        userAddressDao.update(useraddress);
    }

    public Useraddress findAddressById(String openid,int appid,int id) throws Exception{
       return userAddressDao.findUserAddressByOpenidAndId(openid,appid,id);
    }

    public void delete(Useraddress useraddress)throws Exception{
        userAddressDao.delete(useraddress);
    }
}
