package com.sunjinxu.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sunjinxu.pojo.User;
import com.sunjinxu.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/2/21 0021.
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	public UserService userService;
	
	@Override	// Handler执行之前调用这个方法
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("进入拦截器！");
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("user");
        if(user!=null){
        	System.out.println("Login拦截器：用户存在，放行！！");
            return true;
        }else {
        	System.out.println("Login拦截器：未登录！"+user);
		}
        request.getRequestDispatcher("/login.jsp").forward(request, response);
        return false;
	}

	@Override	// 返回modelAndView之前执行
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
		System.out.println("拦截器5：执行！");
	}
	
	@Override	// 执行Handler完成执行此方法
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		System.out.println("拦截器6：拦截器执行完毕！");
	}

}