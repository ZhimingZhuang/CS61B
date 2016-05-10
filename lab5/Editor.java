package editor;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Editor extends Application {
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;

    private static final int STARTING_FONT_SIZE = 20;
    private static final int STARTING_TEXT_POSITION_X = 0;
    private static final int STARTING_TEXT_POSITION_Y = 0;

    private Text displayText;
    private int fontSize = STARTING_FONT_SIZE;
    private String fontName = "Helvetica";
    private String content = new String();

    private class KeyEventHandler implements EventHandler<KeyEvent> {
        KeyEventHandler(final Group root, int windowWidth, int windowHeight) {
            // Initialize some empty text and add it to root so that it will be displayed.
            displayText = new Text(STARTING_TEXT_POSITION_X, STARTING_TEXT_POSITION_Y, "");

            // Always set the text origin to be VPos.TOP! Setting the origin to be VPos.TOP means
            // that when the text is assigned a y-position, that position corresponds to the
            // highest position across all letters (for example, the top of a letter like "I", as
            // opposed to the top of a letter like "e"), which makes calculating positions much
            // simpler!
            displayText.setTextOrigin(VPos.TOP);
            displayText.setFont(Font.font(fontName, fontSize));

            // All new Nodes need to be added to the root in order to be displayed.
            root.getChildren().add(displayText);

        }

        @Override
        public void handle(KeyEvent keyEvent) {
            if(keyEvent.getEventType() == KeyEvent.KEY_TYPED) {
                // Use the KEY_TYPED event rather than KEY_PRESSED for letter keys, because with
                // the KEY_TYPED event, javafx handles the "Shift" key and associated
                // capitalization.
                String characterTyped = keyEvent.getCharacter();
                content += characterTyped;
                if(characterTyped.length() > 0 && characterTyped.charAt(0) != 8) {
                    // Ignore control keys, which have non-zero length, as well as the backspace
                    // key, which is represented as a character of value = 8 on Windows.
                    displayText.setText(content);
                    keyEvent.consume();
                }
                topleftText();
            }else if(keyEvent.getEventType() == keyEvent.KEY_PRESSED) {
                KeyCode code = keyEvent.getCode();
                if(code == KeyCode.BACK_SPACE) {
                    if(content.length() > 0) {
                        content = content.substring(0, content.length() - 1);
                        displayText.setText(content);
                    }
                    topleftText();
                }
            }
        }

        private void topleftText() {
            double textLeft = (double) STARTING_TEXT_POSITION_X;
            double textTop = (double) STARTING_TEXT_POSITION_Y;

            displayText.setX(textLeft);
            displayText.setY(textTop);

            displayText.toFront();
        }


    }
    @Override
    public void start(Stage primaryStage) {
        // Create a Node that will be the parent of all things displayed on the screen.
        Group root = new Group();

        // The Scene represents the window: its height and width will be the height and width
        // of the window displayed.
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);

        // To get information about what keys the user is pressing, create an EventHandler.
        // EventHandler subclasses must override the "handle" function, which will be called
        // by javafx.
        EventHandler<KeyEvent> keyEventEventHandler = new KeyEventHandler(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        // Register the event handler to be called for all KEY_PRESSED and KEY_TYPED events.
        scene.setOnKeyTyped(keyEventEventHandler);
        scene.setOnKeyPressed(keyEventEventHandler);

        primaryStage.setTitle("Simple Typing");
        // This is boilerplate, necessary to setup the window where things are displayed.
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}