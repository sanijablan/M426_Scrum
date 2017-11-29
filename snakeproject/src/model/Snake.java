package model;

import static model.Direction.NORTH;

import java.util.LinkedList;

public class Snake {

	private LinkedList<Position> snakebody;
	private Position head;

	public Snake() {
		snakebody = new LinkedList<>();
		try {
			head = new Position(25, 25, NORTH);
			snakebody.add(head);
			snakebody.add(new Position(25, 26, NORTH));
			snakebody.add(new Position(25, 27, NORTH));
			snakebody.add(new Position(25, 28, NORTH));
		} catch (InvalidSnakePositionException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Gets the lenght of the snake body
	 * 
	 * @return The length of the snake
	 */
	public int getSnakeSize() {
		return snakebody.size();
	}

	public LinkedList<Position> getSnakebody() {
		return snakebody;
	}

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
			Direction dir = snakebody.getLast().getDirection();
			Position pos = new Position(head.getX(), head.getY(), dir);
			snakebody.add(pos);
		} catch (InvalidSnakePositionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates a given Position of the snake according to the direction it is
	 * going
	 * 
	 * @param pos
	 *            The actual Position to be updated
	 */
	public void updatePosition(Position pos) {
		switch (pos.getDirection()) {
		case NORTH:
			head.decreaseCol();
			break;

		case SOUTH:
			head.increaseCol();
			break;

		case WEST:
			head.decreaseRow();
			break;

		case EAST:
			head.increaseRow();
			break;
		}

	}

}
