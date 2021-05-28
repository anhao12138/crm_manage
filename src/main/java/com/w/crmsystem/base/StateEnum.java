package com.w.crmsystem.base;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/13 14:24
 * @Version 1.0
 */

/**
 * 客户状态
 */
public enum StateEnum {
    LOSS("流失",-2),
    DEVE_FAILL("开发失败",-1),
    POTENTIAL_CUSTOMER("潜在客户",0),
    FORMAL_CUSTOMER("正式客户",1),
    RESOURCES_POOL_CUSTOMER("客户资源池",2);



    private final   String state;
    private final Integer code;

    StateEnum(String state,Integer code){
        this.state=state;
        this.code=code;

    }

}
