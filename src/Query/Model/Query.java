package Query.Model;

import java.util.ArrayList;

public class Query
{
    private String action;
    private QueryType queryType;
    private String objectType;
    private ArrayList<Condition> conditions = new ArrayList<>();
    private boolean conditionIsAll;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public QueryType getQueryType() {
        return queryType;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public ArrayList<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<Condition> conditions) {
        this.conditions = conditions;
    }

    public boolean isConditionIsAll() {
        return conditionIsAll;
    }

    public void setConditionIsAll(boolean conditionIsAll) {
        this.conditionIsAll = conditionIsAll;
    }
}
