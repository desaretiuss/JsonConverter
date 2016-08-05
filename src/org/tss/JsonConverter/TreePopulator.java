package org.tss.JsonConverter;

import java.util.ArrayList;

public class TreePopulator {

	private Tree tree;
	private String predecessorData;

	public TreePopulator(Tree tree) {
		setTree(tree);
	}

	public void createSubtreeFromLine(TreeNode treeRoot, String[] currentLine, int k) {
		TreeNode tmp = new TreeNode(currentLine[0]);
		treeRoot.addChild(tmp);
		for (int i = 1; i <= currentLine.length - 1; i++) {
			TreeNode node = new TreeNode(currentLine[i]);
			tmp.addChild(node);
			tmp = node;
		}
	}

	public void addNewNodeSubtree(TreeNode treeRoot, String[] currentLine, int k, int newNodeIndex,
			String predecessorData) {
		TreeNode newNodePredecessor = tree.find(predecessorData);
		TreeNode newNode = new TreeNode(currentLine[newNodeIndex]);
		newNodePredecessor.addChild(newNode);

		for (int i = newNodeIndex + 1; i < currentLine.length; i++) {
			TreeNode tmp = new TreeNode(currentLine[i]);
			newNode.addChild(tmp);
			newNode = tmp;
		}
	}

	public int findNewNode(String[] currentLine) {
		for (int i = 0; i < currentLine.length; i++) {
			if (tree.exists(currentLine[i])) {
				predecessorData = currentLine[i];
			} else {
				return i;
			}
		}
		return -1;
	}

	public void populateTree(TreeNode treeRoot, ArrayList<String[]> data) {

		// for each line
		for (int k = 0; k < data.size(); k++) {
			String[] currentLine = data.get(k);

			if (!tree.exists(currentLine[0])) {
				createSubtreeFromLine(treeRoot, currentLine, k);
			} else {
				if (currentLine.length == 1)
					continue;

				int newNodeIndex = findNewNode(currentLine);
				addNewNodeSubtree(treeRoot, currentLine, k, newNodeIndex, predecessorData);
			}
		}
	}

	public Tree getTree() {
		return tree;
	}

	public void setTree(Tree tree) {
		this.tree = tree;
	}
}
