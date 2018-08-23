package SearchObjects.Finders;

import Model.Map.Area;
import Model.Map.MapInformationService;
import Model.Map.Region;
import Model.Province;
import Model.ProvinceInformation.*;
import SearchObjects.ProvinceKeyWordHandler;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Christian on 14-07-2017.
 */
public class ProvinceFinder implements Finder
{
    private ArrayList<Province> provinceList = new ArrayList<>();
    private final String FINDERNAME = "ProvinceFinder";
    private File[] listOfFiles;
    private ProvinceKeyWordHandler provinceKeyWordHandler = new ProvinceKeyWordHandler();
    private Province currentProvince;
    private String directory;

    public ProvinceFinder(String directory)
    {
        this.directory = directory;
    }


    @Override
    public boolean loadFiles(String directory)
    {
        try {
            File folder = new File(this.directory);
            listOfFiles = folder.listFiles();
        }
        catch (Exception fnfe)
        {
            fnfe.printStackTrace();
        }

        for (File file : listOfFiles)
        {
            if (!file.isFile())
                continue;

            Pair<Integer, String> provinceInformation = getProvinceName(file);

            currentProvince = new Province(provinceInformation.getKey());
            currentProvince.setProvinceName(new ProvinceName());
            currentProvince.getProvinceName().setKeyName(provinceInformation.getValue());

            try
            {
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine())
                {
                    String line = scanner.nextLine();
                    line = line.replaceAll("\\s+", "");

                    if (line.isEmpty())
                        continue;

                    if (line.charAt(0) == '#')
                        continue;

                    if (line.charAt(0) == '}')
                        continue;

                    checkForKeyWord(line, scanner);
                }

                provinceList.add(currentProvince);
                scanner.close();
            }
            catch (FileNotFoundException fnfe)
            {
                fnfe.printStackTrace();
            }
        }

        MapInformationService.getInstance().transferProvinceList(this.provinceList);

