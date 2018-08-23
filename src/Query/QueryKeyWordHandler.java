package Query;

import Query.Model.Condition;
import Query.Model.Query;
import Query.Model.QueryType;

import java.util.ArrayList;

public class QueryKeyWordHandler
{
    private Query query;

    public Query scanLine(String line)
    {
        String fullToken = "";

        this.query = new Query();

        for (int i = 0; i < line.length();i++)
        {
            String token = String.valueOf(line.charAt(i));

            if (token.isEmpty())
                continue;

            if (token.equals("{"))
            {
                ArrayList<Condition> conditionList = scanConditions(line.substring(i + 1));
                if(conditionList != null)
                    this.query.setConditions(conditionList);

                return this.query;
            }

            if (token.equals(" "))
            {
                switch (fullToken)
                {
                    case "GET":
                        query.setAction("GET");
                        fullToken = "";
                        continue;
                    case "EXECUTE":
                        query.setAction("EXECUTE");
                        fullToken = "";
                        continue;
                    case "WHERE":
                        query.setQueryType(QueryType.WHERE);
                        fullToken = "";
                        continue;
                    case "SET":
                        query.setQueryType(QueryType.SET);
                        fullToken = "";
                        continue;
                    case "SORT":
                        query.setQueryType(QueryType.SORT);
                        fullToken = "";
                        continue;
                    case "PROVINCES":
                        query.setObjectType("PROVINCES");
                        fullToken = "";
                        continue;
                }
            }

            fullToken += token;
        }
        return null;
    }

    public ArrayList<Condition> scanConditions(String conditionBody)
    {
        ArrayList<Condition> conditions = new ArrayList<>();
        Condition currentCondition = new Condition();
        String fullToken = "";

        for (int i = 0; i < conditionBody.length();i++)
        {
            String token = String.valueOf(conditionBody.charAt(i));

            if (token.equals(" "))
                continue;

            if (token.equals("="))
            {
                fullToken = fullToken.trim();

                String result = findConditionKey(fullToken);
                if (result == null)
                    return null;

                currentCondition.setKeyWord(result);
                fullToken = "";
                continue;
            }

            if (token.equals(","))
            {
                if (!fullToken.contains("(") && !fullToken.equals(","))
                {
                    if (currentCondition.getKeyWord() != null)
                    {
                        fullToken = fullToken.trim();

                        currentCondition.setValue(fullToken);
                        conditions.add(currentCondition);
                        currentCondition = new Condition();

                        fullToken = "";
                    }
                    continue;
                }
            }

            if (token.equals(")"))
            {
                fullToken += token;
                fullToken = fullToken.trim();

                currentCondition.setValue(fullToken);
                conditions.add(currentCondition);
                currentCondition = new Condition();

                fullToken = "";
                continue;
            }

            if (token.equals("}") && !fullToken.equals("}"))
            {
                if (currentCondition.getKeyWord() != null)
                {
                    fullToken = fullToken.trim();

                    if (fullToken.equals("ALL"))
                    {
                        query.setConditionIsAll(true);
                        return null;
                    }

                    currentCondition.setValue(fullToken);
                    conditions.add(currentCondition);
                }
                else if (fullToken.equals("ALL"))
                {
                    query.setConditionIsAll(true);
                    return null;
                }
                return conditions;
            }

            fullToken += token;
        }
        return null;
    }

    public String findConditionKey(String fullToken)
    {
        switch (fullToken)
        {
            case "ALL":
                query.setConditionIsAll(true);
                return null;
            case "id":
                return "id";
            case "name":
                return "name";
            case "is_city":
                return "is_city";
            case "ownership":
                return "ownership";
            case "core":
                return "core";
            case "owner":
                return "owner";
            case "controller":
                return "controller";
            case "development":
                return "development";
            case "base_tax":
                return "base_tax";
            case "base_production":
                return "base_production";
            case "base_manpower":
                return "base_manpower";
            case "culture":
                return "culture";
            case "religion":
                return "religion";
            case "tradegoods":
                return "tradegoods";
            case "hre":
                return "hre";
            case "discovered":
                return "discovered";
            case "modifier":
                return "modifier";
            case "building":
                return "building";
            case "area":
                return "area";
            case "region":
                return "region";
            case "super_region":
                return "super_region";
            case "continent":
                return "continent";
            case "climate":
                return "climate";
            case "trade_node":
                return "trade_node";
            default:
                return null;
        }
    }
}
