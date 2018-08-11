package Model.ProvinceInformation;

public class ProvinceCitizens
{
    private String culture;
    private String religion;

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

    @Override
    public String toString() {
        String toString;
        toString = "culture = " +  culture + "\n";
        toString += "religion = " +  religion + "\n" + "\n";

        return  toString;
    }
}
