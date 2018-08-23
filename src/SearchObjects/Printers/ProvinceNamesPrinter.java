package SearchObjects.Printers;

import Main.Main;
import Main.View.LogType;
import Model.Map.Area;
import Model.Map.MapInformationService;
import Model.Map.Region;
import Model.Province;
import Model.ProvinceInformation.ProvinceLangaugeNames;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Christian on 19-07-2017.
 */
public class ProvinceNamesPrinter
{
    private Main main;
    private final String PRINTERNAME = "ProvinceNamesPrinter";

    public ProvinceNamesPrinter(Main main)
    {
        this.main = main;
    }
    private TreeMap<String, ProvinceLangaugeNames> list = new TreeMap<>();
    private TreeMap<String, String> langaugemap = new TreeMap<>();

    public void printNamesToFiles()
    {
        main.getLogHandler().updateLogTextArea(LogType.PRINTING,"Processing Started: " + PRINTERNAME);

        for (Province province : MapInformationService.getInstance().getProvinceList())
        {
            if (province.getProvinceName() != null)
            {
                if (province.getProvinceName().getProvinceLangaugeNamesList() != null || !province.getProvinceName().getProvinceLangaugeNamesList().isEmpty())
                {
                    for (ProvinceLangaugeNames provinceLangaugeNames : province.getProvinceName().getProvinceLangaugeNamesList())
                    {
                        String line = provinceLangaugeNames.getId() + " = \"" + provinceLangaugeNames.getValue() + "\"" + "\n";
                        if (langaugemap.get(provinceLangaugeNames.getLangauge()) == null)
                            langaugemap.put(provinceLangaugeNames.getLangauge(), "" + line);
                        else
                            langaugemap.put(provinceLangaugeNames.getLangauge(), langaugemap.get(provinceLangaugeNames.getLangauge()) + line);
                        //list.put(provinceLangaugeNames.getLangauge(), provinceLangaugeNames);
                    }
                }
            }
        }

        for(Map.Entry<String,String> entry : langaugemap.entrySet())
        {
            String key = entry.getKey();
            String value = entry.getValue();

            System.out.println(key + " => " + value);
            try
            {
                PrintStream printStream = new PrintStream(new File(main.getFileHandler().getOutputDirectoryPath() + "\\province_names\\" + key + ".txt"));

                printStream.println(value);

            }
            catch (FileNotFoundException fnfe)
            {
                fnfe.printStackTrace();
                main.getLogHandler().updateLogTextArea(LogType.ERROR,"File Not Found: area.txt");
            }
        }

        main.getLogHandler().updateLogTextArea(LogType.PRINTING,"Processing Finished: " + PRINTERNAME);
    }
}
