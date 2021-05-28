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
 * @Date 2021/5/15 1:08
 * @Version 1.0
 */

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FollowHistory {
    private Integer followId;
    private Date traceTime;
    private String traceDetails;
    private String traceType;
    private Integer traceResult;
    private Integer customerId;
    private String inputUser;
    private Integer type;
}

