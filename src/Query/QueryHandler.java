package Query;

import Main.Main;
import Main.View.LogType;
import Model.Map.MapInformationService;
import Model.Province;
import Query.Model.Query;
import Query.Model.QueryType;
import javafx.application.Platform;

import java.util.ArrayList;

public class QueryHandler
{
    private Main main;

    public QueryHandler(Main main)
    {
        this.main = main;
    }

    public <T> boolean doQuery(String getQueryMessage, String setQueryMessage)
    {
        main.getLogHandler().updateLogTextArea(LogType.QUERY,"Starting Query Now");
        ArrayList<T> getQueryList = new ArrayList<>();
        if (getQueryMessage == null || getQueryMessage.isEmpty())
        {
            main.getLogHandler().updateLogTextArea(LogType.QUERY,"Get Query is empty or invalid");
        }
        else
        {
            getQueryList = doGetQuery(getQueryMessage);
            //resultProvinces = (ArrayList<Province>)getQueryList;
            main.setResultProvinces((ArrayList<Province>)getQueryList);
            if(getQueryList == null || getQueryList.isEmpty())
            {
                main.getLogHandler().updateLogTextArea(LogType.ERROR,"Get Query Failed");
                Platform.runLater(() -> main.getGetQueryResultLabel().setText("Result: 0"));
                return false;
            }
            else
            {
                ArrayList<T> finalGetQueryList = getQueryList;
                Platform.runLater(() -> main.getGetQueryResultLabel().setText("Result: " + String.valueOf(finalGetQueryList.size())));
            }
        }

        boolean setQueryResult = doSetQuery(setQueryMessage, getQueryList);

        if(!setQueryResult)
        {
            main.getLogHandler().updateLogTextArea(LogType.ERROR,"Set Query Failed");
        }

        main.getLogHandler().updateLogTextArea(LogType.QUERY,"Finished Query Succesfully");

        return setQueryResult;
    }

    public <T> ArrayList<T> doGetQuery(String queryMessage)
    {
        QueryKeyWordHandler queryKeyWordHandler = new QueryKeyWordHandler();
        Query query = queryKeyWordHandler.scanLine("GET " + queryMessage);

        if (query == null)
            return null;

        if (query.getAction() == null)
            return null;

        if(query.getAction().equals("GET"))
        {
            Context context = new Context(new WhereQuery());

            if (query.getObjectType() == null)
                return null;

            if (query.getObjectType().equals("PROVINCES"))
            {
                if (query.getConditions() != null && !query.getConditions().isEmpty())
                {
                    ArrayList<Province> list = context.executeStrategy(MapInformationService.getInstance().getProvinceList(), query);
                    return (ArrayList<T>) list;
                }
                else if(query.isConditionIsAll())
                {
                    ArrayList<Province> list = MapInformationService.getInstance().getProvinceList();
                    return (ArrayList<T>) list;
                }
                else
                {
                    return null;
                }
            }
        }
        return null;
    }

    public <T> boolean doSetQuery(String setQueryMessage, ArrayList<T> list)
    {
        if (list == null || list.isEmpty())
            return false;

        if (setQueryMessage == null || setQueryMessage.isEmpty())
            return false;

        QueryKeyWordHandler queryKeyWordHandler = new QueryKeyWordHandler();
        Query query = queryKeyWordHandler.scanLine("EXECUTE " + setQueryMessage);

        if (query == null)
            return false;

        if (query.getAction() == null)
            return false;

        if(query.getAction().equals("EXECUTE"))
        {
            Context context;
            if (query.getQueryType() == QueryType.SET)
                context = new Context(new SetQuery());
            else if (query.getQueryType() == QueryType.SORT)
                context = new Context(new SortQuery());
            else
                return false;

            if (query.getObjectType() == null)
                return false;

            if (query.getConditions() != null && !query.getConditions().isEmpty())
            {
                context.executeStrategy(list, query);
            }
            else if(query.isConditionIsAll())
            {

            }
            else
            {
                return false;
            }
        }
        return true;
    }
}
