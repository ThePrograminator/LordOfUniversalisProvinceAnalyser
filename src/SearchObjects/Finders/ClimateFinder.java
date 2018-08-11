package SearchObjects.Finders;

import Model.Map.Climate;
import Model.Map.MapInformationService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Christian on 14-07-2017.
 */
public class ClimateFinder implements Finder
{
    private ArrayList<Climate> climateList = new ArrayList<>();
    private final String FINDERNAME = "ClimateFinder";
    private final String FILENAME = "climate.txt";
    private int currentClimateId = 0;
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

                if (line.contains("={"))
                {
                    String[] parts = line.split("=");
                    String part1 = parts[0]; // Climate name
                    String part2 = parts[1]; // = {

                    String climateName = part1;
                    climateName = climateName.replaceAll("\\s+", "");

                    Climate climate = new Climate(currentClimateId);
                    climate.setKeyName(climateName);

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

                        addToClimateList(climate, provinceIDLine);
                    }

                    this.climateList.add(climate);

                    currentClimateId++;
                }
            }
        }
        catch(FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
            return false;
        }

        for(Climate climate : this.climateList)
        {
            System.out.println();
            System.out.print("id: " + climate.getKeyName() + " => ");
            for (Integer integer : climate.getProvinceList())
            {
                System.out.print(String.valueOf(integer) + ",");
            }
            System.out.println();
        }

        MapInformationService.getInstance().setClimateList(this.climateList);

        return true;
    }

    public void addToClimateList(Climate climate, String provinceIDLine)
    {
        Scanner scanner = new Scanner(provinceIDLine);

        while (scanner.hasNext())
        {
            try
            {
                int provinceID = scanner.nextInt();

                climate.getProvinceList().add(provinceID);
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
