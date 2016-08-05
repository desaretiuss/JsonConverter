package org.tss.JsonConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import org.tss.JsonConverter.TreePopulator;

public class JsonConverter {

	private static Tree tree;
	private static TreeNode treeRoot;
	private static ArrayList<String> resultArray;

	public static void main(String[] args) {
		resultArray = new ArrayList<String>();
		createTree();
		populateTree();
		parseToJSON(treeRoot);
		printResult(resultArray);
	}

	public static void createTree() {
		tree = new Tree();
		treeRoot = new TreeNode("{}");
		tree.setRoot(treeRoot);
	}

	public static void populateTree() {
		TreePopulator treePopulator = new TreePopulator(tree);
		treePopulator.populateTree(treeRoot, readFileToArrayList());
		tree = treePopulator.getTree();
	}

	public static void printResult(ArrayList<String> resultArray) {
		String resultString = "";
		for (String part : resultArray)
			resultString += part;
		System.out.println(resultString);
	}

	public static ArrayList<String[]> readFileToArrayList() {
		ArrayList<String[]> inputArrayList = new ArrayList<String[]>();
		BufferedReader br = null;

		try {
			String currentLine;
			InputStream stream = JsonConverter.class.getClassLoader().getResourceAsStream("data.txt");
			br = new BufferedReader(new InputStreamReader(stream));
			while ((currentLine = br.readLine()) != null) {
				inputArrayList.add(currentLine.trim().split("/"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return inputArrayList;
	}

	public static void parseToJSON(TreeNode node) {
		for (Iterator<TreeNode> i = node.getChildren().iterator(); i.hasNext();) {
			TreeNode child = (TreeNode) i.next();
			if (child.hasChildren()) {
				resultArray.add(child.getData() + ":{\n ");
				parseToJSON(child);
				resultArray.add(" }");
			} else
				resultArray.add(child.getData() + ": \"\" \n");
		}
	}
}
