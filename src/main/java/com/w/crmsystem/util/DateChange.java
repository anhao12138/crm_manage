package com.w.crmsystem.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/18 1:09
 * @Version 1.0
 */
@Component
public class DateChange {
    public Map<String,Integer> getInitDate(){
        Map<String,Integer> dateMap=new LinkedHashMap<>();
        Integer year=getYear();
        Integer month=getMonth();
        for (int i=0;i<12;i++){
            dateMap.put(year+"-"+addinfoMonth(month),0);
            if (month==1){
                month=12;
                year-=1;
                continue;
            }
            month--;
        }


        return dateMap;
    }
    public Integer getYear(){
        Date date=new Date();

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy");
        String format = simpleDateFormat.format(date);
        return Integer.valueOf(format);
    }
    public Integer getMonth(){
        Date date=new Date();

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM");
        String format = simpleDateFormat.format(date);
        return Integer.valueOf(format);
    }
    public String addinfoMonth(Integer mon){
        if (mon<10){
            return "0"+mon;
        }
        return mon+"";
    }
    public String getEndDate(){
        Map<String, Integer> initDate = getInitDate();
        Set<String> strings = initDate.keySet();
        int size = initDate.size();
        int key=1;
        for (String s: strings) {
            if (key==size){
                return s;
            }
            key++;
        }
        return null;
    }

    public String getNowDate(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM");
        Date date=new Date();
        String endTime = simpleDateFormat.format(date);
        return endTime;
    }
}
