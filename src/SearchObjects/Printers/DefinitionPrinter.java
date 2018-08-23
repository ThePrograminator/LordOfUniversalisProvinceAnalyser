package SearchObjects.Printers;

import Main.Main;
import Main.View.LogType;
import Model.Map.Area;
import Model.Map.MapInformationService;
import Model.Map.Region;
import Model.Province;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.Normalizer;
import java.util.Scanner;

/**
 * Created by Christian on 19-07-2017.
 */
public class DefinitionPrinter
{
    private Main main;
    private final String PRINTERNAME = "DefinitionPrinter";

    public DefinitionPrinter(Main main)
    {
        this.main = main;
    }

    public void printDefinitionToFiles()
    {
        main.getLogHandler().updateLogTextArea(LogType.PRINTING,"Processing Started: " + PRINTERNAME);

            try
            {
                PrintStream printStream = new PrintStream(new File(main.getFileHandler().getOutputDirectoryPath() + "\\map\\definition.csv"));

                String provinceDefinitionStart = "province" + ";" + "red" + ";" + "green" + ";" + "blue" + ";" + "x" + ";" + "x";

                printStream.println(provinceDefinitionStart);

                for (Province province : MapInformationService.getInstance().getProvinceList())
                {
                    String fileName = province.getProvinceName().getValueName();
                    String fileNameFixed;
                    if (fileName == null || fileName.isEmpty())
                    {
                        fileNameFixed = "NULL";
                    }
                    else
                    {
                        fileNameFixed = Normalizer
                                .normalize(fileName, Normalizer.Form.NFD)
                                .replaceAll("[^\\p{ASCII}]", "");
                    }

                    String provinceDefinition;
                    if (province.getRgb() == null)
                        provinceDefinition = province.getID() + ";" + ";" + ";" + ";" + ";";
                    else
                        provinceDefinition = province.getID() + ";" + province.getRgb().getRed() + ";" + province.getRgb().getGreen() + ";" + province.getRgb().getBlue() + ";" + fileNameFixed + ";" + "x";

                    printStream.println(provinceDefinition);
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
