package com.w.crmsystem.mapper;

import com.w.crmsystem.base.CakeInfo;
import com.w.crmsystem.base.ReportExample;
import com.w.crmsystem.base.Report;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/17 17:06
 * @Version 1.0
 */
public interface ReportMapper {
    List<ReportExample> queryAllByKey(Report report);
    Integer countAllByKey(Report report);

    List<CakeInfo> queryOfCake(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<CakeInfo> queryOfColunm(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("username")String username);
}
