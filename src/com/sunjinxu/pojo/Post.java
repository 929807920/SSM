package com.sunjinxu.pojo;

import java.util.List;

public class Post {
	private int id;
	private String title;
	private String summary;
	private String content;
	private int status;
	private String create_at;
	private String update_at;
	private String imgPath;
	private String userName;
	private String typeName;
	private String tagName;
	private List<Object[]> tagNames;
	private int postId;
	private int tagId;
	private int typeId;
	private int userId;
	private int view;
	private int collect;
	private int postNum;
	private int commentNum;

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public int getCollect() {
		return collect;
	}

	public void setCollect(int collect) {
		this.collect = collect;
	}

	public List<Object[]> getTagNames() {
		return tagNames;
	}

	public void setTagNames(List<Object[]> list) {
		this.tagNames = list;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreate_at() {
		return create_at;
	}

	public void setCreate_at(String create_at2) {
		this.create_at = create_at2;
	}

	public String getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(String update_at) {
		this.update_at = update_at;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}


	public Post(int id, String title, String summary, String content,
			int status, String create_at, String update_at, String imgPath,
			String userName, String typeName, String tagName,
			List<Object[]> tagNames, int postId, int tagId, int typeId,
			int userId, int view, int collect, int postNum, int commentNum) {
		super();
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.content = content;
		this.status = status;
		this.create_at = create_at;
		this.update_at = update_at;
		this.imgPath = imgPath;
		this.userName = userName;
		this.typeName = typeName;
		this.tagName = tagName;
		this.tagNames = tagNames;
		this.postId = postId;
		this.tagId = tagId;
		this.typeId = typeId;
		this.userId = userId;
		this.view = view;
		this.collect = collect;
		this.postNum = postNum;
		this.commentNum = commentNum;
	}

	public Post() {
		super();
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", summary=" + summary
				+ ", content=" + content + ", status=" + status + ", create_at="
				+ create_at + ", update_at=" + update_at + ", imgPath="
				+ imgPath + ", userName=" + userName + ", typeName=" + typeName
				+ ", tagName=" + tagName + ", tagNames=" + tagNames
				+ ", postId=" + postId + ", tagId=" + tagId + ", typeId="
				+ typeId + ", userId=" + userId + ", view=" + view
				+ ", collect=" + collect + ", postNum=" + postNum
				+ ", commentNum=" + commentNum + "]";
	}
}
