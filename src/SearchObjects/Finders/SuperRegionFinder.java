package SearchObjects.Finders;

import Model.Map.MapInformationService;
import Model.Map.Region;
import Model.Map.SuperRegion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Christian on 14-07-2017.
 */
public class SuperRegionFinder implements Finder
{
    private ArrayList<SuperRegion> superRegionList = new ArrayList<>();
    private final String FINDERNAME = "SuperRegionFinder";
    private final String FILENAME = "superregion.txt";
    private int currentSuperRegionId = 0;

    @Override
    public boolean loadFiles(String directory) throws FileNotFoundException
    {
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
                line = line.replaceAll("\\s+","");

                if (line.isEmpty())
                    continue;

                if (line.charAt(0) == '#')
                    continue;

                if (line.charAt(0) == '}')
                    continue;

                if (line.contains("superregion={"))
                {
                    String[] parts = line.split("=");
                    String part1 = parts[0]; // SuperRegion name
                    String part2 = parts[1]; // = {

                    String superRegionName = part1;
                    superRegionName = superRegionName.replaceAll("\\s+","");

                    SuperRegion superRegion = new SuperRegion(currentSuperRegionId);
                    superRegion.setKeyName(superRegionName);

                    while (true)
                    {
                        String regionLine = scanner.nextLine();
                        regionLine = regionLine.replace("\t", "");
                        regionLine = regionLine.replaceAll("\\s+","");

                        if (regionLine.isEmpty())
                            continue;

                        if (regionLine.contains("#"))
                            continue;

                        if(regionLine.contains("}"))
                            break;

                        for (Region region : MapInformationService.getInstance().getRegionList())
                        {
                            if(region.getKeyName().equals(regionLine))
                            {
                                superRegion.getRegionList().add(region);
                                break;
                            }
                        }
                    }
                    this.superRegionList.add(superRegion);

                    currentSuperRegionId++;
                }
            }
        }
        catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
            return false;
        }

        for(SuperRegion superRegion : this.superRegionList)
        {
            System.out.println();
            System.out.print("id: " + superRegion.getKeyName() + " => ");
            for (Region region : superRegion.getRegionList())
            {
                System.out.print(region.getKeyName() + ",");
            }
            System.out.println();
        }

        MapInformationService.getInstance().setSuperRegionList(this.superRegionList);

        return true;
    }

    @Override
    public String getFinderName() {
        return FINDERNAME;
    }
}
