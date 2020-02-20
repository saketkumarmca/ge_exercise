package com.ge.exercise1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyApplication extends Application{

	Map<String, MyUser> userMap;
	Map<String, MyGroup> groupMap;
	
	public MyApplication(String id, String name, List<MyUser> userList, List<MyGroup> groupList) {
		super(id, name);
		this.userMap = new HashMap<String, MyUser>();
		this.groupMap = new HashMap<String, MyGroup>();
		populateUserMap(userList);
		populateGroupMap(groupList);
	}

	private void populateUserMap(List<MyUser> userList) {
		userList.forEach(user -> {
			userMap.put(user.getId(), user);
		});
	}
	
	private void populateGroupMap(List<MyGroup> groupList) {
		groupList.forEach(group -> {
			groupMap.put(group.getId(), group);
		});
	}
	
	@Override
	public Collection<User> getUsers() {
		ArrayList<User> users = new ArrayList<>();
		for(Map.Entry<String, MyUser> entry : userMap.entrySet()) {
			users.add((User)entry.getValue());
		}
		return users;
	}

	@Override
	public User getUser(String userId) {
		return userMap.get(userId);
	}

	@Override
	public Collection<Group> getGroups() {
		ArrayList<Group> groups = new ArrayList<>();
		for(Map.Entry<String, MyGroup> entry : groupMap.entrySet()) {
			groups.add((Group)entry.getValue());
		}
		return groups;
	}

	@Override
	public Group getGroup(String groupId) {
		return groupMap.get(groupId);
	}

}
