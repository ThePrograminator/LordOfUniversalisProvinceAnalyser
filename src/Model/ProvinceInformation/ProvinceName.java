package Model.ProvinceInformation;

import java.util.ArrayList;
import java.util.TreeMap;

public class ProvinceName
{
    private String keyName;
    private String valueName;
    private ArrayList<ProvinceLangaugeNames> provinceLangaugeNamesList = new ArrayList<>();

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

    public ArrayList<ProvinceLangaugeNames> getProvinceLangaugeNamesList() {
        return provinceLangaugeNamesList;
    }

    public void setProvinceLangaugeNamesList(ArrayList<ProvinceLangaugeNames> provinceLangaugeNamesList) {
        this.provinceLangaugeNamesList = provinceLangaugeNamesList;
    }
}
