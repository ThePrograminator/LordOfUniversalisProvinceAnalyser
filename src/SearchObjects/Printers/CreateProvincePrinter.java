package SearchObjects.Printers;

import Main.Main;
import Main.View.LogType;
import Model.Localisation.Localisation;
import Model.Map.LocalisationService;
import Model.Map.MapInformationService;
import Model.Province;
import Model.ProvinceInformation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateProvincePrinter
{
    private Main main;
    private final String PRINTERNAME = "CreatePrinter";

    public CreateProvincePrinter(Main main)
    {
        this.main = main;
    }

    public void printProvincesToFiles()
    {
        main.getLogHandler().updateLogTextArea(LogType.PRINTING,"Processing Started: " + PRINTERNAME);
        for (Province province : createProvinces())
        {
            String fileName = province.getID() + " - " + province.getProvinceName().getValueName();

            try
            {
                String value = Normalizer
                        .normalize(fileName, Normalizer.Form.NFD)
                        .replaceAll("[^\\p{ASCII}]", "");

                PrintStream printStream = new PrintStream(new File(main.getFileHandler().getOutputDirectoryPath() + "\\" + value + ".txt"));

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

    public ArrayList<Province> createProvinces()
    {
        int lastId = MapInformationService.getInstance().getProvinceList().get(MapInformationService.getInstance().getProvinceList().size() - 1).getID();
        ArrayList<Province> newProvinces = new ArrayList<>();
        ArrayList<String> discovered = new ArrayList<>();
        discovered.add("lotr_men");
        discovered.add("lotr_arnor_men");
        discovered.add("lotr_dwarven");
        discovered.add("lotr_elven");
        discovered.add("lotr_easterling");
        discovered.add("lotr_haradrim");
        discovered.add("lotr_rohirrim");
        discovered.add("lotr_northron");
        discovered.add("lotr_wildman");
        discovered.add("lotr_hobbit");
        discovered.add("lotr_ent");
        discovered.add("lotr_orc");
        discovered.add("lotr_goblin");
        discovered.add("lotr_troll");
        discovered.add("lotr_uruk_hai");
        discovered.add("lotr_dragon");
        discovered.add("lotr_nazgul");

        for (Localisation localisation : LocalisationService.getInstance().getProvinceLocalisation())
        {
            int provinceId = Integer.parseInt(localisation.getKeyName());
            if (provinceId > lastId)
            {
                Province province = new Province(provinceId);
                province.setProvinceName(new ProvinceName());
                province.getProvinceName().setValueName(localisation.getValueName());
                //Is City
                province.setCity(new City());
                province.getCity().setIsCity(false);
                //Development
                province.setDevelopment(new Development());
                province.getDevelopment().setBaseTax(1);
                province.getDevelopment().setBaseProduction(1);
                province.getDevelopment().setBaseManpower(1);
                //Citizens
                province.setProvinceCitizens(new ProvinceCitizens());
                province.getProvinceCitizens().setCulture("culture_wilderness");
                province.getProvinceCitizens().setReligion("religion_wilderness");

                //tradegoods
                province.setTradeGoods(new TradeGoods());
                province.getTradeGoods().setTradeGoods("grain");
                //hre
                province.setHre(new HRE());
                province.getHre().setPartOfHRE(false);
                //discovered
                province.setDiscovered(new Discovered());

                province.getDiscovered().setTechnolgyGroupList(discovered);

                newProvinces.add(province);
            }
        }
        return newProvinces;
    }
}
