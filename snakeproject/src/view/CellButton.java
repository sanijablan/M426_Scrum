package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class CellButton extends Button {

	// Status is either snake (blue), fruit (red) or board (white)
	private CellStatus cellStatus;

	private String snakeColor = "-fx-background-color: #1E90FF;\n" + "-fx-border-color: grey;\n"
			+ "-fx-border-width: 0.5;";
	private String fruitColor = "-fx-background-color: red;\n" + "-fx-border-color: grey;\n" + "-fx-border-width: 0.5;";
	private String boardColor = "-fx-background-color: white;\n" + "-fx-border-color: grey;\n"
			+ "-fx-border-width: 0.5;";

	private static final int MAXSIZE = 20;

	public CellButton() {
		setMaxSize(MAXSIZE, MAXSIZE);
		setMinSize(MAXSIZE, MAXSIZE);
		cellStatus = CellStatus.BOARD;
		setStyle(boardColor);

		setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// button does nothing
			}
		});
	}

	public void setStatus(CellStatus status) {
		switch (cellStatus) {
		case SNAKE:
			this.setStyle(snakeColor);
			break;
		case FRUIT:
			this.setStyle(fruitColor);
			break;
		case BOARD:
			this.setStyle(boardColor);
			break;
		default:
			this.setStyle(boardColor);
			break;
		}
	}

}