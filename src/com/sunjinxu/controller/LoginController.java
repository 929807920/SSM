package com.sunjinxu.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sunjinxu.controller.base.BaseController;
import com.sunjinxu.mapper.UserMapper;
import com.sunjinxu.pojo.User;
import com.sunjinxu.service.UserService;

@Controller
public class LoginController extends BaseController {

	@Autowired
	UserService userService;

	@Autowired
	UserMapper userMapper;

	/** 用户登录  
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException */
	@RequestMapping("userLogin")
	@ResponseBody
	public ModelAndView userLogin(String account,String password,ModelAndView mv,HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		System.out.println("控制器：userLogin");
		User user=userService.login(account, pwdMD5(password));
        if(user!=null){
            session.setAttribute("user", user);
            mv.setView(new RedirectView("index"));
        }else {
            mv.addObject("errorMsg","登录名和密码错误，请重新输入");
            mv.setViewName("login");
        }
        return mv;
    }

	/**  用户注册 
	 * @throws NoSuchAlgorithmException */
	@RequestMapping("insertUserByReg")
	@ResponseBody
	public ModelAndView insertUserByReg(HttpServletRequest request, HttpServletResponse response,ModelAndView mv)
			throws IOException, NoSuchAlgorithmException{
		System.out.println("控制器：insertUserByReg");
		String account = request.getParameter("account");
		//String userName = new String(request.getParameter("userName").getBytes("iso8859-1"), "utf-8");
		//String email = request.getParameter("email");
		User user = new User();
		user.setAccount(account);
		user.setPassword(pwdMD5(request.getParameter("password")));
		//user.setEmail(email);
		//user.setUserName(userName);
		userService.regist(user);
		User userT = userMapper.getUserByAccount(account);
		if (userT != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", userT);
			mv.setView(new RedirectView("index"));
		} else {
			mv.addObject("errorMsg","程序内部错误！");
		}
		return mv;
	}
	
	//注销方法
    @RequestMapping("/logout")
    public ModelAndView outLogin(ModelAndView mv,HttpSession session){
        session.invalidate();
        mv.setView(new RedirectView("index"));
        return mv;
    }
	
	/** 检查账户是否已存在 */
	@RequestMapping("checkAccountExist")
	@ResponseBody
	public boolean checkAccountExist(HttpServletRequest request) throws IOException {
		System.out.println("控制器：checkAccountExist");
		return (userService.checkAccountExist(request.getParameter("account"))) ? true : false;
	}

	/** 检查用户名是否已存在 */
	@RequestMapping("checkUserNameExist")
	@ResponseBody
	public boolean checkUserNameExist(HttpServletRequest request) throws IOException {
		System.out.println("控制器：checkUserNameExist");
		String userName = new String(request.getParameter("userName").getBytes("iso8859-1"), "utf-8");
		return (userService.checkUserNameExist(userName)) ? true : false;
	}

	/** 检查邮箱是否已存在 */
	@RequestMapping("checkEmailExist")
	@ResponseBody
	public boolean checkEmailExist(HttpServletRequest request) throws IOException {
		System.out.println("控制器：checkEmailExist");
		return (userService.checkEmailExist(request.getParameter("email"))) ? true : false;
	}
}
