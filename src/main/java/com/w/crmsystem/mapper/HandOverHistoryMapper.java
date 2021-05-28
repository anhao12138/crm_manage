package com.w.crmsystem.mapper;

import com.w.crmsystem.domain.HandOverHistory;
import com.w.crmsystem.domain.HandOverHistoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/15 1:11
 * @Version 1.0
 */
public interface HandOverHistoryMapper {
    Integer addHandOverhis(HandOverHistory handOverHistory);

    List<HandOverHistoryExample> queryAll(@Param("offset")Integer offset,
                                          @Param("limit")Integer limit);

    List<HandOverHistoryExample> queryByKey(@Param("offset")Integer offset,
                                          @Param("limit")Integer limit,
                                          @Param("keyword") String keyword,
                                          @Param("startTime") String startTime,
                                          @Param("endTime") String endTime);

    Integer countAll();

    Integer countByKey(@Param("keyword") String keyword,
                       @Param("startTime") String startTime,
                       @Param("endTime") String endTime);
}
