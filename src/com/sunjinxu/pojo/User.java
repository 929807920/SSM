package com.sunjinxu.pojo;

public class User {

	private int id;
	private String account;
	private String userName;
	private String password;
	private String email;
	private String phone;
	private int create_at;
	private int update_at;
	private int status;
	private String avatar;
	private int userId;
	private String business;	//职业
	private String address;
	private String sex;	
	private String desc;		//自我描述
	private String birthday;	//生日
	private int postNum;		//文章数量
	private int commentNum;	//说说数量

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int i) {
		this.postNum = i;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int i) {
		this.commentNum = i;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCreate_at() {
		return create_at;
	}

	public void setCreate_at(int create_at) {
		this.create_at = create_at;
	}

	public int getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(int update_at) {
		this.update_at = update_at;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(int id, String account, String userName, String password,
			String email, String phone, int create_at, int update_at,
			int status, String avatar, int userId, String business,
			String address, String sex, String desc, String birthday,
			int postNum, int commentNum) {
		super();
		this.id = id;
		this.account = account;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.create_at = create_at;
		this.update_at = update_at;
		this.status = status;
		this.avatar = avatar;
		this.userId = userId;
		this.business = business;
		this.address = address;
		this.sex = sex;
		this.desc = desc;
		this.birthday = birthday;
		this.postNum = postNum;
		this.commentNum = commentNum;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", userName="
				+ userName + ", password=" + password + ", email=" + email
				+ ", phone=" + phone + ", create_at=" + create_at
				+ ", update_at=" + update_at + ", status=" + status
				+ ", avatar=" + avatar + ", userId=" + userId + ", business="
				+ business + ", address=" + address + ", sex=" + sex + ", desc="
				+ desc + ", birthday=" + birthday + ", postNum=" + postNum
				+ ", commentNum=" + commentNum + "]";
	}
}
