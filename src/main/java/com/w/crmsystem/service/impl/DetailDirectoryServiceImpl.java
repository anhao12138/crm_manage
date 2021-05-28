package com.w.crmsystem.service.impl;

import com.w.crmsystem.domain.DataDictionary;
import com.w.crmsystem.domain.DetailDirectory;
import com.w.crmsystem.mapper.DetailDirectoryMapper;
import com.w.crmsystem.service.DetailDirectoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/13 2:28
 * @Version 1.0
 */
@Service
public class DetailDirectoryServiceImpl implements DetailDirectoryService {

    @Resource
    private DetailDirectoryMapper detailDirectoryMapper;

    @Override
    public boolean addDeatilDire(DetailDirectory detailDirectory) {
        return detailDirectoryMapper.addDeatilDire(detailDirectory)>0?true:false;
    }

    @Override
    public boolean delDetailById(Integer id) {
        return detailDirectoryMapper.delDetailById(id)>0?true:false;
    }

    @Override
    public boolean updateDetailInfo(DetailDirectory detailDirectory) {
        return detailDirectoryMapper.updateDetailInfo(detailDirectory)>0?true:false;
    }


}
