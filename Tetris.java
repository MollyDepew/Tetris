/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The Tetris Application, which contains the board and a message label.
 * @author pipWolfe
 */
public class Tetris extends Application {

    private static final double MILLISEC = 200;
    private TetrisGame game;
    private TetrisBoard tetrisBoard;
    private Timeline animation;
    private Label statusLabel;

    /**
     * Launches the application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Sets up the tetris board and game, as well as a status label
     * that can be used to display scores and messages.
     * 
     * Enables key events for the arrow keys and space bar, as well
     * as an animation.
     * 
     * @param primaryStage
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        tetrisBoard = new TetrisBoard();
        
        statusLabel = new Label("Tetris");
        statusLabel.setTextFill(Color.RED);
        
        BorderPane pane = new BorderPane();
        pane.setCenter(tetrisBoard);
        pane.setTop(statusLabel);

        Scene scene = new Scene(pane);

        game = new TetrisGame(this, tetrisBoard);

        setUpAnimation();

        setUpKeyPresses();

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * Changes the message in the status label at the top of the screen.
     * @param message 
     */
    public void setMessage(String message) {
        statusLabel.setText(message);
    }

    /**
     * Sets up an animation timeline that calls update on the game every
     * MILLISEC milliseconds.
     */
    private void setUpAnimation() {
        // Create a handler
        EventHandler<ActionEvent> eventHandler = (ActionEvent e) -> {
            this.pause();
            game.update();
            this.resume();
        };
        // Create an animation for alternating text
        animation = new Timeline(new KeyFrame(Duration.millis(MILLISEC), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    /**
     * Sets up key events for the arrow keys and space bar. All keys send 
     * messages to the game, which should react appropriately.
     */
    private void setUpKeyPresses() {
        tetrisBoard.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    game.left();
                    break;
                case RIGHT:
                    game.right();
                    break;
                case DOWN:
                    game.rotateLeft();
                    break;
                case UP:
                    game.rotateRight();
                    break;
                case SPACE:
                    game.drop();
                    break;

            }
        });
        tetrisBoard.requestFocus(); // board is focused to receive key input

    }

    /**
     * Pauses the animation.
     */
    private void pause() {
        animation.pause();
    }

    /** 
     * Resumes the animation.
     */
    private void resume() {
        animation.play();
    }

}

