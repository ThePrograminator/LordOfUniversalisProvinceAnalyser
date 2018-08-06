package Model;

import java.util.ArrayList;

public class Discovered
{
    private ArrayList<String> technolgyGroupList;

    public Discovered(ArrayList<String> technolgyGroupList) {
        this.technolgyGroupList = technolgyGroupList;
    }

    public ArrayList<String> getTechnolgyGroupList() {
        return technolgyGroupList;
    }

    public void setTechnolgyGroupList(ArrayList<String> technolgyGroupList) {
        this.technolgyGroupList = technolgyGroupList;
    }
}
