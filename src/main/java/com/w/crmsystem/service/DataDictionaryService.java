package com.w.crmsystem.service;

import com.w.crmsystem.domain.DataDictionary;

import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/12 19:31
 * @Version 1.0
 */
public interface DataDictionaryService {
    List<DataDictionary> queryDataDicInfo(Integer page, Integer limit);

    Integer countAll();

    Integer countByKey(String key);

    List<DataDictionary> queryByKeyLimit(Integer page, Integer limit, String key);

    boolean delById(Integer dataId);

    boolean addDataDicInfo(DataDictionary dataDictionary);

    boolean updateDataDicInfo(DataDictionary dataDictionary);

    List<DataDictionary> queryAll();
}
