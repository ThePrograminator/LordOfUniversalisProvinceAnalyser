package Model.ProvinceInformation;

public class Building
{
    private int id;
    private String buildingName;

    public Building()
    {
    }

    public Building(int id)
    {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    @Override
    public String toString() {
        String toString;
        toString = buildingName + "\n" + "\n";

        return  toString;
    }
}
