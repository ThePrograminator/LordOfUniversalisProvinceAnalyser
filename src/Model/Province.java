package Model;

import Model.Localisation.Localisation;
import Model.Map.Area;
import Model.Map.LocalisationService;
import Model.Map.MapInformationService;
import Model.ProvinceInformation.*;

import java.util.*;

public class Province implements Comparable<Province>
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
        if(getRgb() != null)
            toString += "# Province RGB : (" +  getRgb().getRed() + ", " + getRgb().getGreen() + ", " + getRgb().getBlue() + ")" + "\n";
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

        if(owner != null)
            toString += owner.toString();

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

    public String getField(String key, String value)
    {
        if (getProvinceMapInformation().getWater() == null)
        {
            switch (key)
            {
                case "id":
                    return String.valueOf(getID());
                case "name":
                    if (getProvinceName() != null && getProvinceName().getValueName() != null)
                        return String.valueOf(getProvinceName().getValueName());
                case "is_city":
                    if (getCity() != null)
                        return String.valueOf(getCity().isCity());
                case "core":
                    if (getOwner() != null && getOwner().getController() != null)
                        return String.valueOf(getOwner().getCore());
                case "owner":
                    if (getOwner() != null && getOwner().getController() != null)
                        return String.valueOf(getOwner().getOwner());
                case "controller":
                    if (getOwner() != null && getOwner().getController() != null)
                        return String.valueOf(getOwner().getController());
                case "development":
                    if (getDevelopment() != null)
                    {
                        int result = getDevelopment().getBaseTax() + getDevelopment().getBaseProduction() + getDevelopment().getBaseManpower();
                        return String.valueOf(result);
                    }
                case "base_tax":
                    if (getDevelopment() != null)
                        return String.valueOf(getDevelopment().getBaseTax());
                case "base_production":
                    if (getDevelopment() != null)
                        return String.valueOf(getDevelopment().getBaseProduction());
                case "base_manpower":
                    if (getDevelopment() != null)
                        return String.valueOf(getDevelopment().getBaseManpower());
                case "culture":
                    if (getProvinceCitizens() != null && getProvinceCitizens().getCulture() != null)
                        return String.valueOf(getProvinceCitizens().getCulture());
                case "religion":
                    if (getProvinceCitizens() != null && getProvinceCitizens().getReligion() != null)
                        return String.valueOf(getProvinceCitizens().getReligion());
                case "tradegoods":
                    if (getTradeGoods() != null)
                        return String.valueOf(getTradeGoods().getTradeGoods());
                case "hre":
                    if (getHre() != null)
                        return String.valueOf(getHre().isPartOfHRE());
                case "discovered":
                    if (getDiscovered() != null && getDiscovered().getTechnolgyGroupList() != null)
                    {
                        for (String keyword : getDiscovered().getTechnolgyGroupList())
                        {
                            if (keyword.equals(value))
                                return String.valueOf(keyword);
                        }
                    }
                case "modifier":
                    if (getModifiers() != null)
                        return String.valueOf(getModifiers());
                case "building":
                    if (getBuildings() != null)
                        return String.valueOf(getBuildings());
                case "history":
                    if (getHistoryEvents() != null)
                        return String.valueOf(getHistoryEvents());
                case "area":
                    if (getProvinceMapInformation().getArea() != null)
                        return String.valueOf(getProvinceMapInformation().getArea().getKeyName());
                case "region":
                    if (getProvinceMapInformation().getRegion() != null)
                        return String.valueOf(getProvinceMapInformation().getRegion().getKeyName());
                case "super_region":
                    if (getProvinceMapInformation().getSuperRegion() != null)
                        return String.valueOf(getProvinceMapInformation().getSuperRegion().getKeyName());
                case "continent":
                    if (getProvinceMapInformation().getContinent() != null)
                        return String.valueOf(getProvinceMapInformation().getContinent().getKeyName());
                case "climate":
                    if (getProvinceMapInformation().getClimate() != null)
                        return String.valueOf(getProvinceMapInformation().getClimate().getKeyName());
                case "trade_node":
                    if (getProvinceMapInformation().getTradeNode() != null)
                        return String.valueOf(getProvinceMapInformation().getTradeNode().getKeyName());
            }
        }
        else
        {
            switch (key)
            {
                case "id":
                    return String.valueOf(getID());
                case "name":
                    if (getProvinceName() != null && getProvinceName().getValueName() != null)
                        return String.valueOf(getProvinceName().getValueName());
                case "discovered":
                    if (getDiscovered() != null && getDiscovered().getTechnolgyGroupList() != null)
                    {
                        for (String keyword : getDiscovered().getTechnolgyGroupList())
                        {
                            if (keyword.equals(value))
                                return String.valueOf(keyword);
                        }
                    }
                case "modifier":
                    if (getModifiers() != null)
                        return String.valueOf(getModifiers());
                case "building":
                    if (getBuildings() != null)
                        return String.valueOf(getBuildings());
                case "area":
                    if (getProvinceMapInformation().getArea() != null)
                        return String.valueOf(getProvinceMapInformation().getArea().getKeyName());
                case "region":
                    if (getProvinceMapInformation().getRegion() != null)
                        return String.valueOf(getProvinceMapInformation().getRegion().getKeyName());
                case "super_region":
                    if (getProvinceMapInformation().getSuperRegion() != null)
                        return String.valueOf(getProvinceMapInformation().getSuperRegion().getKeyName());
                case "continent":
                    if (getProvinceMapInformation().getContinent() != null)
                        return String.valueOf(getProvinceMapInformation().getContinent().getKeyName());
                case "lake":
                    if (getProvinceMapInformation().getWater() != null && getProvinceMapInformation().getWater().getKeyName() != null)
                        return String.valueOf(getProvinceMapInformation().getWater().getKeyName());
                case "sea":
                    if (getProvinceMapInformation().getWater() != null && getProvinceMapInformation().getWater().getKeyName() != null)
                        return String.valueOf(getProvinceMapInformation().getWater().getKeyName());
            }
        }
        return null;
    }

    public boolean setField(String key, String value)
    {
        switch (key)
        {
            case "id":
                try
                {
                    setID(Integer.parseInt(value));
                    return true;
                }
                catch (IllegalArgumentException ia)
                {
                    return false;
                }
            case "name":
                getProvinceName().setValueName(value);

                for (Localisation localisation : LocalisationService.getInstance().getProvinceLocalisation())
                {
                    int provinceId = Integer.parseInt(localisation.getKeyName());
                    if (provinceId == getID())
                    {
                        localisation.setValueName(value);
                    }
                }

                return true;
            case "is_city":
                try
                {
                    getCity().setIsCity(Boolean.parseBoolean(value));
                    return true;
                }
                catch (IllegalArgumentException ia)
                {
                    return false;
                }
            case "ownership":
                if (value.equals("null"))
                {
                    setOwner(null);
                    return true;
                }
                else
                {
                    return false;
                }
            case "core":
                if (value.equals("null"))
                    getOwner().setCore(null);
                else
                {
                    if (getOwner() == null)
                        setOwner(new Owner());

                    getOwner().getCore().add(value);
                }
                return true;
            case "owner":
                if (value.equals("null"))
                    getOwner().setOwner(null);
                else
                {
                    if (getOwner() == null)
                        setOwner(new Owner());

                    getOwner().setOwner(value);
                }
                return true;
            case "controller":
                if (value.equals("null"))
                    getOwner().setController(null);
                else
                {
                    if (getOwner() == null)
                        setOwner(new Owner());

                    getOwner().setController(value);
                }
                return true;
            case "development":
                if (value.equals("null"))
                {
                    setDevelopment(null);
                    return true;
                }
                else
                {
                    if (value.contains("random"))
                    {
                        String randomBody = value.substring(value.indexOf('('));
                        randomBody = randomBody.replaceAll("[()]", "");

                        String[] parts = randomBody.split(",", 2);
                        String lowValue = parts[0]; // province id
                        String highValue = parts[1]; // province name

                        int highvalueInt = 0;
                        int lowvalueInt = 0;
                        try
                        {
                            highvalueInt = Integer.parseInt(highValue);
                            lowvalueInt = Integer.parseInt(lowValue);
                        }
                        catch (IllegalArgumentException ia)
                        {
                            return false;
                        }

                        Random random = new Random();

                        int result = random.nextInt(highvalueInt + 1 - lowvalueInt) + lowvalueInt;

                        List<Integer> intList = new ArrayList<>(3);
                        int temp = 0;
                        int sum = 0;
                        for (int i = 0; i <= 2; i++)
                        {
                            if (!(i == 2))
                            {
                                temp = random.nextInt((result - sum) / (2 - i)) + 1;
                                if (sum + temp == result)
                                {
                                    temp = temp - 1;
                                    intList.add(temp);
                                }
                                else
                                    intList.add(temp);

                                sum += temp;
                            }
                            else
                            {
                                int last = (result - sum);
                                intList.add(last);
                                sum += last;
                            }
                        }

                        Collections.shuffle(intList);

                        if (this.getDevelopment() == null)
                            this.setDevelopment(new Development());

                        this.getDevelopment().setBaseTax(intList.get(0));
                        this.getDevelopment().setBaseProduction(intList.get(1));
                        this.getDevelopment().setBaseManpower(intList.get(2));

                        return true;
                    }

                    return false;
                }
            case "base_tax":
                try
                {
                    getDevelopment().setBaseTax(Integer.parseInt(value));
                    return true;
                }
                catch (IllegalArgumentException ia)
                {
                    return false;
                }
            case "base_production":
                try
                {
                    getDevelopment().setBaseProduction(Integer.parseInt(value));
                    return true;
                }
                catch (IllegalArgumentException ia)
                {
                    return false;
                }
            case "base_manpower":
                try
                {
                    getDevelopment().setBaseManpower(Integer.parseInt(value));
                    return true;
                }
                catch (IllegalArgumentException ia)
                {
                    return false;
                }
            case "culture":
                if (value.equals("null"))
                    getProvinceCitizens().setCulture(null);
                else
                {
                    if (getProvinceCitizens() == null)
                    {
                        setProvinceCitizens(new ProvinceCitizens());
                    }
                    getProvinceCitizens().setCulture(value);
                }

                return true;
            case "religion":
                if (value.equals("null"))
                    getProvinceCitizens().setReligion(null);
                else
                {
                    if (getProvinceCitizens() == null)
                    {
                        setProvinceCitizens(new ProvinceCitizens());
                    }
                    getProvinceCitizens().setReligion(value);
                }
                return true;
            case "tradegoods":
                if (value.equals("null"))
                    getTradeGoods().setTradeGoods(null);
                else if (value.contains("random"))
                {
                    String randomBody = value.substring(value.indexOf('('));
                    randomBody = randomBody.replaceAll("[()]", "");

                    String[] parts = randomBody.split(",");
                    Random random = new Random();

                    int randomIndex = random.nextInt(parts.length) + 1;

                    getTradeGoods().setTradeGoods(parts[randomIndex - 1]);
                }
                else
                {
                    if (getTradeGoods() == null)
                        setTradeGoods(new TradeGoods());

                    getTradeGoods().setTradeGoods(value);
                }

                return true;
            case "hre":
                if (value.equals("null"))
                {
                    setHre(null);
                    return true;
                }

                try
                {
                    getHre().setPartOfHRE(Boolean.parseBoolean(value));
                    return true;
                }
                catch (IllegalArgumentException ia)
                {
                    return false;
                }
            case "discovered":
                if (value.equals("null"))
                    setDiscovered(null);
                else
                    getDiscovered().getTechnolgyGroupList().add(value);
                return true;
            case "modifier":
                if (value.equals("null"))
                {
                    setModifiers(null);
                    return true;
                }
                else
                {
                    return false;
                }
            case "building":
                if (value.equals("null"))
                {
                    setBuildings(null);
                    return true;
                }
                else
                {
                    return false;
                }
            case "history":
                if (value.equals("null"))
                {
                    setHistoryEvents(null);
                    return true;
                }
                else
                {
                    return false;
                }
            case "area":
                if (value.equals("null"))
                {
                    if (getProvinceMapInformation().getArea() != null)
                        getProvinceMapInformation().getArea().getProvinceList().remove(new Integer(getID()));

                    getProvinceMapInformation().setArea(null);
                }
                else
                {
                    getProvinceMapInformation().getArea().getProvinceList().remove(new Integer(getID()));
                    for (Area area : MapInformationService.getInstance().getAreaList())
                    {
                        if(area.getKeyName().equals(value))
                        {
                            area.getProvinceList().add(this.getID());
                            getProvinceMapInformation().setArea(area);
                        }
                    }
                }
                return true;
            case "region":
                if (value.equals("null"))
                    getProvinceMapInformation().setRegion(null);
                else
                    getProvinceMapInformation().getRegion().setKeyName(value);
                return true;
            case "super_region":
                if (value.equals("null"))
                    getProvinceMapInformation().setSuperRegion(null);
                else
                    getProvinceMapInformation().getSuperRegion().setKeyName(value);
                return true;
            case "continent":
                if (value.equals("null"))
                    getProvinceMapInformation().setContinent(null);
                else
                    getProvinceMapInformation().getContinent().setKeyName(value);
                return true;
            case "climate":
                if (value.equals("null"))
                    getProvinceMapInformation().setClimate(null);
                else
                    getProvinceMapInformation().getClimate().setKeyName(value);
                return true;
            case "trade_node":
                if (value.equals("null"))
                    getProvinceMapInformation().setTradeNode(null);
                else
                    getProvinceMapInformation().getTradeNode().setKeyName(value);
                return true;
            case "lake":
                if (value.equals("null"))
                    getProvinceMapInformation().setWater(null);
                else
                    getProvinceMapInformation().getWater().setKeyName(value);
                return true;
            case "sea":
                if (value.equals("null"))
                    getProvinceMapInformation().setWater(null);
                else
                    getProvinceMapInformation().getWater().setKeyName(value);
                return true;
        }

        return false;
    }


    @Override
    public int compareTo(Province o)
    {
        //ascending order
        return this.getID() - o.getID();
    }
}
