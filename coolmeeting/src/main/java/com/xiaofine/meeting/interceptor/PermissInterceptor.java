package com.xiaofine.meeting.interceptor;

import com.xiaofine.meeting.model.Employee;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: xiaofine
 */
public class PermissInterceptor implements HandlerInterceptor {

    AntPathMatcher pathMatcher = new AntPathMatcher();

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getRequestURI();
        if("/".equals(requestUrl)||"/doLogin".equals(requestUrl)||"/register".equals(requestUrl)||"/doReg".equals(requestUrl)){
            return true;
        }
        HttpSession session = request.getSession(true);
        Employee employee = (Employee) session.getAttribute("currentuser");
        if(pathMatcher.match("/admin/**",requestUrl)){
            if (employee!=null){
                if (employee.getRole() == 2){
                    return true;
                }else {
                    response.getWriter().write("forbidden");
                    return false;
                }
            }
        }else {
            if (employee!=null){
                return true;
            }
        }
        response.sendRedirect("/");
        return false;
    }
}
