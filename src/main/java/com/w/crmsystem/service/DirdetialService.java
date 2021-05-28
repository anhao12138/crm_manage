package com.w.crmsystem.service;

import com.w.crmsystem.base.DetailInfoExample;
import com.w.crmsystem.domain.Dirdetial;
import com.w.crmsystem.domain.DirdetialExample;

import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/12 19:32
 * @Version 1.0
 */
public interface DirdetialService {
    boolean addDirDetailInfo(Dirdetial dirdetial);

    boolean updateDirDetail(Dirdetial dirdetial);

    boolean delByIdDirDetail(Integer dirdeId);

    Integer countAll();

    Integer countByKey(String key, Integer dirdicId);

    List<DirdetialExample> queryDeatilInfoByKey(Integer page, Integer limit, String key, Integer dirdicId);

    List<DirdetialExample> queryDeatilInfo(Integer page, Integer limit);

    List<DetailInfoExample> queryDetailByDataId(String title);

    String queryDeatilById(Integer jobId);
}
