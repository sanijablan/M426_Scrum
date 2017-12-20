package model;

public class Position {

    private int row;
    private int col;

    /*
     * The direction the snake is going
     */
    private Direction direction;

    /**
     * Constructor to make a position with default direction being NORTH.
     * 
     * @param row The row or x value of the position
     * @param col The col or y value of the position
     * @throws InvalidSnakePositionException when row or col are negative
     */
    public Position(int row, int col) throws InvalidSnakePositionException {
	if (row >= 0 && col >= 0) {
	    this.row = row;
	    this.col = col;
	    this.direction = Direction.NORTH;
	} else {
	    throw new InvalidSnakePositionException(String.format("Value x{%s} and y{%s} must be greater than 0", row, col));
	}
    }

    /**
     * Constructor to make a position.
     * 
     * @param row The row or x value of the position
     * @param col The col or y value of the position
     * @param direction The direction of the position
     * @throws InvalidSnakePositionException when row or col are negative
     */
    public Position(int row, int col, Direction direction) throws InvalidSnakePositionException {
	this(row, col);
	this.direction = direction;

    }

    /**
     * Creates a position out of a given position with the same values.
     * 
     * @param pos The position from which to take the values for a new position
     * @throws InvalidSnakePositionException when row or col are negative
     */
    public Position(Position pos) throws InvalidSnakePositionException {
	this(pos.getX(), pos.getY(), pos.getDirection());
    }

    public Direction getDirection() {
	return direction;
    }

    public void setDirection(Direction dir) {
	direction = dir;
    }

    public int getX() {
	return row;
    }

    public int getY() {
	return col;
    }

    public void increaseRow() {
	row++;
    }

    public void increaseCol() {
	col++;
    }

    public void decreaseRow() {
	row--;
    }

    public void decreaseCol() {
	col--;
    }

    public void printPos() {
	System.out.println("X: " + row + " Y: " + col);
    }

    @Override
    public boolean equals(Object o) {
	if (!(o instanceof Position)) {
	    return false;
	}
	Position pos = (Position) o;
	return pos.getX() == row && pos.getY() == col;
    }
}
