package com.w.crmsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/14 13:02
 * @Version 1.0
 */
@Component
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PotentialcustomerExample {
    private Integer pcId;

    private String name;

    private Integer age;

    private String gender;

    private String tel;

    private String qq;

    private Integer jobId;

    private Integer sourceId;

    private String seller;

    private String inputuser;
    private Date inputtime;
    private Integer inputuserId;
    private Integer sellerId;
    private Integer status;

    private Date positivetime;
    private String job;
    private String source;

}
