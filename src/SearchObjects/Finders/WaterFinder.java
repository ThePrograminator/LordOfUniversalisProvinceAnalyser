package SearchObjects.Finders;

import Model.Map.MapInformationService;
import Model.Map.Water;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Christian on 14-07-2017.
 */
public class WaterFinder implements Finder
{
    private ArrayList<Water> waterList = new ArrayList<>();
    private final String FINDERNAME = "WaterFinder";
    private final String FILENAME = "default.map";
    private int currentWaterId = 0;
    private int lineCount = 0;

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
                lineCount++;
                line = line.replaceAll("\\s+", "");

                if (line.isEmpty())
                    continue;

                if (line.charAt(0) == '#')
                    continue;

                if (line.charAt(0) == '}')
                    continue;

                if (line.contains("sea_starts={"))
                {
                    String currentWater = "sea_starts";
                    Water water = new Water(currentWaterId);
                    water.setKeyName(currentWater);

                    while (scanner.hasNextLine())
                    {
                        String provinceIDLine = scanner.nextLine();
                        lineCount++;

                        if (provinceIDLine.isEmpty())
                            continue;

                        if (provinceIDLine.contains("#"))
                            continue;

                        if (provinceIDLine.contains("}"))
                            break;

                        addToWaterList(water, provinceIDLine);
                    }

                    this.waterList.add(water);

                    currentWaterId++;
                }
                else if (line.contains("lakes={"))
                {
                    String currentWater = "lakes";
                    Water water = new Water(currentWaterId);
                    water.setKeyName(currentWater);

                    while (scanner.hasNextLine())
                    {
                        String provinceIDLine = scanner.nextLine();
                        lineCount++;

                        if (provinceIDLine.isEmpty())
                            continue;

                        if (provinceIDLine.contains("#"))
                            continue;

                        if (provinceIDLine.contains("}"))
                            break;

                        addToWaterList(water, provinceIDLine);
                    }
                    this.waterList.add(water);

                    currentWaterId++;
                }
            }
        }
        catch(FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
            return false;
        }

        for(Water water : this.waterList)
        {
            System.out.println();
            System.out.print("id: " + water.getKeyName() + " => ");
            for (Integer integer : water.getProvinceList())
            {
                System.out.print(String.valueOf(integer) + ",");
            }
            System.out.println();
        }

        MapInformationService.getInstance().setWaterProvinceList(this.waterList);

        return true;
    }

    public void addToWaterList(Water water, String provinceIDLine)
    {
        Scanner scanner = new Scanner(provinceIDLine);

        while (scanner.hasNext())
        {
            try
            {
                int provinceID = scanner.nextInt();

                water.getProvinceList().add(provinceID);
            }
            catch (InputMismatchException ime)
            {
                System.out.println(ime.getMessage() + "Line number in file: " + lineCount);
            }
        }
    }

    @Override
    public String getFinderName() {
        return FINDERNAME;
    }
}
