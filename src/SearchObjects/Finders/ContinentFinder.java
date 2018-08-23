package SearchObjects.Finders;

import Model.Map.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Christian on 14-07-2017.
 */
public class ContinentFinder implements Finder
{
    private ArrayList<Continent> continentList = new ArrayList<>();
    private final String FINDERNAME = "ContinentFinder";
    private final String FILENAME = "continent.txt";
    private int currentContinentId = 0;

    @Override
    public boolean loadFiles(String directory) throws FileNotFoundException {
        File file = new File(directory + "\\" + FILENAME);
        System.out.println(directory + FILENAME);

        if (!file.exists())
            throw new FileNotFoundException();

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

                if (line.charAt(0) == '}')
                    continue;

                if (line.contains("={"))
                {
                    String[] parts = line.split("=");
                    String part1 = parts[0]; // Continent name
                    String part2 = parts[1]; // = {

                    String continentName = part1;
                    continentName = continentName.replaceAll("\\s+", "");

                    Continent continent = new Continent(currentContinentId);
                    continent.setKeyName(continentName);

                    while (scanner.hasNextLine())
                    {
                        String provinceIDLine = scanner.nextLine();

                        if (provinceIDLine.isEmpty())
                            continue;

                        if (provinceIDLine.contains("#"))
                            continue;

                        if (provinceIDLine.contains("}"))
                            break;

                        addToContinentList(continent, provinceIDLine);
                    }

                    this.continentList.add(continent);

                    currentContinentId++;
                }
            }
            scanner.close();
        }
        catch(FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
            return false;
        }

        for(Continent continent : this.continentList)
        {
            System.out.println();
            System.out.print("id: " + continent.getKeyName() + " => ");
            for (Integer integer : continent.getProvinceList())
            {
                System.out.print(String.valueOf(integer) + ",");
            }
            System.out.println();
        }

        MapInformationService.getInstance().setContinentList(this.continentList);

        return true;
    }

    public void addToContinentList(Continent continent, String provinceIDLine)
    {
        Scanner scanner = new Scanner(provinceIDLine);

        while (scanner.hasNext())
        {
            int provinceID = scanner.nextInt();

            continent.getProvinceList().add(provinceID);
        }
    }

    @Override
    public String getFinderName() {
        return FINDERNAME;
    }
}
