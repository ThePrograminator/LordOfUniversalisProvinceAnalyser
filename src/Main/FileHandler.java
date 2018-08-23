package Main;

import Main.View.LogType;
import Model.Map.Area;
import Model.Map.MapInformationService;
import Model.Map.Region;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class FileHandler
{
    private Main main;
    private String inputDirectoryPath;
    private String outputDirectoryPath;
    private String informationDirectoryPath;
    private ArrayList<String> fileNamesList;
    private TreeMap<String, Boolean> fileNamesTreemap = new TreeMap<>();

    public FileHandler(Main main)
    {
        this.main = main;
        this.fileNamesList = new ArrayList<>();
        addDependentFile("definition.csv");
        addDependentFile("area.txt");
        addDependentFile("region.txt");
        addDependentFile("superregion.txt");
        addDependentFile("continent.txt");
        addDependentFile("00_tradenodes.txt");
        addDependentFile("climate.txt");
        addDependentFile("default.map");
        addDependentFile("prov_names");
        addDependentFile("map_lotr");
        addDependentFile("tradenodes");
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
                else if (key.contains("prov_names"))
                    entry.setValue(true);
                else if (key.contains("map_lotr"))
                    entry.setValue(true);
                else if (key.contains("tradenodes"))
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

    public void loadConfiguration()
    {
        main.getLogHandler().updateLogTextArea(LogType.CONFIGURATION,"Started Loading Configuration File");
        File file = new File("C:\\Users\\Christian\\Desktop\\LordOfUniversalisProvince\\configuration.txt");

        try
        {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                line = line.trim();

                if (line.isEmpty())
                    continue;

                if (line.charAt(0) == '#')
                    continue;

                String[] parts = line.split("=");
                String part1 = parts[0]; // configuration Key
                String part2 = parts[1]; // Value
                part1 = part1.trim();
                part2 = part2.trim();

                if (part1.equals("input-directory"))
                {
                    File folder = new File(part2);
                    File[] listOfFiles = folder.listFiles();

                    if (listOfFiles == null || listOfFiles.length <= 0)
                        continue;

                    this.inputDirectoryPath = part2;
                    this.main.getDirectoryInputLabel().setText(this.inputDirectoryPath);
                }
                else if (part1.equals("output-directory"))
                {
                    this.outputDirectoryPath = part2;
                    this.main.getDirectoryOutputLabel().setText(this.outputDirectoryPath);
                    continue;
                }
                else if (part1.equals("information-directory"))
                {
                    if (searchForAppropriatedFiles(part2))
                    {
                        this.informationDirectoryPath = part2;
                        this.main.getDirectoryInformationLabel().setText(this.informationDirectoryPath);
                        continue;
                    }
                }
            }
        }
        catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }
        main.getLogHandler().updateLogTextArea(LogType.CONFIGURATION,"Loading Configuration File Finished");
    }

    public void printConfiguration()
    {
        try
        {
            PrintStream printStream = new PrintStream(new File("C:\\Users\\Christian\\Desktop\\LordOfUniversalisProvince\\configuration.txt"));
            String result = "";
            if (this.inputDirectoryPath != null)
                result = "input-directory = " + this.inputDirectoryPath + "\n";
            if (this.outputDirectoryPath != null)
                result += "output-directory = " + this.outputDirectoryPath  + "\n";
            if (this.informationDirectoryPath != null)
                result += "information-directory = " + this.informationDirectoryPath  + "\n";

            Scanner scanner = new Scanner(result);

            while (scanner.hasNextLine())
            {
                printStream.println(scanner.nextLine());
            }
        }
        catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }
        main.getLogHandler().updateLogTextArea(LogType.CONFIGURATION,"Saving directories to Configuration File");
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
        return informationDirectoryPath;
    }

    public void setInformationDirectoryPath(String informationDirectoryPath) {
        this.informationDirectoryPath = informationDirectoryPath;
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
