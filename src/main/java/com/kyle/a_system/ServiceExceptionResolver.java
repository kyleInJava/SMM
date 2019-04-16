package com.kyle.a_system;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.kyle.utils.ServiceException;
import com.kyle.utils.StateInfo;

/**
 * 对全局异常进行处理
 * @author kyle
 *
 */
@Component
public class ServiceExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        ServiceException serviceException = null;
        if(ex instanceof ServiceException){
            serviceException = (ServiceException)ex;
        }else{
            serviceException = new ServiceException(StateInfo.error);
            ex.printStackTrace();
        }
        
        ModelAndView mv = new ModelAndView(); 
        response.setStatus(HttpStatus.OK.value()); //设置状态码  
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType  
        response.setCharacterEncoding("UTF-8"); //避免乱码  
        response.setHeader("Cache-Control", "no-cache, must-revalidate");  
        
        try {
            response.getWriter().write("{\"state\":\""+serviceException.getCode()+"\",\"info\":\"" + serviceException.getMsg() + "\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }  

        return mv;  
    }

}
