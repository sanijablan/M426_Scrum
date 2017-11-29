package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Snake;

public class GUI extends Application {

	private Button btnPlay;
	private Button btnReset;

	private HBox buttonBox;
	private GridPane gamePane;

	private final int gridSize = 50;

	private Snake snake;

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Snake");

		FlowPane root = new FlowPane(10, 10);
		root.setAlignment(Pos.BOTTOM_CENTER);

		gamePane = createGamePane();

		primaryStage.setScene(new Scene(root));

		btnPlay = new Button("Play");
		btnReset = new Button("Reset");

		btnPlay.setMinWidth(60);
		btnReset.setMinWidth(60);

		buttonBox = new HBox(3.0);
		buttonBox.getChildren().addAll(btnPlay, btnReset);

		btnPlay.setOnAction(event -> {
			startSnakeGame();
		});

		btnReset.setOnAction(event -> {

		});

		root.getChildren().addAll(gamePane, buttonBox);
		primaryStage.show();
	}

	private void startSnakeGame() {
		// TODO Auto-generated method stub

	}

	private int calcIndex(int row, int col) {
		return row * gridSize + col;
	}

	private void repaintPane() {
		for (int row = 0; row < gridSize; row++) {
			for (int col = 0; col < gridSize; col++) {
				if (snake.isSnakePosition(row, col)) {
					// TODO set color of snake
					gamePane.getChildren().get(calcIndex(row, col));
				} else {
					// TODO paint to board or fruit
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
}
