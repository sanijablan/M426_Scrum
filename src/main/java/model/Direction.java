package model;

public enum Direction {

	/*
	 * Move up
	 */
	NORTH,

	/*
	 * Move right
	 */
	EAST,

	/*
	 * Move down
	 */
	SOUTH,

	/*
	 * Move left
	 */
	WEST;

	private Direction opposite;

	static {
		NORTH.opposite = SOUTH;
		SOUTH.opposite = NORTH;
		EAST.opposite = WEST;
		WEST.opposite = EAST;
	}

	public Direction getOppositeDirection() {
		return opposite;
	}

}
