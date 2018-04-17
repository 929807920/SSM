package com.sunjinxu.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunjinxu.mapper.CommentMapper;
import com.sunjinxu.pojo.Comment;
import com.sunjinxu.service.CommentService;
import com.sunjinxu.tools.PageInfo;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentMapper commentMapper;
	
	@Override
	public void insert(Comment comment) {
		comment.setCreate_at(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		commentMapper.insert(comment);
		//插入评论可以同时向评论列表用linked插入
		System.out.println("++++++++++++"+comment);
		if (comment.getParentId()!=0|| comment.getPostId()!=0) {
			commentMapper.insertCommentAttr(comment);
		}
	}

	@Override
	public void delete(int id) {
		commentMapper.deleteCommentAttr(id);
		commentMapper.delete(id);
	}

	@Override
	public Comment get(int id) {
		return commentMapper.get(id);
	}

	@Override
	public Comment getByUserId(int userId) {
		return commentMapper.getByUserId(userId);
	}

	@Override
	public Comment getByPostId(int postId) {
		return commentMapper.getByPostId(postId);
	}
	
	@Override
	public List<Comment> list(int count) {
		return commentMapper.listAll(count);
	}
	
	@Override
	public PageInfo<Comment> commentList(int pageNum ,int pageSize) {
		int start = pageSize*(pageNum-1);
		PageInfo<Comment> pageInfo = new PageInfo<Comment>(commentMapper.list(start,pageSize),commentMapper.total(),pageNum,pageSize);
		return pageInfo;
	}
	
	@Override
	public List<Comment> listByUserId(int userId) {
		return commentMapper.listByUserId(userId);
	}

	@Override
	public List<Comment> listByPostId(int postId) {
		return commentMapper.listByPostId(postId);
	}

	@Override
	public int subTotal(int id) {
		return commentMapper.subTotal(id);
	}

	@Override
	public int countByUserId(int id) {
		return commentMapper.countByUserId(id);
	}
}
