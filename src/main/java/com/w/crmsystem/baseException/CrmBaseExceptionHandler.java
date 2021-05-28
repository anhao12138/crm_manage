package com.w.crmsystem.baseException;

import com.w.crmsystem.base.ResultSingleInfo;
import com.google.gson.Gson;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.util.NestedServletException;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/8 16:11
 * @Version 1.0
 */

/**
 * 异常在controller层增强
 */
//@ControllerAdvice
public class CrmBaseExceptionHandler {

    /**
     * 控制整异常
     * @param ne
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public String NullPointer(NullPointerException ne, HandlerMethod method, HttpServletResponse response) throws IOException {
        boolean b = method.hasMethodAnnotation(ResponseBody.class);
        MethodParameter returnType = method.getReturnType();
        PrintWriter writer = response.getWriter();
        ne.printStackTrace();
        return "/403.do";
    }

    /**
     * 文件未找到
     * @param fe
     * @param method
     * @param response
     * @return
     */
    @ExceptionHandler(FileNotFoundException.class)
    public String FileNotFound(FileNotFoundException fe,HandlerMethod method,HttpServletResponse response){
        return "403";
    }

    /**
     * 模板找不到异常
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String TemlateInput(){
        return "403";
    }

    @ExceptionHandler(NestedServletException.class)
    public String NestedServlet(){
        return "403";
    }

    /**
     * 传入的信息为空异常
     * @param method
     * @param response
     * @param ne
     * @return
     * @throws IOException
     */
    @ExceptionHandler(NullInfoException.class)
    public String GetNullInfo(HandlerMethod method,HttpServletResponse response,NullInfoException ne) throws IOException {
        if (method.hasMethodAnnotation(ResponseBody.class)){
            ResultSingleInfo resultSingleInfo=new ResultSingleInfo();
            resultSingleInfo.setCode(202);
            resultSingleInfo.setMsg(ne.getMessage());
            Gson gson=new Gson();
            Writer out=response.getWriter();
            ((PrintWriter) out).write(gson.toJson(resultSingleInfo));
        }
        return "error";
    }
}
