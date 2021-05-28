package com.w.crmsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 零点
 */
@Component
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Potentialcustomer {
    private Integer pcId;

    private String name;

    private Integer age;

    private String gender;

    private String tel;

    private String qq;

    private Integer jobId;

    private Integer sourceId;

    private Integer sellerId;

    private Integer inputuserId;

    private Date inputtime;

    private Integer status;

    private Date positivetime;


}