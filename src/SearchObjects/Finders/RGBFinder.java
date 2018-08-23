package SearchObjects.Finders;

import Model.Map.MapInformationService;
import Model.RGB;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RGBFinder implements Finder
{
    private ArrayList<RGB> RGBList = new ArrayList<>();
    private final String FINDERNAME = "RGBFinder";
    private final String FILENAME = "definition.csv";

    public boolean loadFiles(String directory) throws FileNotFoundException {
        File file = new File(directory + "\\" + FILENAME);
        System.out.println(directory + FILENAME);

        if (!file.exists())
            throw new FileNotFoundException();

        try
        {
            Scanner fileScanner = new Scanner(file);

            fileScanner.nextLine();

            while (fileScanner.hasNextLine())
            {
                String line = fileScanner.nextLine();

                if (line.isEmpty())
                    continue;

                if (line.charAt(0) == '#')
                    continue;
                //assumed empty
                if (line.charAt(0) == ';')
                    continue;

                scanLine(line);
            }
            fileScanner.close();
        }
        catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
            return false;
        }

        for(RGB rgb : this.RGBList)
        {
            System.out.println();
            System.out.print("id: " + rgb.getProvinceId() + " => " + rgb.toString());
            System.out.println();
        }

        MapInformationService.getInstance().setRgbList(this.RGBList);

        return true;
    }

    public void scanLine(String line)
    {
        if (line.charAt(0) == ';')
            return;

        RGB rgb = new RGB();
        int id = 0;
        int counter = 0;
        String fullToken = "";

        for (int i = 0; i < line.length();i++)
        {
            String token = String.valueOf(line.charAt(i));

            if (token.isEmpty())
                continue;

            if (token.equals(";"))
            {
                switch (counter)
                {
                    case 0:
                    {
                        id = Integer.parseInt(fullToken);
                        counter++;
                        fullToken = "";
                        continue;
                    }
                    case 1:
                    {
                        rgb.setRed(Integer.parseInt(fullToken));
                        counter++;
                        fullToken = "";
                        continue;
                    }
                    case 2:
                    {
                        rgb.setGreen(Integer.parseInt(fullToken));
                        counter++;
                        fullToken = "";
                        continue;
                    }
                    case 3:
                    {
                        rgb.setBlue(Integer.parseInt(fullToken));
                        counter++;
                        rgb.setProvinceId(id);
                        this.getRGBList().add(rgb);
                        return;
                    }
                }
            }

            fullToken += token;
        }
    }

    public ArrayList<RGB> getRGBList() {
        return RGBList;
    }

    @Override
    public String getFinderName() {
        return FINDERNAME;
    }
}
