package com.mjt.util;

import java.util.List;

public class JsonTreeNode {
	private String id;
	private boolean leaf;	
	private String qtip;
	private String text;
	
	public String getId() {
		return id;
	}
	public String getQtip() {
		return qtip;
	}
	public String getText() {
		return text;
	}
	public boolean isLeaf() {
		return leaf;
	}

	public void setId(String id) {
		this.id = id;
	}
	 public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	 public void setQtip(String qtip) {
		this.qtip = qtip;
	}
	 public void setText(String text) {
		this.text = text;
	}
}
