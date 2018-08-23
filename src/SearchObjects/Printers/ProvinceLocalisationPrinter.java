package SearchObjects.Printers;

import Main.Main;
import Main.View.LogType;
import Model.Map.MapInformationService;
import Model.Map.Region;
import Model.Province;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.Normalizer;

/**
 * Created by Christian on 19-07-2017.
 */
public class ProvinceLocalisationPrinter
{
    private Main main;
    private final String PRINTERNAME = "ProvinceLocalisationPrinter";

    public ProvinceLocalisationPrinter(Main main)
    {
        this.main = main;
    }

    public void printProvinceLocalisationToFiles()
    {
        main.getLogHandler().updateLogTextArea(LogType.PRINTING,"Processing Started: " + PRINTERNAME);

            try
            {
                PrintStream printStream = new PrintStream(new File(main.getFileHandler().getOutputDirectoryPath() + "\\map\\prov_names_l_english.yml"));

                String provinceDefinitionStart = "l_english:";

                printStream.println(provinceDefinitionStart);

                for (Province province : MapInformationService.getInstance().getProvinceList())
                {
                    String provinceLocalisation;

                    provinceLocalisation = " PROV" + province.getID() +": \"" + province.getProvinceName().getValueName() + "\"" ;

                    printStream.println(provinceLocalisation);
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
