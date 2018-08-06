package Model;

public class ProvinceCitizens
{
    private String culture;
    private String religion;
    private boolean isCity;
    private boolean hre;

    public ProvinceCitizens(String culture, String religion, boolean isCity, boolean hre) {
        this.culture = culture;
        this.religion = religion;
        this.isCity = isCity;
        this.hre = hre;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public boolean isCity() {
        return isCity;
    }

    public void setCity(boolean city) {
        isCity = city;
    }

    public boolean isHre() {
        return hre;
    }

    public void setHre(boolean hre) {
        this.hre = hre;
    }
}
