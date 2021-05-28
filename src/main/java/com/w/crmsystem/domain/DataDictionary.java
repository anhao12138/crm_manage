package com.w.crmsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/12 19:29
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class DataDictionary {
    private Integer dicdirId;
    private String sn;
    private String title;
    private String intro;
}
