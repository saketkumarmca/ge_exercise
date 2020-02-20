package com.ge.exercise1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyParser implements Parser{
	
	Application myApplication;
	List<MyUser> userList;
	List<MyGroup> groupList;

	@Override
	public Application parseApplicationData(String data) {
		
		char[] arr = data.toCharArray();
		String appId = "";
		String appName = "";
		ArrayList<String> list = new ArrayList<String>();
		Set<Character> specials = new HashSet<Character>(Arrays.asList('(',')','[',']',',',',',':'));
		StringBuilder sb = new StringBuilder();
		int groupCount = 0;
		
		boolean afterSpecials = false;
		
		for(int i=0; i<arr.length-1; i++) {
			char curr = arr[i];
			
			if(Character.isWhitespace(curr) && afterSpecials) {
				continue;
			}
			if(specials.contains(curr)) {
				afterSpecials = true;
				if(sb.length()>0) {
					list.add(sb.toString());
				}
				sb = new StringBuilder();
			}else {
				afterSpecials = false;
				sb.append(""+curr);
			}
			//System.out.println("list == "+list);
		}
		
		for(int i=0; i<list.size(); i++) {
			if("Application".equals(list.get(i))) {
				appId = list.get(i+2);
				appName = list.get(i+4);
			}else if("users".equals(list.get(i))) {
				userList = new ArrayList<MyUser>();
			}else if("groups".equals(list.get(i))) {
				groupList = new ArrayList<MyGroup>();
				groupCount = i;
				break;
			}else if("User".equals(list.get(i)) && userList !=null) {
				userList.add(new MyUser(list.get(i+2), list.get(i+4)));
				i += 4;
			}
		}
		
		for( ; groupCount<list.size(); groupCount++) {
			if("Group".equals(list.get(groupCount))) {
				groupList.add(new MyGroup(list.get(groupCount+2), list.get(groupCount+4)));
				groupCount += 4;
			}else if("User".equals(list.get(groupCount))) {
				MyUser user = new MyUser(list.get(groupCount+2), list.get(groupCount+4));
				groupList.get(groupList.size()-1).addUser(user);
				groupCount += 4;
			}
		}
		
		if(!"".equals(appId) && !"".equals(appName) && userList !=null && groupList != null) {
			myApplication = new MyApplication(appId, appName, userList, groupList);
		}
		return myApplication;
	}

}
