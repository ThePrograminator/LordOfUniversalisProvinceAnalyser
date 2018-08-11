package Model.ProvinceInformation;

import java.util.ArrayList;

public class Discovered
{
    private ArrayList<String> technolgyGroupList;

    public Discovered()
    {
        technolgyGroupList = new ArrayList<>();
    }

    public ArrayList<String> getTechnolgyGroupList() {
        return technolgyGroupList;
    }

    public void setTechnolgyGroupList(ArrayList<String> technolgyGroupList) {
        this.technolgyGroupList = technolgyGroupList;
    }

    @Override
    public String toString()
    {
        String toString = "";
        for (String string : technolgyGroupList)
        {
            toString += "discovered_by = " + string + "\n";
        }

        toString += "\n";
        return toString;
    }
}
