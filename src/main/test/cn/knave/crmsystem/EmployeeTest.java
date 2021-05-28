package cn.knave.crmsystem;

import cn.knave.crmsystem.base.CakeInfo;
import cn.knave.crmsystem.base.Report;
import cn.knave.crmsystem.domain.DataDictionary;
import cn.knave.crmsystem.domain.Permission;
import cn.knave.crmsystem.service.EmployeeService;
import cn.knave.crmsystem.service.ReportService;
import cn.knave.crmsystem.util.CreateInitMenu;
import cn.knave.crmsystem.util.DateChange;
import com.google.gson.Gson;
import lombok.ToString;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.java2d.windows.GDIBlitLoops;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/5 15:25
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private EmproleMapper emproleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolepermissionMapper rolepermissionMapper;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DataDictionary dataDictionary;

    @Resource
    private ReportMapper reportMapper;

    @Resource
    private ReportService reportService;

    @Resource
    private Gson gson;

    @Test
    public void ServiceReportCake(){
        System.out.println(reportService.queryOfCake());
    }

    @Test
    public void DataDictionary(){
        dataDictionary.setTitle("你还");
        System.out.println(dataDictionary.toString());
    }

    @Test
    public void ReportInfo(){
        Report report=new Report();
        report.setGroupType(1);
        report.setLimit(5);
        report.setOffset(0);
        reportMapper.countAllByKey(report);
        System.out.println(report.getTypeInfoStr());
        reportMapper.queryAllByKey(report );
        System.out.println(report.getTypeInfoStr());
    }

    @Test
    public void CakeInfo(){
        DateChange dateChange=new DateChange();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM");
        Date date=new Date();
        String format = simpleDateFormat.format(date);
        List<CakeInfo> cakeInfos = reportMapper.queryOfCake(dateChange.getEndDate(), format);
        Map<String, Integer> initDate = dateChange.getInitDate();
        Set<Map.Entry<String, Integer>> entries = initDate.entrySet();
        List<CakeInfo> list=new LinkedList<>();

        for (Map.Entry<String, Integer> map:entries) {
            CakeInfo cake=new CakeInfo();
            cake.setValue(map.getValue());
            cake.setName(map.getKey());
            list.add(cake);
        }
        //接下来就是list和cakeinfos的比较
        for (int i=0;i<12;i++){
            for (int j=0;j<cakeInfos.size();j++){
                if (list.get(i).getName().equals(cakeInfos.get(j).getName())){
                    list.get(i).setValue(cakeInfos.get(j).getValue());
                    j=cakeInfos.size();
                }
            }
        }

        System.out.println(list);
    }
    @Test
    public void EmployeeSelect(){
        System.out.println(employeeMapper.findByUserName("admin"));
    }

    @Test
    public void EmpRole(){
        System.out.println(emproleMapper.findByUserId(1));
    }

    @Test
    public void CreateMenu(){
        List<Permission> test1 = employeeService.findByUserNameForMenu("test1");
        CreateInitMenu createInitMenu=new CreateInitMenu();
        System.out.println(createInitMenu.getInitMenuInfo(test1));
    }
}
