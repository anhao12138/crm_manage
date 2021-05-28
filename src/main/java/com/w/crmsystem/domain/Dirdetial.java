package com.w.crmsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/12 19:27
 * @Version 1.0
 */
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dirdetial {
    private Integer dirdetailId;
    private String title;
    private Integer sequence;
}
