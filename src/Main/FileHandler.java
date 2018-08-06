package Main;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class FileHandler
{
    private String inputDirectoryPath;
    private String outputDirectoryPath;
    private String InformationDirectoryPath;
    private ArrayList<String> fileNamesList;
    private TreeMap<String, Boolean> fileNamesTreemap = new TreeMap<>();

    public FileHandler()
    {
        this.fileNamesList = new ArrayList<>();
        addDependentFile("definition.csv");
        addDependentFile("area.txt");
        addDependentFile("region.txt");
        addDependentFile("superregion.txt");
        addDependentFile("continent.txt");
        addDependentFile("00_tradenodes.txt");
    }

    public boolean searchForAppropriatedFiles(String chosenDirectory)
    {
        resetFilesNamesMap();
        File directory = new File(chosenDirectory);
        File[] listOfFiles = directory.listFiles();
        for (File file : listOfFiles)
        {
            for(Map.Entry<String,Boolean> entry : fileNamesTreemap.entrySet())
            {
                String key = entry.getKey();

                if(file.getName().equals(key))
                    entry.setValue(true);
            }
        }
        return appropriateFilesFound();
    }

    private boolean appropriateFilesFound()
    {
        for(Map.Entry<String,Boolean> entry : fileNamesTreemap.entrySet())
        {
            String key = entry.getKey();
            boolean value = entry.getValue();

            if (!value)
                return false;
        }
        return true;
    }

    private void resetFilesNamesMap()
    {
        for(Map.Entry<String,Boolean> entry : fileNamesTreemap.entrySet())
        {
            entry.setValue(false);
        }
    }

    public String getInputDirectoryPath() {
        return inputDirectoryPath;
    }

    public void setInputDirectoryPath(String inputDirectoryPath) {
        this.inputDirectoryPath = inputDirectoryPath;
    }

    public String getOutputDirectoryPath() {
        return outputDirectoryPath;
    }

    public void setOutputDirectoryPath(String outputDirectoryPath) {
        this.outputDirectoryPath = outputDirectoryPath;
    }

    public String getInformationDirectoryPath() {
        return InformationDirectoryPath;
    }

    public void setInformationDirectoryPath(String informationDirectoryPath) {
        InformationDirectoryPath = informationDirectoryPath;
    }

    public void addDependentFile(String fileName)
    {
        this.fileNamesTreemap.put(fileName, false);
    }

    public ArrayList<String> getFileNamesList() {
        return fileNamesList;
    }

    public void setFileNamesList(ArrayList<String> fileNamesList) {
        this.fileNamesList = fileNamesList;
    }
}
