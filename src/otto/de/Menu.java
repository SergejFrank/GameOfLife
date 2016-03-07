package otto.de;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.text.DecimalFormat;

public class Menu extends Pane {
    Label gameStatus = new Label();
    private final Board board;
    private final Slider slider;
    private final int MIN = 55;
    private final int MAX = 1000;
    private final DecimalFormat df = new DecimalFormat( "#,##0.00" );

    public Menu(Board board){
        super();
        this.board = board;
        this.slider = getSlider();

        Label sliderLabel = new Label("Speed:");

        this.getChildren().add(gameStatus);
        this.getChildren().add(sliderLabel);
        this.getChildren().add(slider);

        gameStatus.setTranslateX(10);
        gameStatus.setTranslateY(5);

        sliderLabel.setTranslateX(10);
        sliderLabel.setTranslateY(40);
        sliderLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        slider.setTranslateY(30);
        slider.setTranslateX(5);
        slider.setValue(MAX-board.getDelay()+MIN);
        update();
    }


    public void update(){
        updateGameStatus();
    }

    private void updateGameStatus(){
        gameStatus.setText(board.getGameStatus().toString());
        gameStatus.setTextFill(Color.web(board.getGameStatus().getColor()));
    }


    private Slider getSlider(){
        Slider slider = new Slider();
        slider.setPrefSize(90,70);
        slider.setMin(MIN);
        slider.setMax(MAX);
        slider.setOrientation(javafx.geometry.Orientation.HORIZONTAL);

        slider.valueProperty().addListener((ov, old_val, new_val) -> {
            board.setDelay(MAX-new_val.intValue()+MIN);
        });

        return slider;
    }
}
