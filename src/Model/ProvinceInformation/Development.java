package Model.ProvinceInformation;

public class Development
{
    private int baseTax;
    private int baseProduction;
    private int baseManpower;

    public int getBaseTax() {
        return baseTax;
    }

    public void setBaseTax(int baseTax) {
        this.baseTax = baseTax;
    }

    public int getBaseProduction() {
        return baseProduction;
    }

    public void setBaseProduction(int baseProduction) {
        this.baseProduction = baseProduction;
    }

    public int getBaseManpower() {
        return baseManpower;
    }

    public void setBaseManpower(int baseManpower) {
        this.baseManpower = baseManpower;
    }

    @Override
    public String toString()
    {
        String toString;
        toString = "base_tax = " +  baseTax + "\n";
        toString += "base_production = " +  baseProduction + "\n";
        toString += "base_manpower = " +  baseManpower + "\n" + "\n";

        return  toString;
    }
}
