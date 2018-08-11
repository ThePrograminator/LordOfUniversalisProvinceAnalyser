package Model;

import Model.ProvinceInformation.*;

import java.util.ArrayList;

public class Province
{
    private int ID;
    private ProvinceName provinceName;
    private City city;
    private Owner owner;
    private Development development;
    private ProvinceCitizens provinceCitizens;
    private TradeGoods tradeGoods;
    private HRE hre;
    private Discovered discovered;
    private ArrayList<Modifier> modifiers;
    private ArrayList<Building> buildings;
    private ArrayList<HistoryEvent> historyEvents;
    private RGB rgb;
    private ProvinceMapInformation provinceMapInformation;

    public Province(int ID) {
        this.ID = ID;
        provinceMapInformation = new ProvinceMapInformation();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ProvinceName getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(ProvinceName provinceName) {
        this.provinceName = provinceName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Development getDevelopment() {
        return development;
    }

    public void setDevelopment(Development development) {
        this.development = development;
    }

    public ProvinceCitizens getProvinceCitizens() {
        return provinceCitizens;
    }

    public void setProvinceCitizens(ProvinceCitizens provinceCitizens) {
        this.provinceCitizens = provinceCitizens;
    }

    public TradeGoods getTradeGoods() {
        return tradeGoods;
    }

    public void setTradeGoods(TradeGoods tradeGoods) {
        this.tradeGoods = tradeGoods;
    }

    public HRE getHre() {
        return hre;
    }

    public void setHre(HRE hre) {
        this.hre = hre;
    }

    public Discovered getDiscovered() {
        return discovered;
    }

    public void setDiscovered(Discovered discovered) {
        this.discovered = discovered;
    }

    public ArrayList<Modifier> getModifiers() {
        return modifiers;
    }

    public void setModifiers(ArrayList<Modifier> modifiers) {
        this.modifiers = modifiers;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public ArrayList<HistoryEvent> getHistoryEvents() {
        return historyEvents;
    }

    public void setHistoryEvents(ArrayList<HistoryEvent> historyEvents) {
        this.historyEvents = historyEvents;
    }

    public RGB getRgb() {
        return rgb;
    }

    public void setRgb(RGB rgb) {
        this.rgb = rgb;
    }

    public ProvinceMapInformation getProvinceMapInformation() {
        return provinceMapInformation;
    }

    public void setProvinceMapInformation(ProvinceMapInformation provinceMapInformation) {
        this.provinceMapInformation = provinceMapInformation;
    }

    public String createCommentString() {
        String toString;
        toString = "# Province ID : " +  String.valueOf(getID()) + "\n";
        toString += "# Province Name : " +  getProvinceName().getValueName() + "\n";
        if(getProvinceMapInformation().getArea() != null)
            toString += "# Province Area : " +  getProvinceMapInformation().getArea().getValueName() + "\n";
        else
            toString += "# Province Area : null" + "\n";
        if(getProvinceMapInformation().getRegion() != null)
            toString += "# Province Region : " +  getProvinceMapInformation().getRegion().getValueName() + "\n";
        else
            toString += "# Province Area : null" + "\n";
        if(getProvinceMapInformation().getSuperRegion() != null)
            toString += "# Province Super Region : " +  getProvinceMapInformation().getSuperRegion().getValueName() + "\n";
        else
            toString += "# Province Super Region : null" + "\n";
        if(getProvinceMapInformation().getContinent() != null)
            toString += "# Province Continent : " +  getProvinceMapInformation().getContinent().getValueName() + "\n";
        else
            toString += "# Province Continent : null" + "\n";
        if(getProvinceMapInformation().getTradeNode() != null)
            toString += "# Province Tradenode : " +  getProvinceMapInformation().getTradeNode().getValueName() + "\n";
        else
            toString += "# Province Tradenode : null" + "\n";
        if(getProvinceMapInformation().getClimate() != null)
            toString += "# Province Climate : " +  getProvinceMapInformation().getClimate().getValueName() + "\n";
        else
            toString += "# Province Climate : null" + "\n";
        if(getProvinceMapInformation().getWater() != null && getProvinceMapInformation().getWater().getValueName() != null)
        {
            if(getProvinceMapInformation().getWater().getValueName().equals("Sea"))
            {
                toString += "# Province is Sea Province : " +  getProvinceMapInformation().getWater().getValueName() + "\n";
            }
            else
            {
                toString += "# Province is Lake Province : " +  getProvinceMapInformation().getWater().getValueName() + "\n";
            }
        }
        else
        {
            toString += "# Province is not Sea/Lake Province" + "\n";
        }

        toString += "# Province Contents : " +  "\n" + "\n";

        return toString;
    }

    public String createContentString() {
        String toString = "";
        if(city != null)
            toString += city.toString();

        if(development != null)
            toString += development.toString();

        if(provinceCitizens != null)
            toString += provinceCitizens.toString();

        if(tradeGoods != null)
            toString += tradeGoods.toString();


















































































































































































































































































































































































































































































































































































































































































































































































































































        if(hre != null)
            toString += hre.toString();

        if(buildings != null && buildings.size() >= 1)
        {
            for (Building building : buildings)
            {
                toString += building.toString();
            }
        }

        if(modifiers != null && modifiers.size() >= 1)
        {
            for (Modifier modifier : modifiers)
            {
                toString += modifier.toString();
            }
        }

        if(discovered != null && (discovered.getTechnolgyGroupList() != null && discovered.getTechnolgyGroupList().size() >= 1) )
            toString += discovered.toString();

        if(historyEvents != null && historyEvents.size() >= 1)
        {
            for (HistoryEvent historyEvent : historyEvents)
            {
                toString += historyEvent.toString();
            }
        }

        return toString;
    }

    @Override
    public String toString()
    {
        String toString;
        toString = createCommentString();
        toString += createContentString();

        return toString;
    }
}
