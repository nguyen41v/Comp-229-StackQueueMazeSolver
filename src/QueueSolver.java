// MODIFY queueSolve() in this file

import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class QueueSolver
{
	public int[][] queuemaze;
	public int dimension;

	QueueSolver( int[][] inmaze )
	{
		// deep copy
		queuemaze = new int[inmaze[0].length][inmaze[0].length];
		for ( int ii = 0; ii < inmaze[0].length; ii++ )
		{
			for ( int jj = 0; jj < inmaze[ii].length; jj++ )
			{
				queuemaze[ii][jj] = inmaze[ii][jj];
			}
		}

		dimension = inmaze[0].length; // assuming square maze
	}

	public void showMaze()
	{
		// clear the screen
		System.out.print("\033[H\033[2J");
		System.out.flush();

		// print maze
		for ( int ii = 0; ii < dimension; ii++ )
		{
			for ( int jj = 0; jj < dimension; jj++ )
			{
				System.out.print( queuemaze[ii][jj] + " " );
			}
			System.out.println();
		}
	}

	// uses Coordinate (the 3 input constructor, which differs from stackSolve)
	// does not modify global state
	public void queueSolve()
	{
		// row & col are our current position, tracks coordinates of which space we are currently on
		// start at (0, 0)
		int col = 0;
		int row = 0;

		// start at upper left corner (0, 0)
		// create queue
		Queue<Coordinate> myQueue = new LinkedList<Coordinate>();
		// mark starting space as explored in maze
		queuemaze[0][0] = 0;
		// set coordinate to enqueue (NEED PARENT COORDINATE AS 3RD PARAMETER)
		Coordinate myC = new Coordinate ( 0, 0, null );  // start has no parent (null)
		// enqueue first starting space
		myQueue.add( myC );

		// FOR DEBUGGING
		// Scanner myScan = new Scanner( System.in );

		Coordinate tempC = null;  // used in while loop, and printing final result

		// move by dequeueing every loop, iteratively enqueue surrounding spaces 
		while ( !myQueue.isEmpty() )
		{
			// FOR DEBUGGING: print maze, wait for user to go to next step
			// showMaze();
			// myScan.nextLine();

			// dequeue front, and explore spaces around it
			tempC = myQueue.remove();
			// set current position equal to whichever Coordinate was dequeue'd
			row = tempC.x;
			col = tempC.y;

			// if the status of the dequeue'd coordinate is the finish, stop while()
			if ( queuemaze[row][col] == 3 )
			{
				break;
			}
			else
			{
				// determine whether there are other spaces to explore (only spaces immediately surrounding dequeued space)
				if ( (row - 1) >= 0 ) // explore up
				{
					if ( queuemaze[row - 1][col] >= 2 ) // checking whether up space is unexplored or finish
					{
						if ( queuemaze[row - 1][col] == 3 ) // check if finish
						{
							// enqueue special case of finish space, dequeue'd tempC is parent
							myQueue.add( new Coordinate( row - 1, col, tempC ) );
						}
						else
						{
							// updating newly explored (not finish) space in maze
							queuemaze[row - 1][col] = 0;
							// enqueue newly explored (not finish) space
							myQueue.add( new Coordinate( row - 1, col, tempC ) );
						}
					}
				}
				if ( (row + 1) < dimension ) // explore down
				{
					// PART 1: WRITE YOUR CODE HERE
					if ( queuemaze[row + 1][col] >= 2 ) // checking whether up space is unexplored or finish
					{
						if ( queuemaze[row + 1][col] == 3 ) // check if finish
						{
							// enqueue special case of finish space, dequeue'd tempC is parent
							myQueue.add( new Coordinate( row + 1, col, tempC ) );
						}
						else
						{
							// updating newly explored (not finish) space in maze
							queuemaze[row+1][col] = 0;
							// enqueue newly explored (not finish) space
							myQueue.add( new Coordinate( row + 1, col, tempC ) );
						}
					}
				}
				if ( (col - 1) >= 0 ) // explore left
				{
					// PART 2: WRITE YOUR CODE HERE
					if ( queuemaze[row][col - 1] >= 2 ) // checking whether up space is unexplored or finish
					{
						if ( queuemaze[row][col - 1] == 3 ) // check if finish
						{
							// enqueue special case of finish space, dequeue'd tempC is parent
							myQueue.add( new Coordinate( row, col - 1, tempC ) );
						}
						else
						{
							// updating newly explored (not finish) space in maze
							queuemaze[row][col - 1] = 0;
							// enqueue newly explored (not finish) space
							myQueue.add( new Coordinate( row, col - 1, tempC ) );
						}
					}
				}
				if ( (col + 1) < dimension ) // explore right
				{
					// PART 3: WRITE YOUR CODE HERE
					if ( queuemaze[row][col + 1] >= 2 ) // checking whether up space is unexplored or finish
					{
						if ( queuemaze[row][col + 1] == 3 ) // check if finish
						{
							// enqueue special case of finish space, dequeue'd tempC is parent
							myQueue.add( new Coordinate( row, col + 1, tempC ) );
						}
						else
						{
							// updating newly explored (not finish) space in maze
							queuemaze[row][col + 1] = 0;
							// enqueue newly explored (not finish) space
							myQueue.add( new Coordinate( row, col + 1, tempC ) );
						}
					}
				}
			}
		}

		// print solution coordinates by traversing parents
		System.out.println( "Finished queue solver!" );
		Coordinate yetanothertemp = tempC;
		while ( yetanothertemp != null )
		{
			System.out.println( yetanothertemp.x + " " + yetanothertemp.y + " " );
			yetanothertemp = yetanothertemp.parent;
		}
	}

	// 10 pts extra credit, solve the maze recursively with a queue
	public void queueSolveRecursive() { // USE THIS ONE, defaults to starting position of 0, 0
		queueSolveRecursive(0, 0);
	}

	public void queueSolveRecursive(int startx,int starty) { // OR THIS ONE, changeable starting position
		Queue<Coordinate> myQueue = new LinkedList<Coordinate>();
		myQueue.add(new Coordinate(startx, starty, null));
		queuemaze[startx][starty] = 0;
		queueSolveRecursive(myQueue);
	}

	public void queueSolveRecursive(Queue<Coordinate> myQueue) {
		int row = myQueue.peek().x;
		int col = myQueue.peek().y;
		Coordinate tempC = myQueue.peek();
		// FOR DEBUGGING: print maze, wait for user to go to next step
		// Scanner myScan = new Scanner( System.in );
		// showMaze();
		// myScan.nextLine();
		// System.out.println(row + " " + col);

		if (queuemaze[row][col] == 3) { // base case
			System.out.println("Finished queue solver!");
			Coordinate yetanothertemp = tempC;
			while (yetanothertemp != null) {
				System.out.println(yetanothertemp.x + " " + yetanothertemp.y + " ");
				yetanothertemp = yetanothertemp.parent;
			}
		}
		else {
			myQueue.remove();
			if ( (row - 1) >= 0 ) // explore up
			{
				if ( queuemaze[row - 1][col] >= 2 ) // checking whether up space is unexplored or finish
				{
					if ( queuemaze[row - 1][col] == 3 ) // check if finish
					{
						// enqueue special case of finish space, dequeue'd tempC is parent
						myQueue.add( new Coordinate( row - 1, col, tempC ) );
					}
					else
					{
						// updating newly explored (not finish) space in maze
						queuemaze[row - 1][col] = 0;
						// enqueue newly explored (not finish) space
						myQueue.add( new Coordinate( row - 1, col, tempC ) );
					}
				}
			}
			if ( (row + 1) < dimension ) // explore down
			{
				if ( queuemaze[row + 1][col] >= 2 ) // checking whether up space is unexplored or finish
				{
					if ( queuemaze[row + 1][col] == 3 ) // check if finish
					{
						// enqueue special case of finish space, dequeue'd tempC is parent
						myQueue.add( new Coordinate( row + 1, col, tempC ) );
					}
					else
					{
						// updating newly explored (not finish) space in maze
						queuemaze[row+1][col] = 0;
						// enqueue newly explored (not finish) space
						myQueue.add( new Coordinate( row + 1, col, tempC ) );
					}
				}
			}
			if ( (col - 1) >= 0 ) // explore left
			{
				if ( queuemaze[row][col - 1] >= 2 ) // checking whether up space is unexplored or finish
				{
					if ( queuemaze[row][col - 1] == 3 ) // check if finish
					{
						// enqueue special case of finish space, dequeue'd tempC is parent
						myQueue.add( new Coordinate( row, col - 1, tempC ) );
					}
					else
					{
						// updating newly explored (not finish) space in maze
						queuemaze[row][col - 1] = 0;
						// enqueue newly explored (not finish) space
						myQueue.add( new Coordinate( row, col - 1, tempC ) );
					}
				}
			}
			if ( (col + 1) < dimension ) // explore right
			{
				if ( queuemaze[row][col + 1] >= 2 ) // checking whether up space is unexplored or finish
				{
					if ( queuemaze[row][col + 1] == 3 ) // check if finish
					{
						// enqueue special case of finish space, dequeue'd tempC is parent
						myQueue.add( new Coordinate( row, col + 1, tempC ) );
					}
					else
					{
						// updating newly explored (not finish) space in maze
						queuemaze[row][col + 1] = 0;
						// enqueue newly explored (not finish) space
						myQueue.add( new Coordinate( row, col + 1, tempC ) );
					}
				}
			}
			if (myQueue.isEmpty()) { // just in case; if you run a queue solver before this one, you might get an error
				return;
			}
				queueSolveRecursive(myQueue);
		}
	}
}