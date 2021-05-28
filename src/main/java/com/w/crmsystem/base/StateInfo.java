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
 * @Date 2021/5/13 14:41
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class StateInfo {
    private String state;
    private Integer code;
}
