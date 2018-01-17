package model;

/**
 * The Direction Enum enables to choose between the four wind directions
 * 
 * @author Eileen Brennwald
 *
 */
public enum Direction {

	NORTH, EAST, SOUTH, WEST;

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
