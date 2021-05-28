package com.w.crmsystem.mapper;

import com.w.crmsystem.domain.Potentialcustomer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PotentialcustomerMapper {
    
    List<Potentialcustomer> queryAll(@Param("offset") Integer offset, @Param("limit") Integer limit);

    List<Potentialcustomer> queryByKey(@Param("offset") Integer offset,@Param("limit") Integer limit,@Param("keyword") String keyword,@Param("state") Integer state);

    Integer countAll();

    Integer countByKey(@Param("keyword") String keyword,@Param("state") Integer state);

    Integer addPotentialInfo(Potentialcustomer potentialcustomer);

    Integer updatePotentialInfo(Potentialcustomer potentialcustomer);

    Integer changeState(@Param("pcId") Integer pcId,@Param("state") Integer state);

    List<Potentialcustomer> queryLimitStatusAll(@Param("offset") Integer offset,@Param("limit") Integer limit);

    List<Potentialcustomer> queryLimitStatusByKey(@Param("offset") Integer offset,@Param("limit") Integer limit,@Param("keyword") String keyword,@Param("state") Integer state);

    Integer countLimitStatusAll();

    Integer countLimitStatusByKey(@Param("keyword") String keyword,@Param("state") Integer state);

    String queryById(Integer pcId);


}