        return true;
    }

    public Pair<Integer, String> getProvinceName(File file)
    {
        //---------------------------------------------------
        // First Split, Splits the the name of the files, between the province id and the name
        //---------------------------------------------------

        String fileName = file.getName();

        String[] parts = fileName.split("-", 2);
        String provinceID = parts[0]; // province id
        String part2 = parts[1]; // province name

        //---------------------------------------------------
        // Second Split, Splits the name from the ending (.txt)
        //---------------------------------------------------

        String provinceName = part2;
        provinceName = provinceName.replaceFirst("\\s+","");

            String[] parts2 = provinceName.split("\\.");
            String part1two = parts2[0]; // province name
            String part2two = parts2[1]; // province .txt

            provinceName = part1two;


        //---------------------------------------------------

        provinceID = provinceID.replaceAll("\\s+","");

        return new Pair<Integer, String>(Integer.parseInt(provinceID), provinceName);
    }

    public void checkForKeyWord(String line, Scanner scanner)
    {
        line = line.replaceAll("\\s+", "");

        if (line.contains(provinceKeyWordHandler.getIsCity()))
        {
            if (currentProvince.getCity() == null)
                currentProvince.setCity(new City());

            String[] parts = line.split("=");
            String part1 = parts[0]; // is_city
            String part2 = parts[1]; // value

            currentProvince.getCity().setIsCity(Boolean.parseBoolean(part2));
        }
        else if (line.contains(provinceKeyWordHandler.getAddCore()))
        {
            if (currentProvince.getOwner() == null)
                currentProvince.setOwner(new Owner());

            String[] parts = line.split("=");
            String part1 = parts[0]; // add_core
            String part2 = parts[1]; // value

            currentProvince.getOwner().getCore().add(part2);
        }
        else if (line.contains(provinceKeyWordHandler.getOwner()))
        {
            if (currentProvince.getOwner() == null)
                currentProvince.setOwner(new Owner());

            String[] parts = line.split("=");
            String part1 = parts[0]; // add_core
            String part2 = parts[1]; // value

            currentProvince.getOwner().setOwner(part2);
        }
        else if (line.contains(provinceKeyWordHandler.getController()))
        {
            if (currentProvince.getOwner() == null)
                currentProvince.setOwner(new Owner());

            String[] parts = line.split("=");
            String part1 = parts[0]; // add_core
            String part2 = parts[1]; // value

            currentProvince.getOwner().setController(part2);
        }
        else if (line.contains(provinceKeyWordHandler.getBaseTax()))
        {
            if (currentProvince.getDevelopment() == null)
                currentProvince.setDevelopment(new Development());

            String[] parts = line.split("=");
            String part1 = parts[0]; // base_tax
            String part2 = parts[1]; // value

            currentProvince.getDevelopment().setBaseTax(Integer.parseInt(part2));
        }
        else if (line.contains(provinceKeyWordHandler.getBaseProduction()))
        {
            if (currentProvince.getDevelopment() == null)
                currentProvince.setDevelopment(new Development());

            String[] parts = line.split("=");
            String part1 = parts[0]; // base_production
            String part2 = parts[1]; // value

            currentProvince.getDevelopment().setBaseProduction(Integer.parseInt(part2));
        }
        else if (line.contains(provinceKeyWordHandler.getBaseManpower()))
        {
            if (currentProvince.getDevelopment() == null)
                currentProvince.setDevelopment(new Development());

            String[] parts = line.split("=");
            String part1 = parts[0]; // base_manpower
            String part2 = parts[1]; // value

            currentProvince.getDevelopment().setBaseManpower(Integer.parseInt(part2));
        }
        else if (line.contains(provinceKeyWordHandler.getCulture()))
        {
            if (currentProvince.getProvinceCitizens() == null)
                currentProvince.setProvinceCitizens(new ProvinceCitizens());

            String[] parts = line.split("=");
            String part1 = parts[0]; // culture
            String part2 = parts[1]; // value

            currentProvince.getProvinceCitizens().setCulture(part2);
        }
        else if (line.contains(provinceKeyWordHandler.getReligion()))
        {
            if (currentProvince.getProvinceCitizens() == null)
                currentProvince.setProvinceCitizens(new ProvinceCitizens());

            String[] parts = line.split("=");
            String part1 = parts[0]; // religion
            String part2 = parts[1]; // value

            currentProvince.getProvinceCitizens().setReligion(part2);
        }
        else if (line.contains(provinceKeyWordHandler.getTradeGoods()))
        {
            if (currentProvince.getTradeGoods() == null)
                currentProvince.setTradeGoods(new TradeGoods());

            String[] parts = line.split("=");
            String part1 = parts[0]; // trade_goods
            String part2 = parts[1]; // value

            currentProvince.getTradeGoods().setTradeGoods(part2);
        }
        else if (line.contains(provinceKeyWordHandler.getHre()))
        {
            if (currentProvince.getHre() == null)
                currentProvince.setHre(new HRE());

            String[] parts = line.split("=");
            String part1 = parts[0]; // hre
            String part2 = parts[1]; // value

            currentProvince.getHre().setPartOfHRE(Boolean.parseBoolean(part2));
        }
        else if (line.contains(provinceKeyWordHandler.getAddPermanentProvinceModifier()))
        {
            if (currentProvince.getModifiers() == null)
                currentProvince.setModifiers(new ArrayList<>());

            String[] parts = line.split("=");
            String part1 = parts[0]; // add_permanent_province_modifier
            String part2 = parts[1]; // value

            Modifier modifier = new Modifier();
            modifier.setKeywordName(part1);

            while (scanner.hasNextLine())
            {
                String nextLine = scanner.nextLine();
                nextLine = nextLine.replaceAll("\\s+", "");

                if (nextLine.isEmpty())
                    continue;

                if (nextLine.charAt(0) == '#')
                    continue;

                if (nextLine.charAt(0) == '}')
                    break;

                if (nextLine.contains("name"))
                {
                    String[] nextLineParts = nextLine.split("=");
                    String nextLinePart1 = nextLineParts[0]; // keyword
                    String nextLinePart2 = nextLineParts[1]; // value

                    modifier.setModifierName(nextLinePart2);
                }
                else if (nextLine.contains("duration"))
                {
                    String[] nextLineParts = nextLine.split("=");
                    String nextLinePart1 = nextLineParts[0]; // keyword
                    String nextLinePart2 = nextLineParts[1]; // value

                    modifier.setDuration(Integer.parseInt(nextLinePart2));
                }
            }

            if (modifier.getDuration() == 0)
            {
                modifier.setDuration(-1);
            }

            currentProvince.getModifiers().add(modifier);
        }
        else if (line.contains(provinceKeyWordHandler.getAddProvinceModifier()))
        {
            if (currentProvince.getModifiers() == null)
                currentProvince.setModifiers(new ArrayList<>());

            String[] parts = line.split("=");
            String part1 = parts[0]; // add_province_modifier
            String part2 = parts[1]; // value

            Modifier modifier = new Modifier();
            modifier.setKeywordName(part1);

            while (scanner.hasNextLine())
            {
                String nextLine = scanner.nextLine();
                nextLine = nextLine.replaceAll("\\s+", "");

                if (nextLine.isEmpty())
                    continue;

                if (nextLine.charAt(0) == '#')
                    continue;

                if (nextLine.charAt(0) == '}')
                    break;

                if (nextLine.contains("name"))
                {
                    String[] nextLineParts = nextLine.split("=");
                    String nextLinePart1 = nextLineParts[0]; // keyword
                    String nextLinePart2 = nextLineParts[1]; // value

                    modifier.setModifierName(nextLinePart2);
                }
                else if (nextLine.contains("duration"))
                {
                    String[] nextLineParts = nextLine.split("=");
                    String nextLinePart1 = nextLineParts[0]; // keyword
                    String nextLinePart2 = nextLineParts[1]; // value

                    modifier.setDuration(Integer.parseInt(nextLinePart2));
                }
            }

            currentProvince.getModifiers().add(modifier);
        }
        else if (line.contains(provinceKeyWordHandler.getDiscoveredBy()))
        {
            if (currentProvince.getDiscovered() == null)
                currentProvince.setDiscovered(new Discovered());

            String[] parts = line.split("=");
            String part1 = parts[0]; // discorvered_by
            String part2 = parts[1]; // value

            currentProvince.getDiscovered().getTechnolgyGroupList().add(part2);
        }
        else if(checkForBuildingKeyWord(line))
        {
            if (currentProvince.getBuildings() == null)
                currentProvince.setBuildings(new ArrayList<>());

            Building building = new Building();
            building.setBuildingName(line);

            currentProvince.getBuildings().add(building);
        }
        else  if (line.matches(".*\\..*\\..*"))
        {
            if (currentProvince.getHistoryEvents() == null)
                currentProvince.setHistoryEvents(new ArrayList<>());

            String[] parts = line.split("=");
            String part1 = parts[0]; // date
            String part2 = parts[1]; // = {

            HistoryEvent historyEvent = new HistoryEvent();
            historyEvent.setDate(part1);

            boolean foundModifier = false;

            while (scanner.hasNextLine())
            {
                String nextLine = scanner.nextLine();
                nextLine = nextLine.trim();

                if (nextLine.isEmpty())
                    continue;

                if (nextLine.charAt(0) == '#')
                    continue;

                if (nextLine.contains(provinceKeyWordHandler.getAddProvinceModifier()) || nextLine.contains(provinceKeyWordHandler.getAddPermanentProvinceModifier()))
                {
                    foundModifier = true;
                    historyEvent.addContents(nextLine);
                    continue;
                }

                if (nextLine.charAt(0) == '}' && !foundModifier)
                    break;
                else if (nextLine.charAt(0) == '}' && foundModifier)
                    foundModifier = false;

                if (!foundModifier)
                {
                    historyEvent.addContents(nextLine);
                }
                else
                {
                    nextLine = "\t" +  nextLine;
                    historyEvent.addContents(nextLine);
                }
            }

            currentProvince.getHistoryEvents().add(historyEvent);
        }
    }

    public boolean checkForBuildingKeyWord(String line)
    {
        for (Building building : MapInformationService.getInstance().getBuildingList())
        {
            if (line.contains(building.getBuildingName()))
                return true;
        }
        return false;
    }

    @Override
    public String getFinderName() {
        return FINDERNAME;
    }
}
