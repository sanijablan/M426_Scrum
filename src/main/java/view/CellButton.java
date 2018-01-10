package view;

import static view.CellStatus.BOARD;
import static view.CellStatus.FRUIT;
import static view.CellStatus.SNAKE;
import javafx.scene.control.Button;

public class CellButton extends Button {

    // Status is either snake (blue), fruit (red) or board (white)
    private CellStatus cellStatus;

    private String snakeColor = "-fx-background-color: blue; -fx-border-color: grey; -fx-border-width: 0.5;";
    private String fruitColor = "-fx-background-color: red; -fx-border-color: grey; -fx-border-width: 0.5;";
    private String boardColor = "-fx-background-color: white; -fx-border-color: grey; -fx-border-width: 0.5;";

    private static final int MAXSIZE = 15;

    /**
     * Creates a CellButton with maximum size.
     */
    public CellButton() {
	setMaxSize(MAXSIZE, MAXSIZE);
	setMinSize(MAXSIZE, MAXSIZE);
	cellStatus = BOARD;
	setStyle(boardColor);
    }

    public CellStatus getStatus() {
	return cellStatus;
    }

    /**
     * Sets the status of a cell.
     * 
     * @param status The status of a cell. Can be SNAKE, FRUIT or BOARD
     */
    public void setStatus(CellStatus status) {
	switch (status) {
	case SNAKE:
	    this.setStyle(snakeColor);
	    cellStatus = SNAKE;
	    break;
	case FRUIT:
	    this.setStyle(fruitColor);
	    cellStatus = FRUIT;
	    break;
	case BOARD:
	    this.setStyle(boardColor);
	    cellStatus = BOARD;
	    break;
	default:
	    this.setStyle(boardColor);
	    break;
	}
    }

}
