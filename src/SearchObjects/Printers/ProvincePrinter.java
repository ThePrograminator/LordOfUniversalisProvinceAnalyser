package SearchObjects.Printers;

import Main.Main;
import Main.View.LogType;
import Model.Map.Area;
import Model.Province;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Christian on 19-07-2017.
 */
public class ProvincePrinter
{
    private Main main;
    private final String PRINTERNAME = "ProvincePrinter";

    public ProvincePrinter(Main main)
    {
        this.main = main;
    }

    public void printProvincesToFiles(ArrayList<Province> provinceList)
    {
        main.getLogHandler().updateLogTextArea(LogType.PRINTING,"Processing Started: " + PRINTERNAME);
        for (Province province : provinceList)
        {
            if (province.getID() == 39)
            {
                System.out.println("hello");
            }
            //String fileName = province.getID() + " - " + province.getProvinceName().getKeyName();
            String fileName = province.getID() + " - " + province.getProvinceName().getValueName();

            try
            {
                //byte ptext[] = fileName.getBytes(StandardCharsets.ISO_8859_1);
                //String value = new String(fileName.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                String value = Normalizer
                        .normalize(fileName, Normalizer.Form.NFD)
                        .replaceAll("[^\\p{ASCII}]", "");

                PrintStream printStream = new PrintStream(new File(main.getFileHandler().getOutputDirectoryPath() + "\\" + value + ".txt"));



                //PrintStream printStream = new PrintStream(new File(main.getFileHandler().getOutputDirectoryPath() + "\\" + fileName + ".txt"));

                String provinceFile = province.toString();

                Scanner scanner = new Scanner(provinceFile);

                while (scanner.hasNextLine())
                {
                    printStream.println(scanner.nextLine());
                }
                scanner.close();
            }
            catch (FileNotFoundException fnfe)
            {
                fnfe.printStackTrace();
                main.getLogHandler().updateLogTextArea(LogType.ERROR,"File Not Found: " + fileName);
            }
        }
        main.getLogHandler().updateLogTextArea(LogType.PRINTING,"Processing Finished: " + PRINTERNAME);
    }
}
