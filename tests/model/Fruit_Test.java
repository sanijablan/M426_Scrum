package model;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class Fruit_Test {

    private Snake snake;

    @Before
    public void setUp() throws Exception {
	snake = new Snake(4);
    }

    @Test
    public void shouldCreateFruit() {
	// Given

	// When
	Fruit fruit = new Fruit(snake);

	// Then
	assertFalse(snake.isSnakePosition(fruit.getX(), fruit.getY()));

    }

}
