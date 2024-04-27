public class PlaneService extends TransportationService{
    public void showPrice(Transportation transportation) {
        System.out.println("\nThis is price for Plane service");
        transportation.getPrices().forEach((k, v) -> System.out.println(k + ": " + v));
    }
    public void showShippingTime(Transportation transportation) {
        System.out.println("\nThis is shipping time for Plane service");
        transportation.getShippingTime().forEach((k, v) -> System.out.println(k + ": " + v));
    }
    public void showGuideLine() {
        System.out.println("\nThis is guideline for Plane service");
    }
}
