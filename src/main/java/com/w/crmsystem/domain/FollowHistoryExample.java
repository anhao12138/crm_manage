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
 * @Date 2021/5/15 19:43
 * @Version 1.0
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FollowHistoryExample {
    private Date traceTime;
    private String traceDetails;
    private String traceType;
    private Integer traceResult;
    private String inputUser;
    private Integer type;
    private String name;
    private String realName;
}
