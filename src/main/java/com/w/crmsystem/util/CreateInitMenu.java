package com.w.crmsystem.util;

import com.w.crmsystem.base.HomeInfo;
import com.w.crmsystem.base.INITInfo;
import com.w.crmsystem.base.LogoInfo;
import com.w.crmsystem.base.MenuInfo;
import com.w.crmsystem.domain.Permission;
import com.google.gson.Gson;
import lombok.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/9 18:35
 * @Version 1.0
 */
public class CreateInitMenu {
    private static List<ParentMenu> parentMenus=new ArrayList<>();
    static {
        parentMenus.add(new ParentMenu("系统管理",1,"fa fa-home"));
        parentMenus.add(new ParentMenu("数据管理",2,"fa fa-window-maximize"));
        parentMenus.add(new ParentMenu("客户管理",3,"fa fa-window-maximize"));
        parentMenus.add(new ParentMenu("客户历史",4,"fa fa-window-maximize"));
        parentMenus.add(new ParentMenu("报表统计",5,"fa fa-window-maximize"));
    }
    public String getInitMenuInfo(List<Permission> permissions){
        INITInfo initInfo=new INITInfo();
        initInfo.setHomeInfo(new HomeInfo("首页",""));
        initInfo.setLogoInfo(new LogoInfo("KNAVE CRM SYSTEM","static/layuimini/images/logo.png",""));

        //处理列表信息
        MenuInfo menuInfo=new MenuInfo();

        menuInfo.setTitle("CRM系统管理");
        menuInfo.setIcon("fa fa-address-book");
        menuInfo.setTarget("_self");
        List<MenuInfo> menuInfos=new ArrayList<>();

        //循环菜单item项
        for (int i=0;i<parentMenus.size();i++){
            MenuInfo menu=new MenuInfo();
            menu.setTitle(parentMenus.get(i).getItem());
            menu.setIcon(parentMenus.get(i).getIcon());
            menu.setTarget("_self");
            //setChild()
            List<MenuInfo> child=new ArrayList<>();
            System.out.println(permissions.size());
            //因为涉及到边查边删，所以使用
            Iterator<Permission> permissionIterable=permissions.iterator();
            //循环菜单中应该有的列表项
            while (permissionIterable.hasNext()){

                Permission p=permissionIterable.next();

                if (p.getPid().equals(parentMenus.get(i).getId())){
                    System.out.println(permissionIterable);
                    MenuInfo menu1=new MenuInfo();
                    menu1.setTitle(p.getName());
                    menu1.setHref(p.getResource());
                    menu1.setTarget("_self");
                    menu1.setIcon("fa fa-tachometer");
                    //删除最近依次next
                    permissionIterable.remove();
                    child.add(menu1);

                }
            }

            menu.setChild(child);
            menuInfos.add(menu);
        }
        menuInfo.setChild(menuInfos);
        List<MenuInfo> list=new ArrayList<>();
        list.add(menuInfo);
        initInfo.setMenuInfo(list);
        Gson gson=new Gson();
        return gson.toJson(initInfo);
    }

}
//菜单父类
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class ParentMenu{
    private String item;
    private Integer id;
    private String icon;
}
