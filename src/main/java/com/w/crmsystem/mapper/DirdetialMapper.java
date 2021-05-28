package com.w.crmsystem.mapper;

import com.w.crmsystem.base.DetailInfoExample;
import com.w.crmsystem.domain.Dirdetial;
import com.w.crmsystem.domain.DirdetialExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/12 19:31
 * @Version 1.0
 */
public interface DirdetialMapper {
    List<DirdetialExample> queryDeatilInfo(@Param("offset") Integer offset, @Param("limit") Integer limit);

    List<DirdetialExample> queryDeatilInfoByKey(@Param("offset") Integer offset, @Param("limit") Integer limit, @Param("key") String key, @Param("dirdicId") Integer dirdicId);

    Integer countByKey(@Param("key") String key,@Param("dirdicId") Integer dirdicId);

    Integer countAll();

    Integer delByIdDirDetail(@Param("dirdeId") Integer dirdeId);

    Integer updateDirDetail(Dirdetial dirdetial);

    Integer addDirDetailInfo(Dirdetial dirdetial);

    List<DetailInfoExample> queryDetailByDataId(@Param("title") String title);

    @Select("select title from dirdetial where dirdetial_id=#{jobId}")
    String queryDeatilById(Integer jobId);
}
