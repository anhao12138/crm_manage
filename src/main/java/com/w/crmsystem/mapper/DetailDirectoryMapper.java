package com.w.crmsystem.mapper;

import com.w.crmsystem.domain.DetailDirectory;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/12 19:32
 * @Version 1.0
 */
public interface DetailDirectoryMapper {
    Integer addDeatilDire(DetailDirectory detailDirectory);

    Integer delDetailById(Integer id);

    Integer updateDetailInfo(DetailDirectory detailDirectory);
}
