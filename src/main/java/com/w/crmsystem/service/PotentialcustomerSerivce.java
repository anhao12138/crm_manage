package com.w.crmsystem.service;

import com.w.crmsystem.domain.Potentialcustomer;
import com.w.crmsystem.domain.PotentialcustomerExample;

import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/6 23:01
 * @Version 1.0
 */
public interface PotentialcustomerSerivce {
    List<PotentialcustomerExample> queryAll(Integer page, Integer limit);

    List<PotentialcustomerExample> queryByKey(Integer page, Integer limit, String keyword, Integer state);

    Integer countAll();

    Integer countByKey(String keyword, Integer state);

    boolean addPotentialInfo(Potentialcustomer potentialcustomer);

    boolean updatePotentialInfo(Potentialcustomer potentialcustomer);

    boolean changeState(Integer pcId, Integer state);

    List<PotentialcustomerExample> queryLimitStatusAll(Integer page, Integer limit);

    List<PotentialcustomerExample> queryLimitStatusByKey(Integer page, Integer limit, String keyword, Integer state);

    Integer countLimitStatusAll();

    Integer countLimitStatusByKey(String keyword, Integer state);

    String queryById(Integer pcId);
}
