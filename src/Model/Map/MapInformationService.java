package Model.Map;

import Model.RGB;

import java.util.ArrayList;
import java.util.TreeMap;

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
}
