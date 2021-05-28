package com.w.crmsystem.base;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/17 15:25
 * @Version 1.0
 */
public enum ReportEnum {
    //员工分类
    MYSQLSTATEMENT_EMP(0,"e.realName","员工"),
    //年
    MYSQLSTATEMENT_YEAR(1,"DATE_FORMAT(p.inputTime,\"%Y\")","年"),
    //月
    MYSQLSTATEMENT_MONTH(2,"DATE_FORMAT(p.inputTime,\"%Y-%m\")","月"),
    //日
    MYSQLSTATEMENT_DAY(3,"DATE_FORMAT(p.inputTime,\"%Y-%m-%d\")","日");

    private final Integer value;
    public final String statment;
    private final String type;
    ReportEnum(Integer value,String statment ,String type){
        this.value=value;
        this.statment=statment;
        this.type=type;
    }

}
