package SearchObjects;

import Model.Map.MapInformationService;
import Model.ProvinceInformation.Building;

import java.util.ArrayList;

public class ProvinceKeyWordHandler
{
    private final String isCity = "is_city";
    private final String addCore = "add_core";
    private final String owner = "owner";
    private final String controller = "controller";
    private final String baseTax = "base_tax";
    private final String baseProduction = "base_production";
    private final String baseManpower = "base_manpower";
    private final String culture = "culture";
    private final String religion = "religion";
    private final String tradeGoods = "trade_goods";
    private final String hre = "hre";
    private final String addPermanentProvinceModifier = "add_permanent_province_modifier";
    private final String addProvinceModifier = "add_province_modifier";
    private final String discoveredBy = "discovered_by";
    private  ArrayList<String> keyWords;

    public ProvinceKeyWordHandler()
    {
        this.keyWords = new ArrayList<>();
        this.keyWords.add(isCity);
        this.keyWords.add(addCore);
        this.keyWords.add(owner);
        this.keyWords.add(controller);
        this.keyWords.add(baseTax);
        this.keyWords.add(baseProduction);
        this.keyWords.add(baseManpower);
        this.keyWords.add(culture);
        this.keyWords.add(religion);
        this.keyWords.add(tradeGoods);
        this.keyWords.add(hre);
        this.keyWords.add(addPermanentProvinceModifier);
        this.keyWords.add(addProvinceModifier);
        this.keyWords.add(discoveredBy);
    }

    public String getIsCity() {
        return isCity;
    }

    public String getAddCore() {
        return addCore;
    }

    public String getOwner() {
        return owner;
    }

    public String getController() {
        return controller;
    }

    public String getBaseTax() {
        return baseTax;
    }

    public String getBaseProduction() {
        return baseProduction;
    }

    public String getBaseManpower() {
        return baseManpower;
    }

    public String getCulture() {
        return culture;
    }

    public String getReligion() {
        return religion;
    }

    public String getTradeGoods() {
        return tradeGoods;
    }

    public String getHre() {
        return hre;
    }

    public String getAddPermanentProvinceModifier() {
        return addPermanentProvinceModifier;
    }

    public String getAddProvinceModifier() {
        return addProvinceModifier;
    }

    public String getDiscoveredBy() {
        return discoveredBy;
    }

    public ArrayList<String> getKeyWords() {
        return keyWords;
    }
}
