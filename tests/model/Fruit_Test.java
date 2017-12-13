package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class Fruit_Test {

    private Snake snake;
    private Fruit fruit;

    @Before
    public void setUp() throws Exception {
	snake = new Snake(4);
	fruit = new Fruit(snake);
    }

    @Test
    public void shouldCreateFruit() {
	// When
	fruit.generateRandomPosition();

	// Then
	assertFalse(snake.isSnakePosition(fruit.getX(), fruit.getY()));

    }

    @Test
    public void shouldBeFruitPosition() {
	// Given
	fruit.generateRandomPosition();
	int x = fruit.getX();
	int y = fruit.getY();

	// When
	boolean result = fruit.isFruitPosition(x, y);

	// Then
	assertTrue(result);

    }

}
