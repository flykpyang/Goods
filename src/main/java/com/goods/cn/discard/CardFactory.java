package com.goods.cn.discard;


import com.goods.cn.config.BaseConfig;

/**
 * Created by fly on 17/3/28.
 */
public class CardFactory {

    private static  byte[] b=new byte[0];

    private static volatile CardFactory cardFactory;

    private CardFactory(){

    }

    public static CardFactory getInstansCardFactory(){
        if(cardFactory==null){
            synchronized (b){
                if(cardFactory==null){
                    cardFactory=new CardFactory();
                }
            }
        }
        return  cardFactory;
    }

    public IDisCard creatCard(int id){
        if(id== BaseConfig.FREECARDID){
            return  new FreeCard();
        }else if(id==BaseConfig.RANDOMCARD){
            return  new RandomCard();
        }else if(id==BaseConfig.PROSALECARD){
            return  new ProSaleDisCard();
        }else if(id==BaseConfig.FIXEDCARD){
            return  new FixedDisCard();
        }else if(id==BaseConfig.PAYFULLCARD){
            return new PayFullDisCard();
        }else if(id==BaseConfig.HOLIDAYCARD){
            return new HolidayCard();
        }else if(id==BaseConfig.ONEPRICECARD){
            return new OnePriceDisCard();
        }
        return  new FreeCard();
    }

}
