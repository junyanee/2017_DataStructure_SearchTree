import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SearchTree {
   private Node root;
   
   public SearchTree() {
      this.root = null;
   }

   private Node makeTree() {
      try {
         System.out.println("학생 성적파일 트리");
         File file = new File("data/score.txt");
         Scanner scanner = new Scanner(file);
         while (scanner.hasNext()) {
            Node newNode = new Node(scanner);
            this.root = makeNode(root, newNode);
         }
         scanner.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (InputMismatchException e) {
         e.printStackTrace();
      }
      return root;

   }
   private int computeHeight(Node node) {
      if (node==null) {
         return -1;
      }
      node.setHeight(Math.max(
            computeHeight(node.getLeft()), 
            computeHeight(node.getRight())
            ) + 1);
      return node.getHeight();      
   }
   private int computeDiff(Node node) {
      int leftHeight = -1;
      int rightHeight = -1;
      if (node.getLeft()!=null) {
         leftHeight = node.getLeft().getHeight();
      }
      if (node.getRight()!=null) {
         rightHeight = node.getRight().getHeight();
      }
      return Math.abs(leftHeight-rightHeight);
   }
   private Node LLRotation(Node node) {
      System.out.println("LLRotation:"+node.getId());
      Node newNode = node.getRight();
      node.setRight(newNode.getLeft());
      newNode.setLeft(node);
      // recompute height
      this.computeHeight(node);
      this.computeHeight(newNode);
      return newNode;
   }
   private Node RLRotation(Node node) {
      System.out.println("RLRotation:"+node.getId());
      Node rNode = RRRotation(node.getRight());
      node.setRight(rNode);
      return this.LLRotation(node);
   }
   private Node RRRotation(Node node) {
      System.out.println("RRRotation:"+node.getId());
      Node newNode = node.getLeft();
      node.setLeft(newNode.getRight());
      newNode.setRight(node);
      // recompute height
      this.computeHeight(node);
      this.computeHeight(newNode);
      return newNode;
   }
   private Node LRRotation(Node node) {
      System.out.println("LRRotation:"+node.getId());
      Node lNode = LLRotation(node.getLeft());
      node.setLeft(lNode);
      return this.RRRotation(node);

   }
   private Node makeNode(Node node, Node newNode) {
      if (node == null) {
         System.out.println("newNode:" + newNode.getId());
         return newNode;
      } else{

      if (node.getId() > newNode.getId()) {
         System.out.println(node.getId() + ":goLeft:" + newNode.getId());
         Node lNode = makeNode(node.getLeft(), newNode);
         node.setLeft(lNode);
         this.computeHeight(node);
         System.out.println("-height:" + node.getHeight());
         if (this.computeDiff(node) > 1) {
            if (node.getLeft().getLeft() != null) {
            	System.out.println("RR 로테이션합니다");
               node =  RRRotation(node);
            } else {
            	System.out.println("LR 로테이션합니다");
               node =  LRRotation(node);
            }
         }
      } else if (node.getId() < newNode.getId()) {
         System.out.println(node.getId() + ":goRight:" + newNode.getId());
         Node rNode = makeNode(node.getRight(), newNode);
         node.setRight(rNode);
         this.computeHeight(node);
         System.out.println("-height:" + node.getHeight());
         if (this.computeDiff(node) > 1) {
            if (node.getRight().getRight() != null) {
            	System.out.println("LL 로테이션합니다");
               node = LLRotation(node);         
            } else {
            	System.out.println("RL 로테이션합니다");
               node = RLRotation(node);                  
            }
         }
      }
      }
      return node;
   }

   private void printNode(Node node) {
      if (node == null) {
         return;
      }
      
      printNode(node.getLeft());
      System.out.println("학번 :" + node.getId() + " 이름 :" + node.getName() + " 성적 :" + node.getScore());
      printNode(node.getRight());         
      //이러면 학번순
      //getleft와 getright를 바꾸면 역순
      
   }
   

   private void printTree() {
      System.out.println(" ");
      System.out.println("학생명단입니다.");
      printNode(this.root);
   }

   private void searchNode(Node node, int id) {
      if (node == null) {
         System.out.println("해당학생이 명단에 없습니다!!");
         return;
      }
      if(node.getId() == id){
         System.out.println("학번 :" + node.getId() + " 이름 :" + node.getName() + " 성적 :" + node.getScore());
         return;
      } else if (node.getId() > id){
         System.out.println("search path : " + node.getId() + "번 노드의 왼쪽자식으로");
         searchNode(node.getLeft(), id);
      } else if (node.getId() < id){
         System.out.println("search path: " + node.getId() + "번 노드의 오른쪽자식으로");
         searchNode(node.getRight(), id);
      }
      
   }

   private void searchTree() {
      
      System.out.println("학번을 입력하세요.");
      Scanner scanner = new Scanner(System.in);
      
      while (scanner.hasNext()) {
         int id = scanner.nextInt();
         this.searchNode(this.root, id);
            
         
      }
      scanner.close();
   }
   

   public void run() {
      this.makeTree();
      this.printTree();
      this.searchTree();
   }
}