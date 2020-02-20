package com.ge.exercise1;

import java.util.ArrayList;

public class MyGroup extends Group{

	ArrayList<MyUser> userList;
	public MyGroup(String id, String name) {
		super(id, name);
		this.userList = new ArrayList<MyUser>();
	}
    
	public void addUser(MyUser user){
		userList.add(user);
		size++;
	}
	
	public void removeUser(MyUser user){
		userList.remove(user);
		size--;
	}
}
