import java.util.HashMap;

public class Transportation {
    private String type;
    private HashMap<String, Integer> prices;
    private HashMap<String, Integer> shippingTime;

    public Transportation(String type, HashMap<String, Integer> prices, HashMap<String, Integer> shippingTime) {
        this.type = type;
        this.prices = prices;
        this.shippingTime = shippingTime;
    }

    public String getType() {
        return type;
    }

    public HashMap<String, Integer> getPrices() {
        return prices;
    }

    public HashMap<String, Integer> getShippingTime() {
        return shippingTime;
    }
}
