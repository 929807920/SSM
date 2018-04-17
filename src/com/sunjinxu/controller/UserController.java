package com.sunjinxu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sunjinxu.controller.base.BaseController;
import com.sunjinxu.pojo.Comment;
import com.sunjinxu.pojo.User;
import com.sunjinxu.service.CommentService;
import com.sunjinxu.service.PostService;
import com.sunjinxu.service.UserService;
import com.sunjinxu.tools.PageInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("")
public class UserController extends BaseController{
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PostService postService;
	
	/**
	 * 个人中心
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("center")
	public ModelAndView center(HttpServletRequest request,HttpSession session) {
		System.out.println("控制器：center");
		User user = (User) request.getSession().getAttribute("user");
		if (user!=null) {
			User userT = userService.getUserById(user.getId());
			session.setAttribute("user", userT);
			user=null;
			System.gc();
			ModelAndView mav = new ModelAndView("user/center");
			mav.addObject("user",userT);
			System.out.println("====="+userT);
			return mav;
		}else {
			ModelAndView mav = new ModelAndView("login");
			return mav;
		}
	}
	
	/**
	 * 动态
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("commentListJson")
	public void listComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			int pageSize = 20;	//设置每页数量
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			PageInfo<Comment> pageInfo = commentService.commentList(pageNum, pageSize);
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			JSONObject jsonObject =new JSONObject();
			jsonObject.put("pageInfo", pageInfo);
			out.print(jsonObject);
			out.flush();
			out.close();
		} catch (Exception e) {}
	}

	/**
	 * 发表评论
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("insertComment")
	public void insertComment(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("控制器：insertComment");
		User sessionUser = (User) request.getSession().getAttribute("user");
		Comment comment = new Comment();
		comment .setContent(request.getParameter("content"));
		comment.setUserId((int)sessionUser.getId());
		commentService.insert(comment);
        PrintWriter out = response.getWriter();
		out.print(1);
        out.flush();
        out.close();
	}
	
	/**
	 * 首页评论
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("listComment")
	public void listComment(HttpServletResponse response) throws IOException {
		System.out.println("控制器：listComment");
		Comment comment = new Comment();
		int count = comment.getCount();
		List<Comment> Comments = commentService.list(count);
		response.setCharacterEncoding("UTF-8");   
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
		JSONArray comments = JSONArray.fromObject(Comments);
		out.print(comments);
        out.flush();
        out.close();
	}
	
	/**
	 * 修改个人信息
	 * @param request
	 * @param user
	 * @param session
	 * @param mav
	 * @return
	 */
	@Transactional
	@RequestMapping("chgUserInfo")
	public ModelAndView chgUserInfo(HttpServletRequest request,User user,ServletRequest session,ModelAndView mav) {
		System.out.println("控制器：chgUserInfo");
		try {
			User userT = (User) request.getSession().getAttribute("user");
			userService.chgUserInfo(userT, user);
			session.setAttribute("user", userT);
			user=null;
			System.gc();
			mav.addObject("user",userT);
			mav.setView(new RedirectView("center"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
}
