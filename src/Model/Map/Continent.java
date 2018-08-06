package Model.Map;

import java.util.ArrayList;

public class Continent
{
    private int id;
    private ArrayList<Integer> provinceList;
    private String keyName;
    private String valueName;

    public Continent(int id)
    {
        this.id = id;
        this.provinceList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(ArrayList<Integer> provinceList) {
        provinceList = provinceList;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }
}
