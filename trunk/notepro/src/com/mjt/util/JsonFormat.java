package com.mjt.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;

import com.mjt.entity.Category;
import com.mjt.entity.Note;
import com.mjt.entity.Schedule;

public class JsonFormat {
	public JSONArray format(List<Note> notes,List<Category> categorys) {
		List<JsonTreeNode> jsontreenodes = new ArrayList<JsonTreeNode>(0);
		if(notes != null) {
			for(Note note:notes) {
				JsonTreeNode addnode = new JsonTreeNode();
				addnode.setId(Integer.toString(note.getId()));
				addnode.setLeaf(true);
				addnode.setText(note.getTitle());
				addnode.setQtip(note.getDisplaytime().toString());
				jsontreenodes.add(addnode);
			}
			if(categorys != null) {
				for(Category category:categorys) {
					JsonTreeNode addnode = new JsonTreeNode();
					addnode.setId(category.getId());
					addnode.setLeaf(false);
					addnode.setText(category.getName());
					jsontreenodes.add(addnode);
				}
			}
		}
		JSONArray jsonObject = JSONArray.fromObject(jsontreenodes);
		return jsonObject;
	}
	
	public JSONArray formatSchedule(List<Schedule> list) {
		List<JsonScheduleNode> jsonschedulenodes = new ArrayList<JsonScheduleNode>(0);
		for(Schedule o : list) {
			JsonScheduleNode node = new JsonScheduleNode();
			node.setId(o.getId());
			node.setTitle(o.getTitle());
			node.setColor(o.getColor());
			node.setDescription(o.getDescription());
			node.setStart(o.getStart().toString());
			node.setEnd(o.getEnd().toString());
			node.setMessagenotice(o.getMessagenotice());
			if(o.getMessagenotice() == 1) {
				node.setRemindertime(o.getRemindertime().toString());
			}else {
				node.setRemindertime("");
			}
			
			jsonschedulenodes.add(node);
		}
		return JSONArray.fromObject(jsonschedulenodes);
	}
}
