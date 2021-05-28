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
 * @Date 2021/5/15 20:55
 * @Version 1.0
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HandOverHistoryExample {
    private Date transTime;
    private String transUser;
    private Integer oldSellerId;
    private Integer newSellerId;
    private String transReason;
    private String oldSeller;
    private String newSeller;
    private String name;
}
