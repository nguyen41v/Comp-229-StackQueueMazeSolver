// DO NOT MODIFY

public class Coordinate
{
	public final int x;
	public final int y;
	public Coordinate parent;  // used in Queue for tracking the solution (do not need for Stack)

	Coordinate ( int left, int right )  // use this constructor for STACK implementation
	{
		this.x = left; // row
		this.y = right; // col
		this.parent = null; // don't need parent for Stack implementation
	}

	Coordinate ( int left, int right, Coordinate par )  // use this constructor for QUEUE implementation
	{
		this.x = left; // row
		this.y = right; // col
		this.parent = par; // need this for Queue
	}
}
