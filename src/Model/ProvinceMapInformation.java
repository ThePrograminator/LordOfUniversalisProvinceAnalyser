package Model;

import Model.Map.*;

public class ProvinceMapInformation
{
    private Area area;
    private Region region;
    private SuperRegion superRegion;
    private Continent continent;
    private TradeNode tradeNode;
    private Climate climate;
    private Water water;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public SuperRegion getSuperRegion() {
        return superRegion;
    }

    public void setSuperRegion(SuperRegion superRegion) {
        this.superRegion = superRegion;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public TradeNode getTradeNode() {
        return tradeNode;
    }

    public void setTradeNode(TradeNode tradeNode) {
        this.tradeNode = tradeNode;
    }

    public Climate getClimate() {
        return climate;
    }

    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    public Water getWater() {
        return water;
    }

    public void setWater(Water water) {
        this.water = water;
    }
}
