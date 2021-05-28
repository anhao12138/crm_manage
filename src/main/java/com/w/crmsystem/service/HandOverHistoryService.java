package com.w.crmsystem.service;

import com.w.crmsystem.domain.HandOverHistory;
import com.w.crmsystem.domain.HandOverHistoryExample;

import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/15 1:13
 * @Version 1.0
 */
public interface HandOverHistoryService {
    boolean addHandOverhis(HandOverHistory handOverHistory);

    List<HandOverHistoryExample> queryAll(Integer page, Integer limit);

    List<HandOverHistoryExample> queryByKey(Integer page, Integer limit, String keyword, String startTime, String endTime);

    Integer countAll();

    Integer countByKey(String keyword, String startTime, String endTime);
}
