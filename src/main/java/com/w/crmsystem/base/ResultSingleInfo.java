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
 * @Date 2021/5/7 13:05
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class ResultSingleInfo {
    private Integer code;
    private String msg;
    private Object data;
}
