

import java.util.Scanner;

public class DTree {
	public class Node {
		private Node left;
		private char token;
		private Node right;
		private int height;
		
		
		public Node() {
			this.left = null;
			this.token = '\0';
			this.right = null;
			this.height = 0;
		}
		public Node getLeft() {	return left;}
		public void setLeft(Node left) {this.left = left;}
		public char getToken() {return token;}
		public void setToken(char token) {this.token = token;}
		public Node getRight() {return right;}
		public void setRight(Node right) {this.right = right;}
		public int getHeight() { return height;	}
		public void setHeight(int height) {	this.height = height; }
	};
	
	private Node root;
	private String expression = "IDELXWLQNG";
	private int position = 0;
	
	private Node makeTree() {
		Node node = null;
		for (int i=0; i<expression.length(); i++) {
			System.out.println("==Start:"+expression.charAt(position));
			node = addNode(node);
		}
		return node;
	}
	
	private int computeHeight(Node node) {
		return 0;
	}
	
	private Node addNode(Node node) {
		Node newNode = null;
		if (node==null) {
			newNode = new Node();
			newNode.setToken(expression.charAt(position++));
			System.out.println("MakeNode:"+newNode.getToken());
			return newNode;
		}
		
		if (node.getToken() > expression.charAt(position)) {
			System.out.println("GoLeft:"+node.getToken());
			newNode = addNode(node.getLeft());
			node.setLeft(newNode);
			node.setHeight(this.computeHeight(node));
		} else {
			System.out.println("GoRight:"+node.getToken());
			newNode = addNode(node.getRight());
			node.setRight(newNode);			
			node.setHeight(this.computeHeight(node));			
		}
		return node;
	}
	
	public void find(char c, Node node) {
		if (node == null) {
			System.out.println("Not Found! : "+c);
			return;
		}

		if (node.getToken() == c) {
			System.out.println("Found! : "+c);
			return;
		} else if (node.getToken() > c) {
			System.out.println("Going Left: " + node.getToken());
			find(c, node.getLeft());
		} else {
			System.out.println("Going Right: " + node.getToken());
			find(c, node.getRight());
		}
	}
	
	public void build() {
		root = makeTree();

		System.out.println("찾고 싶은 데이터를 입력하세요 : ");
		Scanner scanner = new Scanner(System.in);
		
		while (scanner.hasNext()) {
			String findData = scanner.next();
			find(findData.charAt(0), root); //처음에 시작점을 줘야 함.
		}
		scanner.close();
	}
}
