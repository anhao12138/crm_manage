package com.w.crmsystem.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/13 22:15
 * @Version 1.0
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailInfoExample {
    private Integer id;
    private String title;
}
