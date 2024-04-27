public class TransportationService {
    public void showPrice(Transportation transportation) {
        System.out.println("\nThis is price for transportation service");
        transportation.getPrices().forEach((k, v) -> System.out.println(k + ": " + v));
    }
    public void showShippingTime(Transportation transportation) {
        System.out.println("\nThis is shipping time for transportation service");
        transportation.getShippingTime().forEach((k, v) -> System.out.println(k + ": " + v));
    }
    public void showGuideLine() {
        System.out.println("\nThis is guideline for transportation service");
    }
    public TransportationService classifyServiceType(Transportation transportation) {
        return switch (transportation.getType()) {
            case "Plane" -> new PlaneService();
            case "Ship" -> new ShipService();
            default -> new TransportationService();
        };
    }
}
