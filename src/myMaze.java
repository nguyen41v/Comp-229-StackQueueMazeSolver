// DO NOT MODIFY

import java.util.LinkedList;

public class myMaze
{
	// start at upper left corner (0, 0)
	// finish at (3, 4)
	// 0's = explored spaces
	// 1's = walls
	// 2's = unexplored spaces
	// 3 = finish
	public static int[][] maze = new int[][] {
		{2, 2, 2, 1, 2},
		{2, 1, 2, 2, 2},
		{2, 1, 2, 1, 2},
		{1, 1, 2, 1, 3},
		{2, 2, 2, 2, 2}
	};

	public static void main ( String[] args )
	{
		StackSolver stSolve = new StackSolver ( maze );
		stSolve.stackSolve();
		StackSolver tSolve = new StackSolver ( maze );
		tSolve.stackSolveRecursive(0, 0);

		QueueSolver qSolve = new QueueSolver ( maze );
		qSolve.queueSolve();
		QueueSolver Solve = new QueueSolver ( maze );
		Solve.queueSolveRecursive(0,0);
	}
}
