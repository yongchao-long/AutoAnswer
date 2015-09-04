package com.example.autoanswer.been;



import java.util.Date;

import android.renderscript.Type;

public class message {

	private String name;
	private String msg;
	private Type type;
	private java.util.Date date;
	
	public enum Type{
		INCOMING,OUTCOMING;
	}

	public message(){
		
	}
	
	public message(String msg, Type type, Date date) {
		super();
		this.msg = msg;
		this.type = type;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}
	
}
