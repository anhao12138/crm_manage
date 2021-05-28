package com.w.crmsystem.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/18 1:41
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CakeInfo {
    private Integer value;
    private String name;
}
