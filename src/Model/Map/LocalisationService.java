package Model.Map;

import Model.Localisation.Localisation;
import Model.Province;
import Model.RGB;

import java.util.ArrayList;

public class LocalisationService
{
    private static LocalisationService instance = new LocalisationService();

    private ArrayList<Localisation> provinceLocalisation;
    private ArrayList<Localisation> areaLocalisation;
    private ArrayList<Localisation> regionLocalisation;
    private ArrayList<Localisation> superRegionLocalisation;
    private ArrayList<Localisation> continentLocalisation;
    private ArrayList<Localisation> tradeNodeLocalisation;

    public static LocalisationService getInstance()
    {
        return instance;
    }

    private LocalisationService()
    {

    }

    public ArrayList<Localisation> getProvinceLocalisation() {
        return provinceLocalisation;
    }

    public void setProvinceLocalisation(ArrayList<Localisation> provinceLocalisation) {
        this.provinceLocalisation = provinceLocalisation;
    }

    public ArrayList<Localisation> getTradeNodeLocalisation() {
        return tradeNodeLocalisation;
    }

    public void setTradeNodeLocalisation(ArrayList<Localisation> tradeNodeLocalisation) {
        this.tradeNodeLocalisation = tradeNodeLocalisation;
    }

    public ArrayList<Localisation> getAreaLocalisation() {
        return areaLocalisation;
    }

    public void setAreaLocalisation(ArrayList<Localisation> areaLocalisation) {
        this.areaLocalisation = areaLocalisation;
    }

    public ArrayList<Localisation> getRegionLocalisation() {
        return regionLocalisation;
    }

    public void setRegionLocalisation(ArrayList<Localisation> regionLocalisation) {
        this.regionLocalisation = regionLocalisation;
    }

    public ArrayList<Localisation> getSuperRegionLocalisation() {
        return superRegionLocalisation;
    }

    public void setSuperRegionLocalisation(ArrayList<Localisation> superRegionLocalisation) {
        this.superRegionLocalisation = superRegionLocalisation;
    }

    public ArrayList<Localisation> getContinentLocalisation() {
        return continentLocalisation;
    }

    public void setContinentLocalisation(ArrayList<Localisation> continentLocalisation) {
        this.continentLocalisation = continentLocalisation;
    }

    public void transferMapList(ArrayList<Localisation> localisationList)
    {
        areaLocalisation = new ArrayList<>();
        regionLocalisation = new ArrayList<>();
        superRegionLocalisation = new ArrayList<>();
        continentLocalisation = new ArrayList<>();

        ArrayList<Area> areaList = MapInformationService.getInstance().getAreaList();

        for (Localisation localisation: localisationList)
        {
            for (Area area : areaList)
            {
                if (localisation.getKeyName().equals(area.getKeyName()))
                {
                    this.areaLocalisation.add(localisation);
                }
            }
        }

        localisationList.removeAll(this.areaLocalisation);

        ArrayList<Region> regionList = MapInformationService.getInstance().getRegionList();

        for (Localisation localisation: localisationList)
        {
            for (Region region : regionList)
            {
                if (localisation.getKeyName().equals(region.getKeyName()))
                {
                    this.regionLocalisation.add(localisation);
                }
            }
        }

        localisationList.removeAll(this.regionLocalisation);

        ArrayList<SuperRegion> superRegionList = MapInformationService.getInstance().getSuperRegionList();

        for (Localisation localisation: localisationList)
        {
            for (SuperRegion superRegion : superRegionList)
            {
                if (localisation.getKeyName().equals(superRegion.getKeyName()))
                {
                    this.superRegionLocalisation.add(localisation);
                }
            }
        }

        localisationList.removeAll(this.superRegionLocalisation);

        ArrayList<Continent> continentList = MapInformationService.getInstance().getContinentList();

        for (Localisation localisation: localisationList)
        {
            for (Continent continent : continentList)
            {
                if (localisation.getKeyName().equals(continent.getKeyName()))
                {
                    this.continentLocalisation.add(localisation);
                }
            }
        }

        localisationList.removeAll(this.continentLocalisation);
    }

    public void transferProvinceList()
    {
        ArrayList<Province> provinceList = MapInformationService.getInstance().getProvinceList();

        for (Area area : MapInformationService.getInstance().getAreaList())
        {
            for (Localisation localisation : getAreaLocalisation())
            {
                if(localisation.getKeyName() == null)
                {
                    System.out.println("Localisation area is null");
                    continue;
                }

                if (localisation.getKeyName().equals(area.getKeyName()))
                {
                    area.setValueName(localisation.getValueName());
                }
            }
        }

        for (Region region : MapInformationService.getInstance().getRegionList())
        {
            for (Localisation localisation : getRegionLocalisation())
            {
                if(localisation.getKeyName() == null)
                {
                    System.out.println("Localisation region is null");
                    continue;
                }

                if (localisation.getKeyName().equals(region.getKeyName()))
                {
                    region.setValueName(localisation.getValueName());
                }
            }
        }

        for (SuperRegion superRegion : MapInformationService.getInstance().getSuperRegionList())
        {
            for (Localisation localisation : getSuperRegionLocalisation())
            {
                if(localisation.getKeyName() == null)
                {
                    System.out.println("Localisation superregion is null");
                    continue;
                }

                if (localisation.getKeyName().equals(superRegion.getKeyName()))
                {
                    superRegion.setValueName(localisation.getValueName());
                }
            }
        }

        for (Continent continent : MapInformationService.getInstance().getContinentList())
        {
            for (Localisation localisation : getContinentLocalisation())
            {
                if(localisation.getKeyName() == null)
                {
                    System.out.println("Localisation continent is null");
                    continue;
                }

                if (localisation.getKeyName().equals(continent.getKeyName()))
                {
                    continent.setValueName(localisation.getValueName());
                }
            }
        }

        for (TradeNode tradeNode : MapInformationService.getInstance().getTradeNodeList())
        {
            for (Localisation localisation : getTradeNodeLocalisation())
            {
                if(localisation.getKeyName() == null)
                {
                    System.out.println("Localisation tradenode is null");
                    continue;
                }

                if (localisation.getKeyName().equals(tradeNode.getKeyName()))
                {
                    tradeNode.setValueName(localisation.getValueName());
                }
            }
        }

        for (Province province: provinceList)
        {
            for (Localisation localisation : getProvinceLocalisation())
            {
                int provinceId = Integer.parseInt(localisation.getKeyName());
                if (provinceId == province.getID())
                {
                    province.getProvinceName().setValueName(localisation.getValueName());
                }
            }
        }
    }
}
