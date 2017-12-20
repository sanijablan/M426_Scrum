package model;

import static model.Direction.NORTH;

import java.util.LinkedList;

public class Snake {

    private LinkedList<Position> snakebody;
    private final int fieldsize;
    private boolean snakeAlive = true;

    /**
     * Creates a snake that can crawl in a fixed sized field of size max.
     * 
     * @param max The maximum field size in which the snake can crawl
     */
    public Snake(int max) {
	snakebody = new LinkedList<>();
	fieldsize = max;

	int posX = max / 2;
	int posY = posX;
	try {
	    snakebody.add(new Position(posX, posY++, NORTH));
	    snakebody.add(new Position(posX, posY++, NORTH));
	    snakebody.add(new Position(posX, posY++, NORTH));
	    snakebody.add(new Position(posX, posY, NORTH));
	} catch (InvalidSnakePositionException e) {
	    e.printStackTrace();
	}

    }

    public int getFieldsize() {
	return fieldsize;
    }

    public boolean isSnakeAlive() {
	return snakeAlive;
    }

    /**
     * Gets the length of the snake body.
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
     * Checks whether the snake has a body element at this position.
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
     * Checks whether the snake head has the same position as a fruit.
     * 
     * @param fruit The Fruit that is available
     * @return true, if the snake head touched the fruit
     */
    public boolean snakeReachedFruit(Fruit fruit) {
	int headX = snakebody.getFirst().getX();
	int headY = snakebody.getFirst().getY();
	return fruit.isFruitPosition(headX, headY);
    }

    /**
     * Adds a body element to the snakebody with the same direction as the last element.
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
     * Snake moves one position in the direction the head is pointing to The last element is removed and a new head
     * element is generated.
     */
    public void move() {
	if (snakeAlive) {
	    snakebody.removeLast();
	    Position head = getNextPosition(snakebody.getFirst());
	    snakebody.addFirst(head);
	}
    }

    /**
     * Changes the direction the snake is facing.
     * 
     * @param dir The direction the snake should go
     */
    public void setNewDirection(Direction dir) {
	Direction headDir = snakebody.getFirst().getDirection();
	if (!dir.equals(headDir.getOppositeDirection())) {
	    snakebody.getFirst().setDirection(dir);
	}
    }

    /**
     * Checks if the snake is still in the valid borders of the field.
     * 
     * @param fieldsize The size of the quadratic field in which the snake can crawl
     * @return true, if snake run out of the field
     */
    private boolean snakeRanOutOfField() {
	for (Position pos : snakebody) {
	    if (pos.getX() < 0 || pos.getX() >= fieldsize) {
		snakeAlive = false;
		return true;
	    }
	    if (pos.getY() < 0 || pos.getY() >= fieldsize) {
		snakeAlive = false;
		return true;
	    }
	}
	return false;
    }

    private boolean snakeRanOverItself() {
	Position head = snakebody.getFirst();
	for (int i = 1; i < snakebody.size(); i++) {
	    if (snakebody.get(i).equals(head)) {
		snakeAlive = false;
		return true;
	    }
	}
	return false;
    }

    /**
     * Checks if the snake is still alive. Snake must not run out of field or over itself to be alive
     * 
     * @return true if the snake ran out of the field or ran over itself
     */
    public boolean isGameOver() {
	return snakeRanOutOfField() || snakeRanOverItself();
    }

    /**
     * Updates a given Position of the snake according to the direction it is going.
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
	default:
	    break;
	}
	return newPos;
    }
}
