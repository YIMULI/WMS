package com.yjxxt.wms.util;


import com.yjxxt.wms.exception.ParamsException;

public class AssertUtil {


    public  static void isTrue(Boolean flag,String msg){
        if(flag){
            throw  new ParamsException(msg);
        }
    }

}
