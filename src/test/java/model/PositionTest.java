package model;

import static org.junit.Assert.fail;

import org.junit.Test;

public class PositionTest {

	private Position pos;

	@Test
	public void shouldThrowExceptionWithNegativeRow() {
		// When
		try {
			pos = new Position(-1, 1);
			fail("Expected Exception was not thrown");
		} catch (InvalidSnakePositionException e) {
			// if execution reaches here,
			// it indicates this exception was occured.
			// so we need not handle it.
		}
	}

	@Test
	public void shouldThrowExceptionWithNegativeCol() {
		// When
		try {
			pos = new Position(1, -1);
			fail("Expected Exception was not thrown");
		} catch (InvalidSnakePositionException e) {
			// if execution reaches here,
			// it indicates this exception was occured.
			// so we need not handle it.
		}
	}

	@Test
	public void shouldNotThrowExceptionWithZero() {
		// When
		try {
			pos = new Position(0, 0);
		} catch (InvalidSnakePositionException e) {
			fail("Unexpected Exception was thrown");
		}
	}

}
