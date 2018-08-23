package Main.View;

import Main.Main;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class LogHandler
{
    private Main main;

    public LogHandler(Main main)
    {
        this.main = main;
    }

    public synchronized void updateLogTextArea(LogType logType, String message)
    {
        String logtypeSTR = logType.name();
        String capLetter = logtypeSTR.substring(0,1).toUpperCase();
        logtypeSTR = capLetter + logtypeSTR.substring(1).toLowerCase();
        String result = "|" + logtypeSTR + "| Message => " + message;

        Platform.runLater(() ->
        {
            TextArea textArea =  main.getLogTextArea();
            textArea.setText(textArea.getText() + result + "\n");
            textArea.selectPositionCaret( textArea.getLength());
            textArea.deselect();
        });
    }
}
