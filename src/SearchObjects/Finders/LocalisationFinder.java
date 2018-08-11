package SearchObjects.Finders;

import Model.Localisation.Localisation;
import Model.Localisation.LocalisationType;
import Model.Map.MapInformationService;
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
    private ArrayList<Localisation> localisationList = new ArrayList<>();
    private Localisation currentLocalisation;

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

            if (file.getName().contains("prov_names"))
                loadProvNamesLocalisation(file);


        }

        //MapInformationService.getInstance().transferProvinceList(this.provinceList);

        return true;
    }

    public void loadProvNamesLocalisation(File file)
    {
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
                    String[] parts = line.split(":");
                    String partOne = parts[0]; // keyword
                    String partTwo = parts[1]; // value
                    currentLocalisation = new Localisation(this.localisationList.size());
                    currentLocalisation.setLocalisationType(LocalisationType.PROVINCE_LOCALITATION);
                    currentLocalisation.setLanguage(language);
                    currentLocalisation.setKeyName(partOne);

                    line = partTwo.trim();
                    line = line.replaceAll("\"+", "");

                    currentLocalisation.setValueName(line);
                    localisationList.add(currentLocalisation);
                }
            }
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
