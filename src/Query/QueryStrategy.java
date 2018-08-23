package Query;

import Query.Model.Query;

import java.util.ArrayList;

public interface QueryStrategy
{
    <T> ArrayList<T> doOperation(ArrayList<T> list, Query query);
}
