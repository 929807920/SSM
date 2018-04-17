package com.sunjinxu.controller.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunjinxu.pojo.Post;
import com.sunjinxu.tools.PageInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

public class BaseController {
	// 密码加密
	public String pwdMD5(String Fpassword) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		MessageDigest md5 = MessageDigest.getInstance("MD5"); // 确定计算方法
		BASE64Encoder base64Encoder = new BASE64Encoder();
		String password = base64Encoder.encode(md5.digest(Fpassword.getBytes("utf-8")));

		return password;
	}
	
	//展示文章列表信息
	public void postList(HttpServletResponse response,int pageSize,int pageNum,PageInfo<Post> pageInfo) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			JSONObject jsonObject =new JSONObject();
			jsonObject.put("pageInfo", pageInfo);
			System.out.println("===文章列表==="+pageInfo);
			out.print(jsonObject);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	//输出JSONArray对象
	public void printJSONArray(HttpServletResponse response,List<Object> obj) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONArray typeNames = JSONArray.fromObject(obj);
		// String data = array.toString();
		out.print(typeNames);
		out.flush();
		out.close();
	}
	//输出JSONArray数组
	public void printJSONIntegerArray(HttpServletResponse response,List<Integer> ints) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONArray arrs = JSONArray.fromObject(ints);
		out.print(arrs);
		out.flush();
		out.close();
	}	
	//输出JSONString
	public void printJSONString(HttpServletResponse response,String string) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(string);
		out.flush();
		out.close();
	}
	
	//获取访问者IP地址
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");  
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getHeader("Proxy-Client-IP");  
		}  
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getHeader("WL-Proxy-Client-IP");  
		}  
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getRemoteAddr();  
		}
		return ip;  
	} 
}
