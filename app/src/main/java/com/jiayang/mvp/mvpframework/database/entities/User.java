package com.jiayang.mvp.mvpframework.database.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {

	@PrimaryKey(autoGenerate = true)
	private long    uid;
	private String name;
	private String address;
	private String phone;
	private Integer age;

	public User() {
	}
	@Ignore
	public User(int uid) {
		this.uid = uid;
	}
	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User{" + "uid=" + uid + ", name='" + name + '\'' + ", address='" + address + '\'' + ", phone='" + phone + '\'' + ", age=" +
			   age + '}';
	}
}
