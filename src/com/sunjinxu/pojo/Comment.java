package com.sunjinxu.pojo;

public class Comment {
	private int id;
	private String content;
	private int praise;
	private int parentId;
	private String create_at;
	private int postId;
	private String postTitle;
	private int userId;
	private String userName;
	private String userAvatar;
	private int subCount;
	private int count=30;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSubCount() {
		return subCount;
	}
	public void setSubCount(int subCount) {
		this.subCount = subCount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPraise() {
		return praise;
	}
	public void setPraise(int praise) {
		this.praise = praise;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreate_at() {
		return create_at;
	}
	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	public Comment(int id, String content, int praise, int parentId, String create_at, int postId, String postTitle,
			int userId, String userName, String userAvatar, int subCount, int count) {
		super();
		this.id = id;
		this.content = content;
		this.praise = praise;
		this.parentId = parentId;
		this.create_at = create_at;
		this.postId = postId;
		this.postTitle = postTitle;
		this.userId = userId;
		this.userName = userName;
		this.userAvatar = userAvatar;
		this.subCount = subCount;
		this.count = count;
	}
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", praise=" + praise + ", parentId=" + parentId
				+ ", create_at=" + create_at + ", postId=" + postId + ", postTitle=" + postTitle + ", userId=" + userId
				+ ", userName=" + userName + ", userAvatar=" + userAvatar + ", subCount=" + subCount + ", count="
				+ count + "]";
	}
}
