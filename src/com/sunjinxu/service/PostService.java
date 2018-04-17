package com.sunjinxu.service;

import java.util.List;
import java.util.Map;

import com.sunjinxu.pojo.Post;
import com.sunjinxu.tools.PageInfo;

public interface PostService {
	
	public List<Object> findAllType();

	Map<String, Double> findTagCloud() throws Exception;
	
	void insert(Post post);
	
	void insertAttributes(Post post);
	
	void update(Post post);
	
	void delete(Post post);
	
	Post get(int postId,String ip);
	
	Post getFullOne(int id);
	
	PageInfo<Post> searchPostList(int start ,int pageSize,String title);
	
	PageInfo<Post> postList(int pageNum, int pageSize);
	
	public int countByUserId(int id);
	
	public int countByPostId(int postId);
	
	public int countIsPcollectByUserIdAndPostId(int userId, int postId);

	public void addCollect(int postId, int userId);

	public void delCollect(int postId, int id);
}
