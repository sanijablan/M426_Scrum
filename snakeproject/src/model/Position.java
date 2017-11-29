package model;

public class Position {

	private int x;
	private int y;

	/*
	 * The direction the snake is going
	 */
	private Direction direction;

	public Position(int x, int y, Direction direction)
			throws InvalidSnakePositionException {
		if (x >= 0 && y >= 0) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		} else {
			throw new InvalidSnakePositionException(String.format(
					"Value x{%s} and y{%s} must be greater than 0", x, y));
		}

	}

	public Direction getDirection() {
		return direction;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void increaseX() {
		x++;
	}

	public void increaseY() {
		y++;
	}

	public void decreaseX() {
		x--;
	}

	public void decreaseY() {
		y--;
	}
}
