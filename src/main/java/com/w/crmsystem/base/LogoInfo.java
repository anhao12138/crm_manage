package com.w.crmsystem.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/9 18:33
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LogoInfo {
    private String title;
    private String image;
    private String href;
}
