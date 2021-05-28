package com.w.crmsystem.service.impl;

import com.w.crmsystem.domain.Potentialcustomer;
import com.w.crmsystem.domain.PotentialcustomerExample;
import com.w.crmsystem.mapper.PotentialcustomerMapper;
import com.w.crmsystem.service.DirdetialService;
import com.w.crmsystem.service.EmployeeService;
import com.w.crmsystem.service.PotentialcustomerSerivce;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/13 15:41
 * @Version 1.0
 */
@Service
public class PotentialcustomerSerivceImpl implements PotentialcustomerSerivce {

    @Resource
    private PotentialcustomerMapper potentialcustomerMapper;
    @Resource
    private DirdetialService dirdetialService;
    @Resource
    private EmployeeService employeeService;

    /**
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<PotentialcustomerExample> queryAll(Integer page, Integer limit) {
        List<Potentialcustomer> potentialcustomers = potentialcustomerMapper.queryAll((page - 1) * limit, limit);
        return getInfo(potentialcustomers);
    }

    /**
     *
     * @param page
     * @param limit
     * @param keyword
     * @param state
     * @return
     */
    @Override
    public List<PotentialcustomerExample> queryByKey(Integer page, Integer limit, String keyword, Integer state) {

        List<Potentialcustomer> potentialcustomers = potentialcustomerMapper.queryByKey((page - 1) * limit, limit, keyword, state);


        return getInfo(potentialcustomers);
    }

    /**
     *
     * @return
     */
    @Override
    public Integer countAll() {
        return potentialcustomerMapper.countAll();
    }

    /**
     *
     * @param keyword
     * @param state
     * @return
     */
    @Override
    public Integer countByKey(String keyword, Integer state) {
        return potentialcustomerMapper.countByKey(keyword,state);
    }

    /**
     *
     * @param potentialcustomer
     * @return
     */
    @Override
    public boolean addPotentialInfo(Potentialcustomer potentialcustomer) {
        return potentialcustomerMapper.addPotentialInfo(potentialcustomer)>0?true:false;
    }

    /**
     *
     * @param potentialcustomer
     * @return
     */
    @Override
    public boolean updatePotentialInfo(Potentialcustomer potentialcustomer) {
        return potentialcustomerMapper.updatePotentialInfo(potentialcustomer)>0?true:false;
    }

    /**
     *
     * @param pcId
     * @param state
     * @return
     */
    @Override
    public boolean changeState(Integer pcId, Integer state) {
        return potentialcustomerMapper.changeState(pcId,state)>0?true:false;
    }

    /**
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<PotentialcustomerExample> queryLimitStatusAll(Integer page, Integer limit) {
        return getInfo(potentialcustomerMapper.queryLimitStatusAll((page-1)*limit,limit));
    }

    /**
     *
     * @param page
     * @param limit
     * @param keyword
     * @param state
     * @return
     */
    @Override
    public List<PotentialcustomerExample> queryLimitStatusByKey(Integer page, Integer limit, String keyword, Integer state) {
        return getInfo(potentialcustomerMapper.queryLimitStatusByKey((page-1)*limit,limit,keyword,state));
    }

    /**
     *
     * @return
     */
    @Override
    public Integer countLimitStatusAll() {
        return potentialcustomerMapper.countLimitStatusAll();
    }


    /**
     *
     * @param keyword
     * @param state
     * @return
     */
    @Override
    public Integer countLimitStatusByKey(String keyword, Integer state) {
        return potentialcustomerMapper.countLimitStatusByKey(keyword,state);
    }

    @Override
    public String queryById(Integer pcId) {
        return potentialcustomerMapper.queryById(pcId);
    }

    /**
     * 数据处理
     */
    public List<PotentialcustomerExample> getInfo(List<Potentialcustomer> potentialcustomers){
        List<PotentialcustomerExample> potentialcustomerExamples=new ArrayList<>();
        for (Potentialcustomer p:potentialcustomers){
            PotentialcustomerExample potentialcustomerExample=new PotentialcustomerExample();
            potentialcustomerExample.setSource(dirdetialService.queryDeatilById(p.getSourceId()));
            potentialcustomerExample.setJob(dirdetialService.queryDeatilById(p.getJobId()));
            potentialcustomerExample.setAge(p.getAge());
            potentialcustomerExample.setGender(p.getGender());
            potentialcustomerExample.setInputtime(p.getInputtime());
            potentialcustomerExample.setInputuser(employeeService.queryById(p.getInputuserId()));
            potentialcustomerExample.setInputuserId(p.getInputuserId());
            potentialcustomerExample.setJobId(p.getJobId());
            potentialcustomerExample.setName(p.getName());
            potentialcustomerExample.setPcId(p.getPcId());
            potentialcustomerExample.setPositivetime(p.getPositivetime());
            potentialcustomerExample.setQq(p.getQq());
            potentialcustomerExample.setSeller(employeeService.queryById(p.getSellerId()));
            potentialcustomerExample.setSellerId(p.getSellerId());
            potentialcustomerExample.setTel(p.getTel());
            potentialcustomerExample.setSourceId(p.getSourceId());
            potentialcustomerExample.setStatus(p.getStatus());
            potentialcustomerExamples.add(potentialcustomerExample);
        }
        return potentialcustomerExamples;
    }
}
