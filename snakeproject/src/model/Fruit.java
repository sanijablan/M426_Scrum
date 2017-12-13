package model;

import static model.Direction.NORTH;

import java.util.concurrent.ThreadLocalRandom;

public class Fruit {

    private Position pos;
    private Snake snake;

    public Fruit(Snake snake) {
	this.snake = snake;
    }

    public void generateRandomPosition() {
	int min = 0;
	int max = snake.getFieldsize();
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

    public boolean isFruitPosition(int x, int y) {
	if (pos != null) {
	    if (pos.getX() == x && pos.getY() == y) {
		return true;
	    }
	}
	return false;
    }
}
