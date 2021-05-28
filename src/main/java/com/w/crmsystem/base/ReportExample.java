package com.w.crmsystem.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/17 15:23
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class ReportExample {
    private String groupType;
    private Integer number;
}
