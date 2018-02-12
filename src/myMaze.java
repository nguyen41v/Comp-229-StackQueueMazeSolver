// DO NOT MODIFY

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
		//stSolve.stackSolveRecursive(/* use whatever parameters you need */);

		QueueSolver qSolve = new QueueSolver ( maze );
		qSolve.queueSolve();
		//qSolve.queueSolveRecursive(/* use whatever parameters you need */);
	}
}
