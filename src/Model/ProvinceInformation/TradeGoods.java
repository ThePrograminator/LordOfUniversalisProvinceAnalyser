package Model.ProvinceInformation;

public class TradeGoods {
    private String tradeGoods;

    public String getTradeGoods() {
        return tradeGoods;
    }

    public void setTradeGoods(String tradeGoods) {
        this.tradeGoods = tradeGoods;
    }

    @Override
    public String toString() {
        String toString;
        toString = "trade_goods = " +  tradeGoods + "\n" + "\n";

        return  toString;
    }
}
