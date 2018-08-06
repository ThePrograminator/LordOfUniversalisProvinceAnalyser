package SearchObjects;

import Main.Main;
import javafx.application.Platform;

public class LoadingBarObserver extends Observer
{
    public LoadingBarObserver(Calculator calculator){
        this.calculator = calculator;
        this.calculator.attach(this);
    }
    @Override
    public void update() {
        updateLoadingBar();
    }

    public synchronized void updateLoadingBar()
    {
        Main instance = this.calculator.getMain();

        double progressValue = 0;
        if (this.calculator.getFindersDone() != this.calculator.getFinderList().size())
        {
            double percentageValue = this.calculator.getFindersDone()/ this.calculator.getFinderList().size();
            //progressValue = instance.getProgressValue() + percentageValue;
            progressValue = percentageValue;
        }
        else
        {
            progressValue = 1.0;
        }

        instance.setProgressValue(progressValue);

        Platform.runLater(() -> instance.getFinderLoadingLabel().setText(calculator.getCurrentFinder()));

        instance.getPb().setProgress(progressValue);
        instance.getPi().setProgress(progressValue);

        this.calculator.getLogHandler().updateLogTextArea("|Processing| Message => Subprocess done: " + calculator.getCurrentFinder() + ", the total progress done: " + (progressValue * 100.0));
    }
}
