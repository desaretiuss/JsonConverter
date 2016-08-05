package org.tss.JsonConverter;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

	private String data;
	private List<TreeNode> children;
	private TreeNode parent;

	public TreeNode() {
		super();
		children = new ArrayList<TreeNode>();
	}

	public TreeNode(String data) {
		this();
		setData(data);
	}

	public List<TreeNode> getChildren() {
		return this.children;
	}

	public int getNumberOfChildren() {
		return getChildren().size();
	}

	public boolean hasChildren() {
		return (getNumberOfChildren() > 0);
	}

	public void setChildren(List<TreeNode> children) {
		for (TreeNode child : children) {
			child.setParent(this);
		}

		this.children = children;
	}

	public void addChild(TreeNode child) {
		child.setParent(this);
		children.add(child);
	}

	public TreeNode getChildAt(int index) throws IndexOutOfBoundsException {
		return children.get(index);
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String toString() {
		return getData().toString();
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

}