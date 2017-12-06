package model;

import static model.Direction.NORTH;

import java.util.LinkedList;

public class Snake {

    private LinkedList<Position> snakebody;

    public Snake() {
	snakebody = new LinkedList<>();
	try {
	    snakebody.add(new Position(25, 25, NORTH));
	    snakebody.add(new Position(25, 26, NORTH));
	    snakebody.add(new Position(25, 27, NORTH));
	    snakebody.add(new Position(25, 28, NORTH));
	} catch (InvalidSnakePositionException e) {
	    e.printStackTrace();
	}

    }

    /**
     * Gets the length of the snake body
     * 
     * @return The length of the snake
     */
    public int getSnakeSize() {
	return snakebody.size();
    }

    public LinkedList<Position> getSnakebody() {
	return snakebody;
    }

    /**
     * Checks whether the snake has a body element at this position
     * 
     * @param x The row value
     * @param y The column value
     * @return true, if position is set with a body element
     */
    public boolean isSnakePosition(int x, int y) {
	boolean hasThisPosition = false;
	for (Position pos : snakebody) {
	    if (pos.getX() == x && pos.getY() == y) {
		hasThisPosition = true;
		break;
	    }
	}
	return hasThisPosition;
    }

    /**
     * Adds a body element to the snakebody with the same direction as the last
     * element
     */
    public void eatFruit() {
	try {
	    Position lastPos = snakebody.getLast();
	    Direction dir = lastPos.getDirection();
	    Position pos = new Position(lastPos.getX(), lastPos.getY(), dir);
	    snakebody.add(pos);
	} catch (InvalidSnakePositionException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Snake moves one position in the direction the head is pointing to The
     * last element is removed and a new head element is generated
     */
    public void move() {
	snakebody.removeLast();
	Position head = getNextPosition(snakebody.getFirst());
	snakebody.addFirst(head);
    }

    /**
     * Checks if the snake is still in the valid borders of the field
     * 
     * @param fieldsize The size of the quadratic field in which the snake can
     * crawl
     * @return true, if snake run out of the field
     */
    public boolean snakeRunOutOfField(int fieldsize) {
	for (Position pos : snakebody) {
	    if (pos.getX() < 0 || pos.getX() > fieldsize) {
		return true;
	    }
	    if (pos.getY() < 0 || pos.getY() > fieldsize) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Updates a given Position of the snake according to the direction it is
     * going
     * 
     * @param pos The actual Position to be updated
     */
    private Position getNextPosition(Position pos) {
	Position newPos = null;
	try {
	    newPos = new Position(pos);
	} catch (InvalidSnakePositionException e) {
	    e.printStackTrace();
	}
	switch (pos.getDirection()) {
	case NORTH:
	    newPos.decreaseCol();
	    break;

	case SOUTH:
	    newPos.increaseCol();
	    break;

	case WEST:
	    newPos.decreaseRow();
	    break;

	case EAST:
	    newPos.increaseRow();
	    break;
	}
	return newPos;
    }

}
