package Model;

public class Development
{
    private int baseTax;
    private int baseProduction;
    private int baseManpower;
    private String tradeGoods;

    public Development(int baseTax, int baseProduction, int baseManpower) {
        this.baseTax = baseTax;
        this.baseProduction = baseProduction;
        this.baseManpower = baseManpower;
    }

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

    public String getTradeGoods() {
        return tradeGoods;
    }

    public void setTradeGoods(String tradeGoods) {
        this.tradeGoods = tradeGoods;
    }
}
