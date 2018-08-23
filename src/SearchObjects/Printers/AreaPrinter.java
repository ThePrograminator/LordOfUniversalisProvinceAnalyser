package SearchObjects.Printers;

import Main.Main;
import Main.View.LogType;
import Model.Map.Area;
import Model.Map.MapInformationService;
import Model.Map.Region;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Christian on 19-07-2017.
 */
public class AreaPrinter
{
    private Main main;
    private final String PRINTERNAME = "AreaPrinter";

    public AreaPrinter(Main main)
    {
        this.main = main;
    }

    public void printAreasToFiles()
    {
        main.getLogHandler().updateLogTextArea(LogType.PRINTING,"Processing Started: " + PRINTERNAME);

            try
            {
                PrintStream printStream = new PrintStream(new File(main.getFileHandler().getOutputDirectoryPath() + "\\map\\area.txt"));

                for (Region region : MapInformationService.getInstance().getRegionList())
                {
                    printStream.println(createRegionComment(region));

                    for (Area area : region.getAreaList())
                    {
                        String provinceFile = area.toString();

                        Scanner scanner = new Scanner(provinceFile);

                        while (scanner.hasNextLine())
                        {
                            printStream.println(scanner.nextLine());
                        }
                        scanner.close();
                    }
                }

            }
            catch (FileNotFoundException fnfe)
            {
                fnfe.printStackTrace();
                main.getLogHandler().updateLogTextArea(LogType.ERROR,"File Not Found: area.txt");
            }
        main.getLogHandler().updateLogTextArea(LogType.PRINTING,"Processing Finished: " + PRINTERNAME);
    }

    public String createRegionComment(Region region)
    {
        String commentString = "#---------------------------" + "\n";
        if (region != null && region.getSuperRegion() != null)
            if (region.getValueName() != null && region.getSuperRegion().getValueName() != null)
                commentString += "# " + region.getValueName() + " (" + region.getSuperRegion().getValueName() + ")\n";
            else
                commentString += "# " + region.getValueName() + "\n";

        commentString += "#---------------------------" + "\n";
        return commentString;
    }
}
