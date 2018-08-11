package Model.ProvinceInformation;

public class City
{
    private boolean isCity;

    public boolean isCity() {
        return isCity;
    }

    public void setIsCity(boolean isCity) {
        this.isCity = isCity;
    }

    @Override
    public String toString()
    {
        if(isCity)
            return  "is_city = yes" + "\n" + "\n";
        else
            return  "is_city = no" + "\n" + "\n";
    }
}
