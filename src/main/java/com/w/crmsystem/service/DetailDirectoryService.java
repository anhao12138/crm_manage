package com.w.crmsystem.service;

import com.w.crmsystem.domain.DataDictionary;
import com.w.crmsystem.domain.DetailDirectory;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/12 19:32
 * @Version 1.0
 */
public interface DetailDirectoryService {
    boolean addDeatilDire(DetailDirectory detailDirectory);
    boolean delDetailById(Integer id);
    boolean updateDetailInfo(DetailDirectory detailDirectory);

}
