package SearchObjects;

import Main.Main;
import javafx.application.Platform;

public class LogHandler
{
    private Main main;

    public LogHandler(Main main){
        this.main = main;
    }

    public synchronized void updateLogTextArea(String message)
    {
        Platform.runLater(() -> main.getLogTextArea().setText(main.getLogTextArea().getText() + message + "\n"));
    }
}
