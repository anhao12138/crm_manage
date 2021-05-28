package com.w.crmsystem.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/9 18:17
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MenuInfo {
    private String title;
    private String icon;
    private String href;
    private String target;
    private List<MenuInfo> child;


}
