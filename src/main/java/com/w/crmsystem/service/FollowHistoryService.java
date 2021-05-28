package com.w.crmsystem.service;

import com.w.crmsystem.domain.FollowHistory;
import com.w.crmsystem.domain.FollowHistoryExample;

import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/15 1:13
 * @Version 1.0
 */
public interface FollowHistoryService {
    boolean addFollowHisInfo(FollowHistory followHistory);

    List<FollowHistoryExample> queryAll(Integer page, Integer limit);

    List<FollowHistoryExample> queryByKey(Integer page, Integer limit, String keyword, String startTime, String endTime, Integer resultId);

    Integer countAll();

    Integer countByKey(String keyword, String startTime, String endTime, Integer resultId);
}
