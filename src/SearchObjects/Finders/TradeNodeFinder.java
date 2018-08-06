package SearchObjects.Finders;

import Model.Map.Continent;
import Model.Map.MapInformationService;
import Model.Map.TradeNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Christian on 14-07-2017.
 */
public class TradeNodeFinder implements Finder
{
    private ArrayList<TradeNode> tradeNodeList = new ArrayList<>();
    private final String FINDERNAME = "TradeNodeFinder";
    private final String FILENAME = "00_tradenodes.txt";
    private int currentTradeNodeId = 0;

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
                    String part1 = parts[0]; // Continent name
                    String part2 = parts[1]; // = {

                    String tradeNodeName = part1;
                    tradeNodeName = tradeNodeName.replaceAll("\\s+", "");

                    TradeNode tradeNode = new TradeNode(currentTradeNodeId);
                    tradeNode.setKeyName(tradeNodeName);

                    while (scanner.hasNextLine())
                    {
                        String provinceIDLine = scanner.nextLine();
                        provinceIDLine = provinceIDLine.replaceAll("\\s+","");

                        if (provinceIDLine.isEmpty())
                            continue;

                        if (provinceIDLine.contains("#"))
                            continue;

                        if (provinceIDLine.contains("members={"))
                        {
                            provinceIDLine = scanner.nextLine();

                            addToTradeNodeMap(tradeNode, provinceIDLine);

                            break;
                        }
                    }

                    this.tradeNodeList.add(tradeNode);

                    currentTradeNodeId++;
                }
            }
        }
        catch(FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
            return false;
        }

        for(TradeNode tradeNode : this.tradeNodeList)
        {
            System.out.println();
            System.out.print("id: " + tradeNode.getKeyName() + " => ");
            for (Integer integer : tradeNode.getProvinceList())
            {
                System.out.print(String.valueOf(integer) + ",");
            }
            System.out.println();
        }

        MapInformationService.getInstance().setTradeNodeList(this.tradeNodeList);

        return true;
    }

    public void addToTradeNodeMap(TradeNode tradeNode, String provinceIDLine)
    {
        Scanner scanner = new Scanner(provinceIDLine);

        while (scanner.hasNext())
        {
            int provinceID = scanner.nextInt();

            tradeNode.getProvinceList().add(provinceID);
        }
    }

    @Override
    public String getFinderName() {
        return FINDERNAME;
    }
}
