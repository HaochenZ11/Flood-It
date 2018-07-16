package codes;

import java.util.Scanner;

public class FloodGrid {
	private Node root;
	public boolean success;
	public Node rowMarker;
	public Node temp, position=root;
	public int number;

	public FloodGrid(int dimension) //creates a linked grid
	{
		Node temp = new Node();
		root = temp;
		Node marker = root;//marker is also temp
		
		for (int x=0; x < dimension-1; x++)//creates a first row of linked nodes
		{
			temp = new Node();
			marker.setRight(temp);

			temp.setLeft(marker);
			marker = temp;
		}//links the first row
		marker = root;
		Node rowMarker = marker;
		
		for(int z=1; z < dimension; z++)//creates rows of nodes top to bottom
		{
			temp = new Node();
			rowMarker.setDown(temp);
			temp.setUp(rowMarker);
			marker = temp;//moves the marker down
			rowMarker = temp;
			for(int y=0; y < dimension-1; y++)//creates a row of nodes left to right
			{
				temp = new Node();
				temp.setLeft(marker);
				marker.setRight(temp);
				temp.setUp(marker.getUp().getRight());
				marker.getUp().getRight().setDown(temp);
				marker = temp;//marker moves down the row from left to right
			}		
		}
	}
	public void set()//sets the numbers for the grid initially
	{
		Node temp = root;
		Node rowMarker = root;
		root.setFlood(false);
		int max=6, min=1,random=0; //6 colour options represented as numbers
		
		while(temp!=null)//checks at beginning of each new row
		{	
			while(temp!=null)//check at end of row
			{	
				random = (int)(Math.random()*(max-min+1) + min);//randomly generates number for each node
				temp.setData(random);
				temp = temp.getRight();
			}
			temp = rowMarker.getDown();
			if(rowMarker.getDown() !=null)//if it isn't the last row
			{
				rowMarker = temp;
			}
		}
		root.setFlood(true);
		setFlood();
	}
	public void flood2()//sets nodes to be a number and "member" of the group
	{	//set first node to be true 
		Node temp = root;
		Node rowMarker = root;
		while(temp!=null)//goes down the rows
		{
			while(temp!=null)//goes across a row left to right
			{	
				if(temp.isFlood()==true)//if node is part of the "group"
				{	
					temp.setData(number);//sets the node to be the number
					try //try catch loops check for nearby numbered nodes to "flood"
					{
						if(temp.getUp().getData()==number && temp.getUp().isFlood()==false)
						{
							temp.getUp().setFlood(true);
							flood2();//recursive code to set other nodes to be true that are the same number
						}
					}
					catch(Exception e)
					{
						
					}
					try
					{
						if(temp.getRight().getData()==number && temp.getRight().isFlood()==false)
						{
							temp.getRight().setFlood(true);
							flood2();
						}
					}
					catch(Exception e)
					{
						
					}
					try
					{
						if(temp.getDown().getData()==number && temp.getDown().isFlood()==false)
						{
							temp.getDown().setFlood(true);
							flood2();
						}
					}
					catch(Exception e)
					{
						
					}
					try
					{
						if(temp.getLeft().getData()==number && temp.getLeft().isFlood()==false)
						{
							temp.getLeft().setFlood(true);
							flood2();
						}
					}
					catch(Exception e)
					{
						
					}
				}
				temp = temp.getRight();
			}
			temp = rowMarker.getDown();
			if(rowMarker.getDown() !=null)//if it isn't the bottom row
			{
				rowMarker = temp;
			}	
		}//goes through the array and checks each node	
	}
	public void setFlood()//sets the grid
	{
		root.setFlood(true);

		if(root.getRight().getData()==root.getData())//checks if initially number is more than one node
		{
			root.getRight().setFlood(true);
		}
		if(root.getRight().getRight().getData()==root.getData())//checks if initially number is more than one node
		{
			root.getRight().getRight().setFlood(true);
		}
		if(root.getDown().getData()==root.getData())
		{
			root.getDown().setFlood(true);
		}
		if(root.getDown().getDown().getData()==root.getData())//checks if initially number is more than one node
		{
			root.getDown().getDown().setFlood(true);
		}
	}
	public void flood1()//general method for number input
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a number:");
		number = input.nextInt();
		
		flood2();
		display();
		if(check()==false)//if grid is not complete
		{	
			flood1();
		}
	}
	public boolean check()//checks to see if whole grid is one number
	{	Node temp = root;
		Node rowMarker = root;
		int num = root.getData();
		while(temp!=null)//check at beginning of new row
		{
			while(temp!=null)//checks the row
			{	
				if(temp.getData() != num)
				{	
					return false;//the grid is not all one number
				}
				temp = temp.getRight();
			}
			temp = rowMarker.getDown();
			if(rowMarker.getDown() !=null)//if isn't the bottom row
			{
				rowMarker = temp;
			}
		}
		return true;//if grid is completely "flooded"
	}
	public void display()//displays the current grid
	{
		Node temp = root;
		Node rowMarker = root;
		while(temp!=null)//check at beginning of new row
		{
			while(temp!=null)//check at end of row
			{
				System.out.print(temp.getData()+" ");
				temp = temp.getRight();//moves to the next node from left to right
			}
			temp = rowMarker.getDown();
			if(rowMarker.getDown() !=null)//if isn't the bottom row
			{
				rowMarker = temp;
			}
			System.out.println();
		}	
		System.out.println();
	}
}

