package com.w.crmsystem.mapper;

import com.w.crmsystem.domain.DataDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/12 19:30
 * @Version 1.0
 */
public interface DataDictionaryMapper {
    List<DataDictionary> queryDataDicInfo(@Param("offset") Integer offset, @Param("limit") Integer limit);

    Integer countAll();

    Integer countByKey(@Param("key")String key);

    List<DataDictionary> queryByKeyLimit(@Param("offset") Integer offset, @Param("limit") Integer limit,@Param("key") String key);

    Integer delById(Integer dataId);

    Integer addDataDicInfo(DataDictionary dataDictionary);

    Integer updateDataDicInfo(DataDictionary dataDictionary);

    List<DataDictionary> queryAll();
}
