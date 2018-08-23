package Query;

import Query.Model.Condition;
import Query.Model.Query;

import java.util.ArrayList;

public class Context
{
    private QueryStrategy queryStrategy;

    public Context(QueryStrategy queryStrategy)
    {
        this.queryStrategy =  queryStrategy;
    }

    public <T> ArrayList<T> executeStrategy(ArrayList<T> list, Query query)
    {
        return queryStrategy.doOperation(list, query);
    }
}
