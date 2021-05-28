package com.w.crmsystem.service.impl;

import com.w.crmsystem.domain.DataDictionary;
import com.w.crmsystem.mapper.DataDictionaryMapper;
import com.w.crmsystem.service.DataDictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/12 20:04
 * @Version 1.0
 */
@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {


    @Resource
    private DataDictionaryMapper dataDictionaryMapper;

    /**
     * 普通分页查询
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<DataDictionary> queryDataDicInfo(Integer page, Integer limit) {
        return dataDictionaryMapper.queryDataDicInfo((page-1)*limit,limit);
    }

    /**
     * 普通查询所有数据总数
     * @return
     */
    @Override
    public Integer countAll() {
        return dataDictionaryMapper.countAll();
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    public Integer countByKey(String key) {
        return dataDictionaryMapper.countByKey(key);
    }

    /**
     *分页条件查询
     * @param page
     * @param limit
     * @param key
     * @return
     */
    @Override
    public List<DataDictionary> queryByKeyLimit(Integer page, Integer limit, String key) {
        return dataDictionaryMapper.queryByKeyLimit((page-1)*limit,limit,key);
    }

    /**
     *删除
     * @param dataId
     * @return
     */
    @Override
    public boolean delById(Integer dataId) {
        return dataDictionaryMapper.delById(dataId)>0?true:false;
    }

    /**
     *
     * @param dataDictionary
     * @return
     */
    @Override
    public boolean addDataDicInfo(DataDictionary dataDictionary) {
        return dataDictionaryMapper.addDataDicInfo(dataDictionary)>0?true:false;
    }

    /**
     *跟新
     * @param dataDictionary
     * @return
     */
    @Override
    public boolean updateDataDicInfo(DataDictionary dataDictionary) {
        return dataDictionaryMapper.updateDataDicInfo(dataDictionary)>0?true:false;
    }

    @Override
    public List<DataDictionary> queryAll() {
        return dataDictionaryMapper.queryAll();
    }
}
