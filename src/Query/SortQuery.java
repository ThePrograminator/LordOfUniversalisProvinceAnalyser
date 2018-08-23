package Query;

import Model.Map.MapInformationService;
import Model.Province;
import Model.ProvinceInformation.ProvinceLangaugeNames;
import Query.Model.Condition;
import Query.Model.Query;

import java.util.ArrayList;

public class SortQuery implements QueryStrategy
{
    @Override
    public <T> ArrayList<T> doOperation(ArrayList<T> list, Query query)
    {
        ArrayList<T> resultList = new ArrayList<>();

        for(Condition condition : query.getConditions())
        {
            sortThis(condition);
        }
        return resultList;
    }

    private void sortThis(Condition condition)
    {

        ArrayList<Province> frontList = new ArrayList<>();
        ArrayList<Province> backList = new ArrayList<>();

        for (Province province : MapInformationService.getInstance().getProvinceList())
        {
            String result = province.getField(condition.getKeyWord(), condition.getValue());

            if (result == null)
                continue;
            else if (result.equals(condition.getValue()))
                frontList.add(province);
            else
                backList.add(province);
        }

        frontList.addAll(backList);

        for (int i = 0; i < frontList.size(); i++)
        {
            frontList.get(i).setID(i + 1);
            if (frontList.get(i).getProvinceName() != null)
            {
                if (frontList.get(i).getProvinceName().getProvinceLangaugeNamesList() != null || !frontList.get(i).getProvinceName().getProvinceLangaugeNamesList().isEmpty())
                {
                    for (ProvinceLangaugeNames provinceLangaugeNames : frontList.get(i).getProvinceName().getProvinceLangaugeNamesList())
                    {
                        provinceLangaugeNames.setId(frontList.get(i).getID());
                    }
                }
            }

        }

        MapInformationService.getInstance().setProvinceList(frontList);
    }
}
