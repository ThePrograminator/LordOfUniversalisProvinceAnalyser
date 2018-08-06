package SearchObjects.Finders;

import Model.Map.Area;
import Model.Map.MapInformationService;
import Model.RGB;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Christian on 14-07-2017.
 */
public class AreaFinder implements Finder
{
    private ArrayList<Area> areaList = new ArrayList<>();
    private final String FINDERNAME = "AreaFinder";
    private final String FILENAME = "area.txt";
    private int currentAreaId = 0;

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
                {
                    continue;
                }

                if (line.charAt(0) == '#')
                {
                    continue;
                }

                if (line.charAt(0) == '}')
                {
                    continue;
                }

                if (line.contains(" = {"))
                {
                    String[] parts = line.split("=");
                    String part1 = parts[0]; // area name
                    String part2 = parts[1]; // = {

                    String areaName = part1;
                    areaName = areaName.replaceAll("\\s+","");

                    Area area = new Area(currentAreaId);
                    area.setKeyName(areaName);

                    String provinceIDLine = scanner.nextLine();

                    addToAreaMap(area, provinceIDLine);
                    this.areaList.add(area);
                }
            }
        }
        catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
            return false;
        }

        for(Area area : this.areaList)
        {
            System.out.println();
            System.out.print("id: " + area.getKeyName() + " => ");
            for (Integer integer : area.getProvinceList())
            {
                System.out.print(String.valueOf(integer) + ",");
            }
            System.out.println();
        }

        MapInformationService.getInstance().setAreaList(this.areaList);

        return true;
    }

    public void addToAreaMap(Area area, String provinceIDLine)
    {
        Scanner scanner = new Scanner(provinceIDLine);

        while (scanner.hasNext())
        {
            int provinceID = scanner.nextInt();

            area.getProvinceList().add(provinceID);
        }
    }

    @Override
    public String getFinderName() {
        return FINDERNAME;
    }
}
