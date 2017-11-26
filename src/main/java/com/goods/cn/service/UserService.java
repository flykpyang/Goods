package com.goods.cn.service;

import com.alibaba.fastjson.JSONObject;
import com.fly.cn.dao.PageQueryResult;
import com.fly.cn.netframe.BaseNetAns;
import com.fly.cn.netframe.NetService;
import com.fly.cn.netframe.RequestInfo;
import com.goods.cn.config.BaseConfig;
import com.goods.cn.config.CgiConfig;
import com.goods.cn.dao.DestDTO;
import com.goods.cn.factory.CheckSubscribeAns;
import com.goods.cn.po.*;
import com.goods.cn.util.BaseUtil;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserService extends BaseService{

    public void saveUser(UserInfo userinfo) throws Exception {
        userDao.save(userinfo);
    }


    public UserModel findUserByOpenId(String openid,int appid) throws Exception {
        UserInfo userInfo = userDao.checkUserByOpenid(openid,appid);
        UserModel userModel = new UserModel();
        App app=appDao.findAppById(appid);
        System.out.println("userinfo=" + userInfo + "  openid=" + openid);
        if (userInfo == null) {
            userInfo = BaseUtil.creatNewUserInfo(openid,appid);
            //这里的入侵太强了  <aop:aspectj-autoproxy expose-proxy="true" />
            System.out.println("new user register!!!");
            ((UserService) AopContext.currentProxy()).saveUser(userInfo);
            //这中方式也可以 getBean 生成的是代理
            //UserService userService=(UserService) SpringConfigTool.getBean(BeanDaoImplConfig.USERSERVICE);
            //userService.saveUser(userInfo);
        }
        List<Useraddress> useraddresses=userAddressDao.findUserAddress(openid,appid);
        userModel.useraddresses=useraddresses;
        userModel.userInfo = userInfo;
        userModel.soreName=app.getcSoreName();
        int vid = userInfo.getcVid();
        Vip vip = vipDao.getVipByvid(vid,appid);
        if (vip == null) {
            vip = new Vip();
        }
        vip.setcAppid(appid);
        userModel.vipInfo = vip;
        if (vip.getcRank() == 1) {
            userModel.welcome = "会员可以享受更多的咖啡体验!";
        }
        //查找所有有效卡片
        List<Usercard> disCards = userCardDao.findAllCardByOpenid(openid, appid,true);
        userModel.disCards = disCards;
        return userModel;
    }



    /**
     * 更新
     * @param userInfo
     * @throws Exception
     */
    public void  update(UserInfo userInfo) throws Exception{
        userDao.update(userInfo);
    }


    public UserInfo updateUserInfo(UpdateUserInfo userInfo) throws Exception {
        String openid = userInfo.getOpenid();
        int    appid=userInfo.getAppid();
        UserInfo user = userDao.checkUserByOpenid(openid,appid);
        if (user != null) {
            //老的user
            Userupdatelog userupdatelog = new Userupdatelog();
            userupdatelog.setcOpenid(openid);
            userupdatelog.setcAppid(userInfo.getAppid());
            userupdatelog.setcOld(JSONObject.toJSONString(user));
            String name = userInfo.getName();
            user.setcName(name);
            String birthday = userInfo.getBirthday();
            user.setcBirthday(birthday);
            String phone = userInfo.getPhone();
            user.setcPhone(phone);
            String sex = userInfo.getSex();
            user.setcSex(sex);
            System.out.println(" user info  update!!!");
            //新的user
            userupdatelog.setcNew(JSONObject.toJSONString(user));
            ((UserService) AopContext.currentProxy()).updateAndlog(user,userupdatelog);
        }
        return user;
    }

    public void updateAndlog(UserInfo userInfo,Userupdatelog userupdatelog) throws Exception {
        userDao.update(userInfo);
    }


    public List<UserInfo> findAllUserInfoByVid(int vid,int appid) throws Exception {
        return userDao.findAllUserByVid(vid,appid);
    }

    public List<Vip>  getAllVip(int appid)throws Exception{
        return vipDao.getAllVip(appid);
    }


    public PageQueryResult<DestDTO> findAllUserInfo(int pageindex, int pagesize,int appid) throws Exception {
        return userDao.findAllUserInfo(pageindex,pagesize,appid);
    }

    public Love userLove(String openid,int appid,String goodsid)throws Exception{
        Love love=loveDao.findLoveByOpenidAndAppidAndGoodsId(openid,appid,goodsid);
        if(love!=null){
            //取消关注
            loveDao.delete(love);
        }else{
            //开始专注
            love=new Love();
            love.setcAppid(appid);
            love.setcGoodsid(goodsid);
            love.setcOpenid(openid);
            loveDao.save(love);
        }
        return love;
    }

    /**
     * 获取商家二维码
     * @param openid
     * @param appid
     * @return
     * @throws Exception
     */
    public String checkUserSubscribe(String openid,int appid)throws Exception{
        App app=appDao.findAppById(appid);
        if(app!=null){
            String appstringid=app.getcAppid();
            RequestInfo info=new RequestInfo();
            info.url= BaseConfig.WXSUBSCRIBEURL;
            info.openid=openid;
            info.appId=appstringid;
            info.requestKey= CgiConfig.WXSUBSCRIBE;
            CheckSubscribeAns ans=(CheckSubscribeAns)NetService.getInstance().startSynHttpNetWork(info);
            if(!ans.isError&&ans.subscribe==0){
                 //没有关注
                 return app.getcOrcode();
            }
        }
        return "-1";
    }

}
