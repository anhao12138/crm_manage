package com.w.crmsystem.service.impl;

import com.w.crmsystem.domain.FollowHistory;
import com.w.crmsystem.domain.FollowHistoryExample;
import com.w.crmsystem.mapper.FollowHistoryMapper;
import com.w.crmsystem.service.FollowHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/15 1:14
 * @Version 1.0
 */

@Service
public class FollowHistoryServiceImpl implements FollowHistoryService {

    @Resource
    private FollowHistoryMapper followHistoryMapper;

    /**
     * 添加跟进记录
     * @param followHistory
     * @return
     */
    @Override
    public boolean addFollowHisInfo(FollowHistory followHistory) {
        return followHistoryMapper.addFollowHisInfo(followHistory)>0?true:false;
    }

    /**
     * 分页查询普通
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<FollowHistoryExample> queryAll(Integer page, Integer limit) {
        return followHistoryMapper.queryAll((page-1)*limit,limit);
    }

    /**
     * 分页查询条件
     *
     * @param page
     * @param limit
     * @param keyword
     * @param startTime
     * @param endTime
     * @param resultId
     * @return
     */
    @Override
    public List<FollowHistoryExample> queryByKey(Integer page, Integer limit, String keyword, String startTime, String endTime, Integer resultId) {
        return followHistoryMapper.queryByKey((page-1)*limit,limit,keyword,startTime,endTime,resultId);
    }

    /**
     * 查询总数普通
     * @return
     */
    @Override
    public Integer countAll() {
        return followHistoryMapper.countAll();
    }

    /**
     * 条件查询总数
     * @param keyword
     * @param startTime
     * @param endTime
     * @param resultId
     * @return
     */
    @Override
    public Integer countByKey(String keyword, String startTime, String endTime, Integer resultId) {
        return followHistoryMapper.countByKey(keyword,startTime,endTime,resultId);
    }
}
