package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class FruitTest {

    private Snake snake;
    private Fruit fruit;

    @Before
    public void setUp() throws Exception {
	snake = new Snake(2);
	fruit = new Fruit(snake);
    }

    @Test
    public void shouldCreateFruitWithPosition() {
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

    @Test
    public void shouldNotBeFruitPosition() {
	// Given
	fruit.generateRandomPosition();
	int x = fruit.getX() + 1;
	int y = fruit.getY() + 1;

	// When
	boolean result = fruit.isFruitPosition(x, y);

	// Then
	assertFalse(result);

    }

}
