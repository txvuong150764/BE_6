import java.util.HashMap;
import java.util.Scanner;

enum TransportType {
    PLANE,
    SHIP;
}

public class Main {
    public static void showMenu() {
        System.out.println("!!!!!!!!!!!Welcome!!!!!!!!!!!");
        System.out.println("PLANE");
        System.out.println("SHIP");
        System.out.print("Please choose shipment method: ");
    }
    public static void main(String[] args) {
        HashMap<String, Integer> planePrice = new HashMap<>();
        planePrice.put("Small Package", 10);
        planePrice.put("Medium Package", 15);
        planePrice.put("Large Package", 20);

        HashMap<String, Integer> planeShippingTime = new HashMap<>();
        planeShippingTime.put("Small Package", 2);
        planeShippingTime.put("Medium Package", 5);
        planeShippingTime.put("Large Package", 7);

        HashMap<String, Integer> shipPrice = new HashMap<>();
        shipPrice.put("Small Package", 5);
        shipPrice.put("Medium Package", 7);
        shipPrice.put("Large Package", 9);

        HashMap<String, Integer> shipShippingTime = new HashMap<>();
        shipShippingTime.put("Small Package", 10);
        shipShippingTime.put("Medium Package", 21);
        shipShippingTime.put("Large Package", 30);


        Transportation planeTransportation = new Transportation("Plane", planePrice, planeShippingTime);
        Transportation shipTransportation = new Transportation("Ship", shipPrice, shipShippingTime);
        TransportationService transportationService = new TransportationService();

        showMenu();

        Scanner sc = new Scanner(System.in);
        TransportType transportationType = TransportType.valueOf(sc.nextLine());

        Transportation transportation = null;

        switch (transportationType) {
            case PLANE -> transportation = planeTransportation;
            case SHIP -> transportation = shipTransportation;
            default -> System.out.println("The transportation type you choose does not exist. Please try again!");
        }

        transportationService = transportationService.classifyServiceType(transportation);
        transportationService.showPrice(transportation);
        transportationService.showShippingTime(transportation);
        transportationService.showGuideLine();
    }
}