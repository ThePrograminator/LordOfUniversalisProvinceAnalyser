package SearchObjects;

import Main.Main;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class LogHandler
{
    private Main main;

    public LogHandler(Main main){
        this.main = main;
    }

    public synchronized void updateLogTextArea(String message)
    {
        Platform.runLater(() ->
        {
            TextArea textArea =  main.getLogTextArea();
            textArea.setText(textArea.getText() + message + "\n");
            textArea.selectPositionCaret( textArea.getLength());
            textArea.deselect();
        });
    }
}
