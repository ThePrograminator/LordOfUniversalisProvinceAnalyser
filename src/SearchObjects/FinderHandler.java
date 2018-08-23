package SearchObjects;

import Main.Main;
import Main.FileHandler;
import Main.View.LogHandler;
import Main.View.LogType;
import Main.View.Observer;
import SearchObjects.Finders.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FinderHandler implements Runnable
{
    private Main main;
    private ArrayList<Finder> finderList = new ArrayList<>();
    private ArrayList<Observer> observersList = new ArrayList<>();

    private String currentFinder;
    private float findersDone = 0;

    private ProvinceFinder provinceFinder;

    public FinderHandler(Main main)
    {
        this.main = main;
        this.finderList.add(new RGBFinder());
        this.finderList.add(new AreaFinder());
        this.finderList.add(new RegionFinder());
        this.finderList.add(new SuperRegionFinder());
        this.finderList.add(new ContinentFinder());
        this.finderList.add(new TradeNodeFinder());
        this.finderList.add(new ClimateFinder());
        this.finderList.add(new WaterFinder());
        this.finderList.add(new BuildingFinder());
        this.finderList.add(new LocalisationFinder());
        this.finderList.add(new ProvinceNamesFinder());
    }

    public void startCalculator()
    {
        main.getLogHandler().updateLogTextArea(LogType.ANALYSING,"Analysing Started");
        for (Finder finder : finderList)
        {
            this.currentFinder = finder.getFinderName();
            main.getLogHandler().updateLogTextArea(LogType.ANALYSING,"Currently Analysing: " + currentFinder);
            try {
                finder.loadFiles(main.getFileHandler().getInformationDirectoryPath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            findersDone++;
            notifyAllObservers();
        }
        main.getLogHandler().updateLogTextArea(LogType.ANALYSING,"Analysing Finished");
        main.setFilesLoaded(true);
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

    @Override
    public void run()
    {
        this.provinceFinder = new ProvinceFinder(main.getFileHandler().getInputDirectoryPath());
        this.finderList.add(provinceFinder);
        startCalculator();
    }
}