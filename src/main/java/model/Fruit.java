package model;

import static model.Direction.NORTH;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The Fruit represents a small object that can be eaten by a {@link Snake}
 * 
 * @author Eileen Brennwald
 *
 */
public class Fruit {

	private Position pos;
	private final Snake snake;
	private int value;

	public Fruit(Snake snake, int value) {
		this.snake = snake;
		this.value = value;
	}

	public Fruit(Snake snake, Position pos, int value) {
		this.snake = snake;
		this.pos = pos;
		this.value = value;
	}

	/**
	 * Generates a random Position for the fruit.
	 */
	public void generateRandomPosition() {
		int min = 0;
		int max = snake.getFieldsize() - 1;
		int x = ThreadLocalRandom.current().nextInt(min, max + 1);
		int y = ThreadLocalRandom.current().nextInt(min, max + 1);

		if (snake.isSnakePosition(x, y)) {
			generateRandomPosition();
		} else {
			try {
				pos = new Position(x, y, NORTH);
			} catch (InvalidSnakePositionException e) {
				e.printStackTrace();
			}
		}
	}

	public int getX() {
		return pos.getX();
	}

	public int getY() {
		return pos.getY();
	}

	/**
	 * Determines whether a fruit is set on the given position.
	 * 
	 * @param x The x value of the position
	 * @param y The y value of the position
	 * @return true if the fruit has this position, false otherwise
	 */
	public boolean isFruitPosition(int x, int y) {
		if (pos != null) {
			return pos.getX() == x && pos.getY() == y;
		}
		return false;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
