package Main.View;

import Main.Main;
import SearchObjects.FinderHandler;
import javafx.application.Platform;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoadingBarObserver extends Observer
{
    public LoadingBarObserver(FinderHandler finderHandler){
        this.finderHandler = finderHandler;
        this.finderHandler.attach(this);
    }
    @Override
    public void update() {
        updateLoadingBar();
    }

    public synchronized void updateLoadingBar()
    {
        Main instance = this.finderHandler.getMain();

        double progressValue = 0;
        if (this.finderHandler.getFindersDone() != this.finderHandler.getFinderList().size())
        {
            double percentageValue = this.finderHandler.getFindersDone()/ this.finderHandler.getFinderList().size();
            //progressValue = instance.getProgressValue() + percentageValue;
            progressValue = percentageValue;
        }
        else
        {
            progressValue = 1.0;
        }

        instance.setProgressValue(progressValue);

        Platform.runLater(() -> instance.getFinderLoadingLabel().setText(finderHandler.getCurrentFinder()));

        instance.getPb().setProgress(progressValue);
        instance.getPi().setProgress(progressValue);

        String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());

        instance.getLogHandler().updateLogTextArea(LogType.ANALYSING,"Subprocess done: " + finderHandler.getCurrentFinder() + ", the total progress done: " + (progressValue * 100.0) + " , Time Finished: " + timeStamp );
    }
}
