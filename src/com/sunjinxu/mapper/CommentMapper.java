package com.sunjinxu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sunjinxu.pojo.Comment;

public interface CommentMapper {
	public void insert(Comment comment);

	public void insertCommentAttr(Comment comment);

	public void delete(int id);

	public void deleteCommentAttr(int id);

	public Comment get(int id);

	public Comment getByUserId(int userId);

	public Comment getByPostId(int postId);

	public List<Comment> list(@Param("start") int start,@Param("pageSize") int pageSize);
	
	public List<Comment> listAll(int count);
	
	public List<Comment> listByUserId(int userId);

	public List<Comment> listByPostId(int postId);

	public int subTotal(int id);

	public int total();
	
	public int countByUserId(int id);
}
