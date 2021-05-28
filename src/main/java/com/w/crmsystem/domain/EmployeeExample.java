package com.w.crmsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/8 18:29
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeExample {
    private Integer empId;

    private String username;

    private String realname;

    private String password;

    private String tel;

    private String email;

    private Integer deptId;

    private Date inputtime;

    private Integer state;

    private String admin;

    private Date birthDay;

    private String sex;
    private String dept;
}
