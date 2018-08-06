package Model;

import javafx.util.Pair;

public class Province
{
    private int ID;
    private Owner owner;
    private Pair<String, String> provinceName;
    private Development development;
    private ProvinceCitizens provinceCitizens;
    private Discovered discovered;
    private RGB rgb;
    private ProvinceMapInformation provinceMapInformation;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public Pair getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(Pair provinceName) {
        this.provinceName = provinceName;
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

    public Discovered getDiscovered() {
        return discovered;
    }

    public void setDiscovered(Discovered discovered) {
        this.discovered = discovered;
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
}
