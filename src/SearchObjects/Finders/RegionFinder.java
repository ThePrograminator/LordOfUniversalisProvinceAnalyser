package SearchObjects.Finders;

import Model.Map.Area;
import Model.Map.MapInformationService;
import Model.Map.Region;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Christian on 14-07-2017.
 */
public class RegionFinder implements Finder
{
    private ArrayList<Region> regionList = new ArrayList<>();
    private final String FINDERNAME = "RegionFinder";
    private final String FILENAME = "region.txt";
    private int currentRegionId = 0;

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

                if (line.isEmpty())
                    continue;

                if (line.charAt(0) == '#')
                    continue;

                if (line.charAt(0) == '}')
                    continue;

                if (line.contains("region = {"))
                {
                    String[] parts = line.split("=");
                    String part1 = parts[0]; // region name
                    String part2 = parts[1]; // = {

                    String regionName = part1;
                    regionName = regionName.replaceAll("\\s+","");

                    Region region = new Region(currentRegionId);
                    region.setKeyName(regionName);

                    while (true)
                    {
                        String areaLine = scanner.nextLine();
                        areaLine = areaLine.replace("\t", "");
                        areaLine = areaLine.replaceAll("\\s+","");

                        if (areaLine.isEmpty())
                            continue;

                        if (areaLine.contains("#"))
                            continue;

                        if (areaLine.equals("areas={"))
                            continue;

                        if(areaLine.contains("}"))
                            break;

                        for (Area area : MapInformationService.getInstance().getAreaList())
                        {
                            if(area.getKeyName().equals(areaLine))
                            {
                                region.getAreaList().add(area);
                                area.setRegion(region);
                                break;
                            }
                        }
                    }
                    this.regionList.add(region);

                    currentRegionId++;
                }
            }
            scanner.close();
        }
        catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
            return false;
        }

        for(Region region : this.regionList)
        {
            System.out.println();
            System.out.print("id: " + region.getKeyName() + " => ");
            for (Area area : region.getAreaList())
            {
                System.out.print(area.getKeyName() + ",");
            }
            System.out.println();
        }

        MapInformationService.getInstance().setRegionList(this.regionList);

        return true;
    }

    @Override
    public String getFinderName() {
        return FINDERNAME;
    }
}
