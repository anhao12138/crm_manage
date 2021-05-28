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
 * @Date 2021/5/15 1:06
 * @Version 1.0
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HandOverHistory {
    private Integer handoverId;
    private Integer customerId;
    private Date transTime;
    private String transUser;
    private Integer oldSellerId;
    private Integer newSellerId;
    private String transReason;
}
