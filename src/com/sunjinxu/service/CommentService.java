package com.sunjinxu.service;

import java.util.List;
import com.sunjinxu.pojo.Comment;
import com.sunjinxu.tools.PageInfo;

public interface CommentService {
	public void insert(Comment comment);

	public void delete(int id);

	public Comment get(int id);

	public Comment getByUserId(int userId);

	public Comment getByPostId(int postId);

	List<Comment> list(int count);
	
	public List<Comment> listByUserId(int userId);

	public List<Comment> listByPostId(int postId);

	public int subTotal(int id);

	PageInfo<Comment> commentList(int pageNum, int pageSize);
	
	public int countByUserId(int id);
}
