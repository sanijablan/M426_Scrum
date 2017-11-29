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

	public LinkedList<Position> getSnakePosition() {
		return snakebody;
	}

	public Position eatFruit(int x, int y) {
		Position pos = null;
		try {
			Direction dir = snakebody.getLast().getDirection();
			pos = new Position(x, y, dir);
			snakebody.add(pos);
			return pos;
		} catch (InvalidSnakePositionException e) {
			e.printStackTrace();
		}
		return pos;
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
			head.decreaseY();
			break;

		case SOUTH:
			head.increaseY();
			break;

		case WEST:
			head.decreaseX();
			break;

		case EAST:
			head.increaseX();
			break;
		}

	}

}
