package Query;

import Model.Province;
import Query.Model.Condition;
import Query.Model.Query;

import java.util.ArrayList;

public class WhereQuery implements QueryStrategy
{
    @Override
    public <T> ArrayList<T> doOperation(ArrayList<T> list, Query query)
    {
        ArrayList<T> resultList = new ArrayList<>();

        boolean allConditionsMet = false;

        for (T item : list)
        {

            for(Condition condition : query.getConditions())
            {
                if(item instanceof Province)
                {
                    String result = ((Province) item).getField(condition.getKeyWord(), condition.getValue());
                    if(result != null && result.equals(condition.getValue()))
                    {
                        allConditionsMet = true;
                    }
                    else
                    {
                        allConditionsMet = false;
                        break;
                    }
                }
            }

            if (allConditionsMet)
            {
                resultList.add(item);
                allConditionsMet = false;
            }
        }
        return resultList;
    }
}
