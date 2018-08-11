package SearchObjects.Finders;

import Model.ProvinceInformation.Building;
import Model.Map.MapInformationService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Christian on 14-07-2017.
 */
public class BuildingFinder implements Finder
{
    private ArrayList<Building> buildingList = new ArrayList<>();
    private final String FINDERNAME = "BuildingFinder";
    private int currentBuildingId = 0;
    private File[] listOfFiles;

    @Override
    public boolean loadFiles(String directory)
    {
        try {
            File folder = new File(directory + "\\" + "buildings");
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

                    if (line.charAt(0) == '}')
                        continue;

                    if (line.contains("building={"))
                    {
                        String[] parts = line.split("=");
                        String part1 = parts[0]; // building name
                        String part2 = parts[1]; // = {

                        String buildingName = part1;
                        buildingName = buildingName.replaceAll("\\s+","");

                        Building building = new Building(currentBuildingId);
                        building.setBuildingName(buildingName);

                        this.buildingList.add(building);
                        currentBuildingId++;
                    }
                }
            }
            catch (FileNotFoundException fnfe)
            {
                fnfe.printStackTrace();
            }
        }

        for(Building building : this.buildingList)
        {
            System.out.println("id: " + building.getBuildingName());
        }

        MapInformationService.getInstance().setBuildingList(this.buildingList);

        return true;
    }

    @Override
    public String getFinderName() {
        return FINDERNAME;
    }
}
