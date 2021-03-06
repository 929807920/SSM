package com.sunjinxu.pojo;

public class Type {
	private int id;
	private int typeCode;
	private String typeName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(int typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Type(int id, int typeCode, String typeName) {
		super();
		this.id = id;
		this.typeCode = typeCode;
		this.typeName = typeName;
	}

	public Type() {
		super();
	}

	@Override
	public String toString() {
		return "type [id=" + id + ", typeCode=" + typeCode + ", typeName=" + typeName + "]";
	}
}
