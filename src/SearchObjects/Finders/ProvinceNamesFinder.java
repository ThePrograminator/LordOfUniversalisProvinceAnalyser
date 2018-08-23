package SearchObjects.Finders;

import Model.Map.Area;
import Model.Map.MapInformationService;
import Model.Province;
import Model.ProvinceInformation.*;
import SearchObjects.ProvinceKeyWordHandler;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Christian on 14-07-2017.
 */
public class ProvinceNamesFinder implements Finder
{
    private final String FINDERNAME = "ProvinceNamesFinder";
    private File[] listOfFiles;
    private ArrayList<ProvinceLangaugeNames> provinceLangaugeNamesList = new ArrayList<>();

    @Override
    public boolean loadFiles(String directory)
    {
        try {
            File folder = new File(directory + "\\province_names");
            listOfFiles = folder.listFiles();
        }
        catch (Exception fnfe)
        {
            fnfe.printStackTrace();
        }

        for (File file : listOfFiles)
        {
            if (!file.isFile())
                continue;

            try
            {
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine())
                {
                    String line = scanner.nextLine();
                    line = line.replaceAll("\\s+", "");

                    if (line.isEmpty())
                        continue;

                    if (line.charAt(0) == '#')
                        continue;

                    if (line.contains("="))
                    {
                        String[] parts = line.split("=");
                        String part1 = parts[0]; // id
                        String part2 = parts[1]; // province name

                        String id = part1;
                        id = id.replaceAll("\\s+","");

                        String provinceName = part2.trim();
                        provinceName = provinceName.replaceAll("\"+", "");

                        ProvinceLangaugeNames provinceLangaugeNames = new ProvinceLangaugeNames();

                        provinceLangaugeNames.setId(Integer.parseInt(id));
                        provinceLangaugeNames.setValue(provinceName);
                        provinceLangaugeNames.setLangauge(file.getName());

                        this.provinceLangaugeNamesList.add(provinceLangaugeNames);
                    }
                }
                scanner.close();
            }
            catch (FileNotFoundException fnfe)
            {
                fnfe.printStackTrace();
            }
        }

        MapInformationService.getInstance().setProvinceLangaugeNames(this.provinceLangaugeNamesList);

        return true;
    }

    @Override
    public String getFinderName() {
        return FINDERNAME;
    }
}
