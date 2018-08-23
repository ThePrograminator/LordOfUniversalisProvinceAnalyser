package Query;

import Model.Province;
import Query.Model.Condition;
import Query.Model.Query;

import java.util.ArrayList;

public class SetQuery implements QueryStrategy
{
    @Override
    public <T> ArrayList<T> doOperation(ArrayList<T> list, Query query)
    {
        ArrayList<T> resultList = new ArrayList<>();

        //boolean allConditionsMet = false;

        for (T item : list)
        {
            for(Condition condition : query.getConditions())
            {
                if(item instanceof Province)
                {
                    boolean result = ((Province) item).setField(condition.getKeyWord(), condition.getValue());
                }
            }

            /*if (allConditionsMet)
            {
                resultList.add(item);
                allConditionsMet = false;
            }*/
        }
        return resultList;
    }
}
