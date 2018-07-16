package codes;

public class Node {
	private int data=0;
	private boolean flood;
	private Node up;
	private Node down; //new name of node created, not a new node itself
	private Node right;
	private Node left;
	
	public void Node()
	{
		this.data=data;
		up=null;
		down=null;
		left=null;
		right=null;
		flood=false;
	}

	public boolean isFlood() {
		return flood;
	}

	public void setFlood(boolean flood) {
		this.flood = flood;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getUp() {
		return up;
	}

	public void setUp(Node up) {
		this.up = up;
	}

	public Node getDown() {
		return down;
	}

	public void setDown(Node down) {
		this.down = down;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}


}
