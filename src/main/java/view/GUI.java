package view;

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
import javafx.scene.input.KeyCode;
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

@SuppressWarnings({ "unchecked", "rawtypes" })
public class GUI extends Application {

	private Button btnPlay;
	private Button btnPause;
	private boolean paused;

	private HBox buttonBox;
	private HBox scoreBox;
	private GridPane gamePane;

	private final int gridSize = 30;
	private Timeline timeline = new Timeline();
	private Direction pressedDir;
	private Snake snake;
	private boolean hasGameStarted = false;
	private Fruit fruit;

	private double speed = 250;
	private double increment = 0.1;

	private Label descScore;
	private Label textScore;
	private int score;

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Snake");
		snake = new Snake(gridSize);
		fruit = new Fruit(snake);

		FlowPane root = new FlowPane(10, 10);
		root.setAlignment(Pos.BOTTOM_CENTER);

		gamePane = createGamePane();

		primaryStage.setScene(new Scene(root));

		score = snake.getScore();
		descScore = new Label("Score: ");
		descScore.setMinWidth(60);
		descScore.setMinHeight(20);
		textScore = new Label();
		textScore.setText(Integer.toString(score));
		textScore.setMinWidth(60);
		textScore.setMinHeight(20);

		btnPlay = new Button("Start");
		btnPause = new Button("II");

		btnPlay.setMinWidth(60);
		btnPause.setMinWidth(60);

		scoreBox = new HBox(3.0);
		scoreBox.getChildren().addAll(descScore, textScore);

		buttonBox = new HBox(3.0);
		buttonBox.getChildren().addAll(btnPlay, btnPause);

		btnPlay.setOnAction(event -> {
			if (!hasGameStarted) {
				fruit.generateRandomPosition();
				startSnakeGame();
			}
		});

		btnPause.setOnAction(event -> {
			if (paused == false) {
				timeline.pause();
				paused = true;
				btnPause.setText(">");
			} else {
				timeline.play();
				btnPause.setText("II");
				paused = false;
			}

		});

		root.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.RIGHT) {
					pressedDir = EAST;
				}
				if (event.getCode() == KeyCode.LEFT) {
					pressedDir = WEST;
				}
				if (event.getCode() == KeyCode.UP) {
					pressedDir = NORTH;
				}
				if (event.getCode() == KeyCode.DOWN) {
					pressedDir = SOUTH;
				}
			}
		});

		root.getChildren().addAll(scoreBox, gamePane, buttonBox);
		primaryStage.show();
	}

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
					snake.eatFruit();
					score = snake.getScore();
					textScore.setText(Integer.toString(score));
					fruit.generateRandomPosition();
					timeline.setRate(timeline.getCurrentRate() + increment);
				}
				if (snake.isGameOver()) {
					timeline.stop();
					Stage gameOverStage = new Stage();
					gameOverStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
						@Override
						public void handle(WindowEvent we) {
							Platform.exit();
							System.exit(0);
						}
					});
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

					// Exit Game
					quit.setOnAction((ActionEvent e) -> {
						Platform.exit();
						System.exit(0);
					});

					// New Game
					restart.setOnAction((ActionEvent e) -> {
						gameOverStage.close();
						snake = new Snake(gridSize);
						score = snake.getScore();
						textScore.setText(Integer.toString(score));
						pressedDir = NORTH;
						fruit.generateRandomPosition();
						startSnakeGame();
					});

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

	/**
	 * For testing purposes prints the game pane to System.out.
	 */
	private void printGamePane() {
		for (int row = 0; row < gridSize; row++) {
			for (int col = 0; col < gridSize; col++) {
				if (((CellButton) gamePane.getChildren().get(calcIndex(row, col))).getStatus().equals(SNAKE)) {
					System.out.print("s");
				} else {
					System.out.print("o");
				}
			}
			System.out.println();
		}
		System.out.println("end");
	}

}
