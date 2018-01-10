package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class SnakeTest {

	private Snake snake;

	@Before
	public void setUp() throws Exception {
		snake = new Snake(50);
	}

	@Test
	public void shouldIncreaseWhenEatingFruit() {
		// Given
		int initialSize = snake.getSnakeSize();

		// When
		snake.eatFruit();

		// Then
		assertEquals(initialSize + 1, snake.getSnakeSize());
		assertEquals(snake.getSnakebody().get(initialSize).getDirection(), snake.getSnakebody().getLast().getDirection());

	}

	@Test
	public void shouldMove() {
		// Given
		// Initial snake body positions are:
		// Position(25, 25, NORTH), Position(25, 26, NORTH)
		// Position(25, 27, NORTH), Position(25, 28, NORTH)

		// When
		snake.move();

		// Then
		LinkedList<Position> body = snake.getSnakebody();
		assertEquals(4, snake.getSnakeSize());
		assertEquals(25, body.getFirst().getX());
		assertEquals(24, body.getFirst().getY());

		assertEquals(25, body.getLast().getX());
		assertEquals(27, body.getLast().getY());
	}

	@Test
	public void shouldChangeDirection() {
		// Given
		// Snake is facing direction NORTH

		// When
		snake.setNewDirection(Direction.EAST);

		// Then
		assertEquals(Direction.EAST, snake.getSnakebody().getFirst().getDirection());

	}

	@Test
	public void shouldNotChangeDirection() {
		// Given
		// Snake is facing direction NORTH

		// When
		snake.setNewDirection(Direction.SOUTH);

		// Then
		assertEquals(Direction.NORTH, snake.getSnakebody().getFirst().getDirection());

	}

	@Test
	public void shouldBeSnakePosition() {
		// Given
		// Initial snake body positions are:
		// Position(25, 25, NORTH), Position(25, 26, NORTH)
		// Position(25, 27, NORTH), Position(25, 28, NORTH)

		// When
		boolean result = snake.isSnakePosition(25, 25);

		// Then
		assertTrue(result);
	}

	@Test
	public void shouldNotBeSnakePosition() {
		// Given
		// Initial snake body positions are:
		// Position(25, 25, NORTH), Position(25, 26, NORTH)
		// Position(25, 27, NORTH), Position(25, 28, NORTH)

		// When
		boolean result = snake.isSnakePosition(2, 2);

		// Then
		assertFalse(result);
	}

	@Test
	public void shouldBeAliveAtBeginning() {
		// When
		// snake has been created

		// Then
		assertTrue(snake.isSnakeAlive());
	}

	@Test
	public void shouldHaveReachedFruit() throws InvalidSnakePositionException {
		// Given
		Position pos = new Position(25, 25);
		Fruit fruit = new Fruit(snake, pos);

		// When
		boolean result = snake.snakeReachedFruit(fruit);

		// Then
		assertTrue(result);
	}

}
