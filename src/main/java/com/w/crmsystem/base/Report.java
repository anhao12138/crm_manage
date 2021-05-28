package com.w.crmsystem.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/17 15:19
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Report {
    public Integer groupType;
    public String name;
    public String startTime;
    public String endTime;
    public String typeInfoStr;
    public Integer offset;
    public Integer limit;

    public String getTypeInfoStr() {
        String s="";
        switch (groupType){
            case 0:
                s=ReportEnum.MYSQLSTATEMENT_EMP.statment;
                break;
            case 1:
                s=ReportEnum.MYSQLSTATEMENT_YEAR.statment;
                break;
            case 2:
                s=ReportEnum.MYSQLSTATEMENT_MONTH.statment;
                break;
            case 3:
                s=ReportEnum.MYSQLSTATEMENT_DAY.statment;
                break;
        }
        return s;
    }
}
