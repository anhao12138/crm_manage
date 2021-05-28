package com.w.crmsystem.base;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/15 3:32
 * @Version 1.0
 */
@Component
public class TraceTypeInfo {
    private TraceTypeInfo(){}
    public Map<String,String> getTraceInfo(){
        Map<String,String> trace=new HashMap<>();
        trace.put("电话联系","电话联系");
        trace.put("微信聊天","微信聊天");
        trace.put("下午茶交流","下午茶交流");
        trace.put("邀约钓鱼","邀约钓鱼");
        trace.put("打高尔夫","打高尔夫");
        trace.put("上门拜访","上门拜访");
        trace.put("其他","其他");
        return trace;
    }
}
