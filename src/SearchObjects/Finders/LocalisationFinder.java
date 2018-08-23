package SearchObjects.Finders;

import Model.Localisation.Localisation;
import Model.Localisation.LocalisationType;
import Model.Map.*;
import Model.Province;
import Model.ProvinceInformation.*;
import SearchObjects.ProvinceKeyWordHandler;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Christian on 14-07-2017.
 */
public class LocalisationFinder implements Finder
{
    private final String FINDERNAME = "LocalisationFinder";
    private File[] listOfFiles;
    private ArrayList<Localisation> provinceList = new ArrayList<>();
    private ArrayList<Localisation> mapList = new ArrayList<>();
    private ArrayList<Localisation> tradeNodeList = new ArrayList<>();
    //private Localisation currentLocalisation;

    @Override
    public boolean loadFiles(String directory)
    {
        try
        {
            File folder = new File(directory + "\\localisation");
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

            if (file.getName().contains("prov_names_l_english"))
                loadProvNamesLocalisation(file);
            else if (file.getName().contains("map_lotr_l_english"))
                loadMapNamesLocalisation(file);
            else if (file.getName().contains("tradenodes_lotr_l_english"))
                loadTradeNodeLocalisation(file);
        }

        return true;
    }

    public void loadProvNamesLocalisation(File file)
    {
        this.provinceList.clear();

        try
        {
            Scanner scanner = new Scanner(file);

            String language = null;


            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                line = line.trim();

                if (line.isEmpty())
                    continue;

                if (line.charAt(0) == '#')
                    continue;

                if (line.charAt(0) == '}')
                    continue;

                if (line.contains("l_"))
                {
                    line = line.replace(":", "");
                    language = line;
                    language = language.substring(3);
                    continue;
                }

                if(line.contains("PROV"))
                {
                    Localisation currentLocalisation;
                    String[] parts = line.split(":");
                    String partOne = parts[0]; // keyword
                    String partTwo = parts[1]; // value
                    currentLocalisation = new Localisation(this.provinceList.size());
                    currentLocalisation.setLocalisationType(LocalisationType.PROVINCE_LOCALITATION);
                    currentLocalisation.setLanguage(language);

                    partOne = partOne.trim();
                    partOne = partOne.substring(4);

                    currentLocalisation.setKeyName(partOne);

                    line = partTwo.trim();
                    line = line.replaceAll("\"+", "");

                    currentLocalisation.setValueName(line);
                    provinceList.add(currentLocalisation);
                }
            }

            LocalisationService.getInstance().setProvinceLocalisation(this.provinceList);

        }
        catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }
    }

    public void loadMapNamesLocalisation(File file)
    {
        this.mapList.clear();

        try
        {
            Scanner scanner = new Scanner(file);

            String language = null;

            boolean foundLanguage = false;
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                line = line.trim();

                if (line.isEmpty())
                    continue;

                if (line.charAt(0) == '#')
                    continue;

                if (line.contains("l_") && !foundLanguage)
                {
                    line = line.replace(":", "");
                    language = line;
                    language = language.substring(3);
                    foundLanguage = true;
                    continue;
                }

                Localisation currentLocalisation;
                line = line.trim();
                String[] parts = line.split(":");
                String partOne = parts[0]; // keyword
                String partTwo = parts[1]; // value
                if (line.contains("unique"))
                    continue;
                if (line.contains("_name"))
                    continue;
                if (line.contains("_adj"))
                    continue;
                currentLocalisation = new Localisation(this.mapList.size());
                currentLocalisation.setLanguage(language);
                currentLocalisation.setKeyName(partOne);

                line = partTwo.trim();
                line = line.replaceAll("\"+", "");

                currentLocalisation.setValueName(line);

                mapList.add(currentLocalisation);
            }
            scanner.close();

            LocalisationService.getInstance().transferMapList(this.mapList);
        }
        catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }
    }

    public void loadTradeNodeLocalisation(File file)
    {
        this.tradeNodeList.clear();

        try
        {
            Scanner scanner = new Scanner(file);

            String language = null;

            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                line = line.trim();

                if (line.isEmpty())
                    continue;

                if (line.charAt(0) == '#')
                    continue;

                if (line.contains("l_"))
                {
                    line = line.replace(":", "");
                    language = line;
                    language = language.substring(3);
                    continue;
                }

                Localisation currentLocalisation;
                line = line.trim();
                String[] parts = line.split(":");
                String partOne = parts[0]; // keyword
                String partTwo = parts[1]; // value
                currentLocalisation = new Localisation(this.tradeNodeList.size());
                currentLocalisation.setLocalisationType(LocalisationType.TRADENODE_LOCALITATION);
                currentLocalisation.setLanguage(language);
                currentLocalisation.setKeyName(partOne);

                line = partTwo.trim();
                line = line.replaceAll("\"+", "");

                currentLocalisation.setValueName(line);

                tradeNodeList.add(currentLocalisation);
            }

            LocalisationService.getInstance().setTradeNodeLocalisation(this.tradeNodeList);
        }
        catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }
    }

    @Override
    public String getFinderName() {
        return FINDERNAME;
    }
}
