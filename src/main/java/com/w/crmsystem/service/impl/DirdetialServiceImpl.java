package com.w.crmsystem.service.impl;

import com.w.crmsystem.base.DetailInfoExample;
import com.w.crmsystem.domain.Dirdetial;
import com.w.crmsystem.domain.DirdetialExample;
import com.w.crmsystem.mapper.DirdetialMapper;
import com.w.crmsystem.service.DirdetialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/13 1:58
 * @Version 1.0
 */
@Service
public class DirdetialServiceImpl implements DirdetialService {

    @Resource
    private DirdetialMapper dirdetialMapper;

    @Override
    public boolean addDirDetailInfo(Dirdetial dirdetial) {
        return dirdetialMapper.addDirDetailInfo(dirdetial)>0?true:false;
    }

    @Override
    public boolean updateDirDetail(Dirdetial dirdetial) {
        return dirdetialMapper.updateDirDetail(dirdetial)>0?true:false;
    }

    @Override
    public boolean delByIdDirDetail(Integer dirdeId) {
        return dirdetialMapper.delByIdDirDetail(dirdeId)>0?true:false;
    }

    @Override
    public Integer countAll() {
        return dirdetialMapper.countAll();
    }

    @Override
    public Integer countByKey(String key, Integer dirdicId) {
        return dirdetialMapper.countByKey(key, dirdicId);
    }

    @Override
    public List<DirdetialExample> queryDeatilInfoByKey(Integer page, Integer limit, String key, Integer dirdicId) {
        return dirdetialMapper.queryDeatilInfoByKey((page-1)*limit,limit,key,dirdicId);
    }

    @Override
    public List<DirdetialExample> queryDeatilInfo(Integer page, Integer limit) {
        return dirdetialMapper.queryDeatilInfo((page-1)*limit,limit);
    }

    @Override
    public List<DetailInfoExample> queryDetailByDataId(String title) {
        return dirdetialMapper.queryDetailByDataId(title);
    }

    @Override
    public String queryDeatilById(Integer jobId) {
        return dirdetialMapper.queryDeatilById(jobId);
    }
}
