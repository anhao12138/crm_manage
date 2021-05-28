package com.w.crmsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/13 1:34
 * @Version 1.0
 */
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirdetialExample {
    private Integer dirdetailId;
    private String title;
    private Integer sequence;
    private Integer dicdirId;
}
