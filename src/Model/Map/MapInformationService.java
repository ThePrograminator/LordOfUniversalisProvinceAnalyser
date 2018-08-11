package Model.Map;

import Model.Province;
import Model.ProvinceInformation.Building;
import Model.RGB;

import java.util.ArrayList;

public class MapInformationService {
    private static MapInformationService instance = new MapInformationService();

    public static MapInformationService getInstance() {
        return instance;
    }

    private ArrayList<RGB> rgbList;
    private ArrayList<Area> areaList;
    private ArrayList<Region> regionList;
    private ArrayList<SuperRegion> superRegionList;
    private ArrayList<Continent> continentList;
    private ArrayList<Climate> climateList;
    private ArrayList<TradeNode> tradeNodeList;
    private ArrayList<Water> waterProvinceList;
    private ArrayList<Building> buildingList;
    private ArrayList<Province> provinceList;

    private MapInformationService()
    {
        rgbList = new ArrayList<>();
        areaList = new ArrayList<>();
        regionList = new ArrayList<>();
        superRegionList = new ArrayList<>();
        continentList = new ArrayList<>();
        climateList = new ArrayList<>();
        tradeNodeList = new ArrayList<>();
        waterProvinceList = new ArrayList<>();
        buildingList = new ArrayList<>();
        provinceList = new ArrayList<>();
    }

    public ArrayList<RGB> getRgbList() {
        return rgbList;
    }

    public void setRgbList(ArrayList<RGB> rgbList) {
        this.rgbList = rgbList;
    }

    public ArrayList<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(ArrayList<Area> areaList) {
        this.areaList = areaList;
    }

    public ArrayList<Region> getRegionList() {
        return regionList;
    }

    public void setRegionList(ArrayList<Region> regionList) {
        this.regionList = regionList;
    }

    public ArrayList<SuperRegion> getSuperRegionList() {
        return superRegionList;
    }

    public void setSuperRegionList(ArrayList<SuperRegion> superRegionList) {
        this.superRegionList = superRegionList;
    }

    public ArrayList<Continent> getContinentList() {
        return continentList;
    }

    public void setContinentList(ArrayList<Continent> continentList) {
        this.continentList = continentList;
    }

    public ArrayList<Climate> getClimateList() {
        return climateList;
    }

    public void setClimateList(ArrayList<Climate> climateList) {
        climateList = climateList;
    }

    public ArrayList<TradeNode> getTradeNodeList() {
        return tradeNodeList;
    }

    public void setTradeNodeList(ArrayList<TradeNode> tradeNodeList) {
        this.tradeNodeList = tradeNodeList;
    }

    public ArrayList<Water> getWaterProvinceList() {
        return waterProvinceList;
    }

    public void setWaterProvinceList(ArrayList<Water> waterProvinceList) {
        this.waterProvinceList = waterProvinceList;
    }

    public ArrayList<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(ArrayList<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public ArrayList<Province> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(ArrayList<Province> provinceList) {
        this.provinceList = provinceList;
    }

    public void transferProvinceList(ArrayList<Province> provinceList) {
        this.provinceList = provinceList;

        for (Province province: this.provinceList)
        {
            for (RGB rgb : this.rgbList)
            {
                if (rgb.getProvinceId() == province.getID())
                {
                    province.setRgb(rgb);
                }
            }

            for (Area area : this.areaList)
            {
                for (Integer integer : area.getProvinceList())
                {
                    if (integer == province.getID())
                    {
                        province.getProvinceMapInformation().setArea(area);
                    }
                }
            }

            for (Region region : this.regionList)
            {
                for (Area area : region.getAreaList())
                {
                    if (province.getProvinceMapInformation().getArea() == null)
                        continue;

                    if (area == province.getProvinceMapInformation().getArea())
                    {
                        province.getProvinceMapInformation().setRegion(region);
                    }
                }
            }

            for (SuperRegion superRegion : this.superRegionList)
            {
                for (Region region : superRegion.getRegionList())
                {
                    if (region == province.getProvinceMapInformation().getRegion())
                    {
                        province.getProvinceMapInformation().setSuperRegion(superRegion);
                    }
                }
            }

            for (Continent continent : this.continentList)
            {
                for (Integer integer : continent.getProvinceList())
                {
                    if (integer == province.getID())
                    {
                        province.getProvinceMapInformation().setContinent(continent);
                    }
                }
            }

            for (TradeNode tradeNode : this.tradeNodeList)
            {
                for (Integer integer : tradeNode.getProvinceList())
                {
                    if (integer == province.getID())
                    {
                        province.getProvinceMapInformation().setTradeNode(tradeNode);
                    }
                }
            }

            for (Climate climate : this.climateList)
            {
                for (Integer integer : climate.getProvinceList())
                {
                    if (integer == province.getID())
                    {
                        province.getProvinceMapInformation().setClimate(climate);
                    }
                }
            }

            for (Water water : this.waterProvinceList)
            {
                for (Integer integer : water.getProvinceList())
                {
                    if (integer == province.getID())
                    {
                        province.getProvinceMapInformation().setWater(water);
                    }
                }
            }

            int stop = 0;
        }
    }
}
