package SearchObjects;

import Main.Main;
import Main.FileHandler;
import SearchObjects.Finders.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Calculator implements Runnable
{
    private Main main;
    private ArrayList<Finder> finderList = new ArrayList<>();
    private ArrayList<Observer> observersList = new ArrayList<>();

    private String currentFinder;
    private float findersDone = 0;

    private FileHandler fileHandler;
    private LogHandler logHandler;

    public Calculator(Main main)
    {
        this.main = main;
        this.fileHandler = new FileHandler();
        this.finderList.add(new RGBFinder());
        this.finderList.add(new AreaFinder());
        this.finderList.add(new RegionFinder());
        this.finderList.add(new SuperRegionFinder());
        this.finderList.add(new ContinentFinder());
        this.finderList.add(new TradeNodeFinder());

        this.logHandler = new LogHandler(main);
    }

    public void startCalculator()
    {
        getLogHandler().updateLogTextArea("|Processing| Message => Processing Started");
        for (Finder finder : finderList)
        {
            this.currentFinder = finder.getFinderName();
            getLogHandler().updateLogTextArea("|Processing| Message => Currently Loading: " + currentFinder);
            try {
                finder.loadFiles(fileHandler.getDirectoryPath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            findersDone++;
            notifyAllObservers();
        }
        getLogHandler().updateLogTextArea("|Processing| Message => Processing Finished");

    }

    public void attach(Observer observer){
        observersList.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observersList) {
            observer.update();
        }
    }

    public Main getMain() {
        return main;
    }

    public String getCurrentFinder() {
        return currentFinder;
    }

    public void setCurrentFinder(String currentFinder) {
        this.currentFinder = currentFinder;
    }

    public float getFindersDone() {
        return findersDone;
    }

    public void setFindersDone(int findersDone) {
        this.findersDone = findersDone;
    }

    public ArrayList<Finder> getFinderList() {
        return finderList;
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }

    public LogHandler getLogHandler() {
        return logHandler;
    }

    @Override
    public void run()
    {
        startCalculator();
    }
}