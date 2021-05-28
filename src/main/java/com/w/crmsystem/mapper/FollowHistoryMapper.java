package com.w.crmsystem.mapper;

import com.w.crmsystem.domain.FollowHistory;
import com.w.crmsystem.domain.FollowHistoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/15 1:12
 * @Version 1.0
 */
public interface FollowHistoryMapper {

    Integer addFollowHisInfo(FollowHistory followHistory);

    List<FollowHistoryExample> queryAll(@Param("offset")Integer offset,
                                        @Param("limit")Integer limit);

    List<FollowHistoryExample> queryByKey(@Param("offset")Integer offset,
                                          @Param("limit")Integer limit,
                                          @Param("keyword") String keyword,
                                          @Param("startTime") String startTime,
                                          @Param("endTime") String endTime,
                                          @Param("resultId") Integer resultId);

    Integer countAll();

    Integer countByKey(@Param("keyword") String keyword,
                       @Param("startTime") String startTime,
                       @Param("endTime") String endTime,
                       @Param("resultId") Integer resultId);
}
