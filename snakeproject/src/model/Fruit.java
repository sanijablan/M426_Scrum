package model;

import java.util.concurrent.ThreadLocalRandom;

public class Fruit {

    private Position pos;

    public Fruit(Snake snake) {
	pos = generateRandomPosition(snake);

    }

    private Position generateRandomPosition(Snake snake) {
	Direction dir = Direction.NORTH;
	Position pos = null;

	int min = 0;
	int max = snake.getFieldsize();
	int x = ThreadLocalRandom.current().nextInt(min, max + 1);
	int y = ThreadLocalRandom.current().nextInt(min, max + 1);

	if (snake.isSnakePosition(x, y)) {
	    generateRandomPosition(snake);
	} else {
	    try {
		pos = new Position(x, y, dir);
	    } catch (InvalidSnakePositionException e) {
		e.printStackTrace();
	    }
	}

	return pos;
    }

    public int getX() {
	return pos.getX();
    }

    public int getY() {
	return pos.getY();
    }

}
