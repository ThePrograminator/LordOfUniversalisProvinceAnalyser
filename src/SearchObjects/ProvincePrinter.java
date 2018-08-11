package SearchObjects;

import Main.Main;
import Main.View.LogType;
import Model.Province;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Christian on 19-07-2017.
 */
public class ProvincePrinter
{
    private Main main;

    public ProvincePrinter(Main main)
    {
        this.main = main;
    }

    public void printProvincesToFiles(ArrayList<Province> provinceList)
    {
        main.getLogHandler().updateLogTextArea(LogType.PRINTING,"Processing Started");
        for (Province province : provinceList)
        {
            String fileName = province.getID() + " - " + province.getProvinceName().getKeyName();

            try
            {
                PrintStream printStream = new PrintStream(new File(main.getFileHandler().getOutputDirectoryPath() + "\\" + fileName + ".txt"));

                String provinceFile = province.toString();
                System.out.println(province.toString());

                Scanner scanner = new Scanner(provinceFile);

                while (scanner.hasNextLine())
                {
                    printStream.println(scanner.nextLine());
                }
            }
            catch (FileNotFoundException fnfe)
            {
                fnfe.printStackTrace();
                main.getLogHandler().updateLogTextArea(LogType.ERROR,"File Not Found: " + fileName);
            }
        }
        main.getLogHandler().updateLogTextArea(LogType.PRINTING,"Processing Finished");
    }
}
