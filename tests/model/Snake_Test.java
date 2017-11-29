package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class Snake_Test {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void shouldEatFruit() {
		// Given
		Snake snake = new Snake();
		int initialSize = snake.getSnakeSize();

		// When
		snake.eatFruit();

		// Then
		assertEquals(initialSize + 1, snake.getSnakeSize());
		assertEquals(snake.getSnakebody().get(initialSize).getDirection(),
				snake.getSnakebody().getLast().getDirection());

	}
}
