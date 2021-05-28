package com.w.crmsystem.domain;

import com.w.crmsystem.base.ResultInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/9 13:44
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleExample {
    private Integer roleId;

    private String sn;

    private String name;

    private ResultInfo<Permission> permissions;
}
