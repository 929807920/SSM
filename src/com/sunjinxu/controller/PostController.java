package com.sunjinxu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sunjinxu.controller.base.BaseController;
import com.sunjinxu.pojo.Post;
import com.sunjinxu.pojo.User;
import com.sunjinxu.service.PostService;
import com.sunjinxu.tools.PageInfo;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("")
public class PostController extends BaseController{

	@Autowired
	private PostService postService;
	
	/**
	 * 文章主页
	 * @param request
	 * @param response
	 */
	@RequestMapping("postListJson")
	public void listPost(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("控制器：postListJson");
		int pageSize = 5;	//设置每页数量
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		PageInfo<Post> pageInfo = postService.postList(pageNum, pageSize);
		postList(response, pageSize, pageNum, pageInfo);
	}

	/**
	 * 查找文章
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("searchPostList")
	public void searchPostList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("控制器：searchPostList");
		int pageSize = 5;	//设置每页数量
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String title = request.getParameter("title");
		PageInfo<Post> pageInfo = postService.searchPostList(pageNum, pageSize,title);
		postList(response, pageSize, pageNum, pageInfo);
	}		
	
	/**
	 * 主页
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request) throws IOException {
		System.out.println("控制器：index");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}

	/**
	 * 查找所有文章类型
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("typeList")
	public void typeList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		List<Object> types = postService.findAllType();
		printJSONArray(response, types);
		return;
	}

	/**
	 * 查看文章详情
	 * @param request
	 * @param post
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	@Transactional
	@RequestMapping("singlePost")
	public ModelAndView singlePost(HttpServletRequest request,Integer postId) throws NumberFormatException, Exception {
		System.out.println("控制器：singlePost1"+postId);
		postId = Integer.parseInt(request.getParameter("id"));
		System.out.println("控制器：singlePost2"+postId);
		ModelAndView mav = new ModelAndView("singlePost");
		Post p = postService.get(postId,getIpAddr(request));
		mav.addObject("p", p);
		return mav;
	}
	/**
	 * 加入收藏
	 * @param request
	 * @return 
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@Transactional
	@RequestMapping("collect")
	public void collect(HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, Exception {
		int postId = Integer.parseInt(request.getParameter("postId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		try {
			Integer isCollect = postService.countIsPcollectByUserIdAndPostId(userId, postId);
			//若isCollect=0表示未收藏->加入收藏，1表示已收藏->删除收藏
			if (isCollect.equals(0)) {
				postService.addCollect(postId, userId);	
				isCollect=1;
			}else {
				postService.delCollect(postId, userId);
				isCollect=0;
			}
			//更新此文章收藏数
			Integer collect = postService.countByPostId(postId);
			List<Integer> arr = new LinkedList<Integer>();
			arr.add(isCollect);
			arr.add(collect);
			printJSONIntegerArray(response, arr);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("getCollect")
	public void getCollect(HttpServletRequest request,HttpServletResponse response) throws IOException {
		int postId = Integer.parseInt(request.getParameter("postId"));
		User user = (User)request.getSession().getAttribute("user");
		Integer isCollect = 0;
		if (user!=null) {
			isCollect = postService.countIsPcollectByUserIdAndPostId(user.getId(), postId);
		}
		Integer collect = postService.countByPostId(postId);
		List<Integer> arr = new LinkedList<Integer>();
		arr.add(isCollect);
		arr.add(collect);
		printJSONIntegerArray(response, arr);
	}
	/**
	 * 新增文章
	 * @return
	 */
	@RequestMapping("createPost")
	public ModelAndView createPost() {
		System.out.println("控制器：createPost");
		ModelAndView mav = new ModelAndView("user/createPost");
		return mav;
	}
	
	/**
	 * 新增文章操作
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("insertPost")
	public ModelAndView createPost(HttpServletRequest request) {
		System.out.println("控制器：insertPost");
		Post post = new Post();
		try {
			post.setTitle(request.getParameter("title"));
			post.setSummary(request.getParameter("summary"));
			post.setContent(request.getParameter("content"));
			post.setTagName(request.getParameter("tagName"));
			post.setTypeId(Integer.parseInt(request.getParameter("typeId")));
			postService.insert(post);
			User sessionUser = (User) request.getSession().getAttribute("user");
			post.setUserId((int) sessionUser.getId());
			post.setImgPath("src/images/0.jpg");
			postService.insertAttributes(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("redirect:/singlePost?id=" + post.getId());
		return mav;
	}	

	/**
	 * 删除文章操作
	 * @param post
	 * @return
	 */
	@Transactional
	@RequestMapping("deletePost")
	public ModelAndView deletePost(Post post) {
		System.out.println("控制器：deletePost");
		postService.delete(post);
		ModelAndView mav = new ModelAndView("redirect:/index");
		return mav;
	}

	/**
	 * 修改文章
	 * @param post
	 * @return
	 */
	@RequestMapping("editPost")
	public ModelAndView editPost(Post post) {
		System.out.println("控制器：editPost");
		Post p = postService.getFullOne(post.getId());
		ModelAndView mav = new ModelAndView("user/editPost");
		mav.addObject("p", p);
		return mav;
	}

	/**
	 * 修改文章操作
	 * @param request
	 * @param post
	 * @return
	 */
	@Transactional
	@RequestMapping("updatePost")
	public ModelAndView updatePost(HttpServletRequest request, Post post) {
		System.out.println("控制器：updatePost");
		try {
			post = postService.getFullOne(post.getId());
			post.setTitle(request.getParameter("title"));
			post.setSummary(request.getParameter("summary"));
			post.setContent(request.getParameter("content"));
			post.setTypeId(Integer.parseInt(request.getParameter("typeId")));
			post.setImgPath("src/images/0.jpg");
			post.setTagName(request.getParameter("tagName"));
			post.setUpdate_at(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
			postService.update(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("redirect:/singlePost?id=" + post.getId());
		return mav;
	}

	/**
	 * 标签云
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("tagCloud")
	public void tagCloud(HttpServletResponse response) throws Exception {
		System.out.println("控制器：tagCloud");
		postService.findTagCloud();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONArray tags = JSONArray.fromObject(postService.findTagCloud());
		out.print(tags);
		out.flush();
		out.close();
		return;
	}
	
	
	
	/**
	 * 取消收藏
	 * @param request
	 */
	@Transactional
	@RequestMapping("delCollect")
	public void delCollect(HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		postService.delCollect(Integer.parseInt(request.getParameter("postId")), user.getId());
	}
}
