package view;

import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.UP;
import static model.Direction.EAST;
import static model.Direction.NORTH;
import static model.Direction.SOUTH;
import static model.Direction.WEST;
import static view.CellStatus.BOARD;
import static view.CellStatus.FRUIT;
import static view.CellStatus.SNAKE;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import model.Direction;
import model.Fruit;
import model.Snake;

@SuppressWarnings({ "unchecked", "rawtypes", "restriction" })
public class GUI extends Application {

	private HBox buttonBox;
	private Button btnPlay;
	private Button btnPause;
	private String btnPlayStart = "Start";
	private String btnPlayRestart = "Restart";

	private HBox scoreBox;
	private Label scoreName;
	private Label scoreValue;

	private GridPane gamePane;
	private Timeline timeline = new Timeline();
	private Direction pressedDir;
	private boolean hasGameStarted = false;
	private boolean paused;

	private Snake snake;
	private Fruit fruit;

	private final int normalFruitValue = 1;
	private final int specialFruitValue = 3;
	private final int specialFruitFrequency = 5;
	private final int gridSize = 30;
	private final double speed = 250;
	private final double increment = 0.1;

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Snake");
		FlowPane root = new FlowPane(10, 10);
		root.setAlignment(Pos.BOTTOM_CENTER);
		primaryStage.setScene(new Scene(root));

		snake = new Snake(gridSize);
		fruit = new Fruit(snake, 1);
		gamePane = createGamePane();

		scoreName = new Label("Score: ");
		scoreName.setMinWidth(60);
		scoreName.setMinHeight(20);
		scoreValue = new Label();
		scoreValue.setText(Integer.toString(snake.getScore()));
		scoreValue.setMinWidth(60);
		scoreValue.setMinHeight(20);

		btnPlay = new Button(btnPlayStart);
		btnPause = new Button("II");

		btnPlay.setMinWidth(60);
		btnPause.setMinWidth(60);

		scoreBox = new HBox(3.0);
		scoreBox.getChildren().addAll(scoreName, scoreValue);

		buttonBox = new HBox(3.0);
		buttonBox.getChildren().addAll(btnPlay, btnPause);

		btnPlay.setOnAction(event -> {
			if (!hasGameStarted) {
				fruit.generateRandomPosition();
				startSnakeGame();
			}
			if (paused) {
				restartGame();
				btnPlay.setText(btnPlayStart);
			}
		});

		btnPause.setOnAction(event -> {
			if (paused == false) {
				timeline.pause();
				paused = true;
				btnPause.setText(">");
				btnPlay.setText(btnPlayRestart);
			} else {
				timeline.play();
				btnPause.setText("II");
				paused = false;
			}

		});

		root.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				Direction currentDir = snake.getSnakebody().getFirst().getDirection();
				if (event.getCode() == RIGHT && !currentDir.equals(WEST)) {
					pressedDir = EAST;
				}
				if (event.getCode() == LEFT && !currentDir.equals(EAST)) {
					pressedDir = WEST;
				}
				if (event.getCode() == UP && !currentDir.equals(SOUTH)) {
					pressedDir = NORTH;
				}
				if (event.getCode() == DOWN && !currentDir.equals(NORTH)) {
					pressedDir = SOUTH;
				}
			}
		});

		root.getChildren().addAll(scoreBox, gamePane, buttonBox);
		primaryStage.show();

	}

	/**
	 * Starts the timeline for the snake game and monitors the snake action
	 */
	private void startSnakeGame() {
		hasGameStarted = true;
		paused = false;
		timeline = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler() {
			@Override
			public void handle(Event event) {
				if (pressedDir != null) {
					snake.setNewDirection(pressedDir);
				}
				snake.move();
				if (snake.snakeReachedFruit(fruit)) {
					snakeEatsFruit();
				}
				if (snake.isGameOver()) {
					timeline.stop();
					createGameOverPane();
				}
				repaintPane();
			}
		}), new KeyFrame(Duration.millis(speed)));

		if (snake.isSnakeAlive()) {
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.play();
		}
	}

	private int calcIndex(int row, int col) {
		return row * gridSize + col;
	}

	private void repaintPane() {
		for (int row = 0; row < gridSize; row++) {
			for (int col = 0; col < gridSize; col++) {
				CellButton cb = (CellButton) gamePane.getChildren().get(calcIndex(row, col));
				if (snake.isSnakePosition(row, col)) {
					cb.setStatus(SNAKE);
				} else {
					if (fruit.isFruitPosition(row, col)) {
						cb.setStatus(FRUIT);

					} else {
						cb.setStatus(BOARD);
					}
				}
			}
		}
	}

	private GridPane createGamePane() {
		GridPane pane = new GridPane();
		for (int row = 0; row < gridSize; row++) {
			for (int col = 0; col < gridSize; col++) {
				pane.add(new CellButton(), row, col);
			}
		}
		return pane;
	}

	private void snakeEatsFruit() {
		snake.eatFruit(fruit);
		scoreValue.setText(Integer.toString(snake.getScore()));
		fruit.generateRandomPosition();

		// If the player has eaten a certain amount of fruits, the next fruit is a
		// special fruit
		if (snake.getFruitsEaten() % specialFruitFrequency == 0 && snake.getFruitsEaten() != 0) {
			fruit.setValue(specialFruitValue);
		} else {
			fruit.setValue(normalFruitValue);
		}
		timeline.setRate(timeline.getCurrentRate() + increment);
	}

	private void restartGame() {
		snake = new Snake(gridSize);
		scoreValue.setText(Integer.toString(snake.getScore()));
		pressedDir = NORTH;
		fruit.generateRandomPosition();
		startSnakeGame();
	}

	private void createGameOverPane() {
		Stage gameOverStage = new Stage();
		BorderPane gameOverScreen = new BorderPane();
		gameOverScreen.setPrefHeight(500);
		gameOverScreen.setPrefWidth(500);
		Label gameOver = new Label("Game Over!");
		gameOver.setTextFill(Color.BLACK);
		gameOver.setFont(Font.font("Courier New", FontWeight.BOLD, 60));
		gameOverScreen.setCenter(gameOver);
		gameOverScreen.setPadding(new Insets(0, 0, 10, 0));

		Button restart = new Button("New Game");
		Button quit = new Button("Exit Game");
		HBox buttonBoxGameOver = new HBox(3.0);
		buttonBoxGameOver.getChildren().addAll(restart, quit);
		gameOverScreen.setBottom(buttonBoxGameOver);
		buttonBoxGameOver.setAlignment(Pos.BOTTOM_CENTER);

		gameOverStage.setTitle("Game Over!");
		gameOverStage.setScene(new Scene(gameOverScreen, 380, 200));
		gameOverStage.show();

		gameOverStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent we) {
				Platform.exit();
				System.exit(0);
			}
		});

		// Exit Game
		quit.setOnAction((ActionEvent e) -> {
			Platform.exit();
			System.exit(0);
		});

		// New Game
		restart.setOnAction((ActionEvent e) -> {
			gameOverStage.close();
			restartGame();
		});
	}

}
