import java.util.Scanner;

public class Node {
	
		private Node left;
		private int id;
		private String name;
		private int score;
		private Node right;
		private int height;
		
		
		public Node(Scanner scanner) {
			this.left = null;
			this.id = scanner.nextInt();
			this.name = scanner.next();
			this.score = scanner.nextInt();
			this.right = null;
			this.height = 0;
		}
		public Node getLeft() {	return left;}
		public void setLeft(Node left) {this.left = left;}
		public String getName() {return name;}
		public void setName(String name) {this.name = name;}
		public int getId() {return id;}
		public void setId(int id) {this.id = id;}
		public int getScore() {return score;}
		public void setScore(int score) {this.score = score;}
		public Node getRight() {return right;}
		public void setRight(Node right) {this.right = right;}
		public int getHeight() { return height;	}
		public void setHeight(int height) {	this.height = height; }
	
}
