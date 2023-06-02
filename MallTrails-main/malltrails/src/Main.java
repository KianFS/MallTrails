import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class MallNavigationApp {
    private static Map<Integer, String> storeMap;
    private static Map<Integer, String[]> brandMap;
    private static Map<String, Double> ratingMap;
    private static Map<String, String> directionMap;
    private static Map<DayOfWeek, String> workingHoursMap;

    public static void main(String[] args) {
        initializeData();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("----- Mall Navigation App -----");
            displayStoreList();
            System.out.print("Select a store (1-" + storeMap.size() + ") or enter '0' to exit: ");
            int storeNumber = Integer.parseInt(scanner.nextLine());

            if (storeNumber == 0) {
                exit = true;
                continue;
            }

            String storeName = storeMap.get(storeNumber);

            if (storeName != null) {
                displayBrands(storeName);
                System.out.print("Select a brand (1-" + brandMap.get(storeNumber).length + ") or enter '0' to go back: ");
                int brandNumber = Integer.parseInt(scanner.nextLine());

                if (brandNumber == 0) {
                    continue;
                }

                String brand = brandMap.get(storeNumber)[brandNumber - 1];

                if (brand != null) {
                    displayRating(storeName, brand);
                    displayWorkingHours(storeName, brand);
                } else {
                    System.out.println("Invalid brand selection. Please try again.");
                }
            } else {
                System.out.println("Invalid store selection. Please try again.");
            }

            System.out.println("--------------------------------");
        }

        scanner.close();
    }

    private static void initializeData() {
        storeMap = new HashMap<>();
        storeMap.put(1, "Forum Bornova");
        storeMap.put(2, "Agora AVM");
        storeMap.put(3, "Optimum Outlet İzmir");
        storeMap.put(4, "MaviBahçe Shopping Center");
        storeMap.put(5, "İzmir Optimum Outlet Center");
        storeMap.put(6, "Ege Park Outlet Center");
        storeMap.put(7, "Pulse İzmir");
        storeMap.put(8, "Folkart Time Outlet Center");
        storeMap.put(9, "Kipaş Holding Outlet Center");
        storeMap.put(10, "Bayraklı Mal");

        brandMap = new HashMap<>();
        brandMap.put(1, new String[]{"Zara", "H&M"});
        brandMap.put(2, new String[]{"Mango", "LC Waikiki"});
        brandMap.put(3, new String[]{"Bershka", "Pull&Bear"});
        brandMap.put(4, new String[]{"Massimo Dutti", "Stradivarius"});
        brandMap.put(5, new String[]{"Nike", "Adidas"});
        brandMap.put(6, new String[]{"Marks & Spencer", "Deichmann"});
        brandMap.put(7, new String[]{"Sephora", "MediaMarkt"});
        brandMap.put(8, new String[]{"Teknosa", "Koton"});

        ratingMap = new HashMap<>();
        ratingMap.put("Forum Bornova-Zara", 4.2);
        ratingMap.put("Forum Bornova-H&M", 4.5);
        ratingMap.put("Agora AVM-Mango", 4.1);
        ratingMap.put("Agora AVM-LC Waikiki", 4.7);
        ratingMap.put("Optimum Outlet İzmir-Bershka", 4.3);
        ratingMap.put("Optimum Outlet İzmir-Pull&Bear", 4.4);
        ratingMap.put("MaviBahçe Shopping Center-Massimo Dutti", 4.0);
        ratingMap.put("MaviBahçe Shopping Center-Stradivarius", 4.6);
        ratingMap.put("İzmir Optimum Outlet Center-Nike", 3.9);
        ratingMap.put("İzmir Optimum Outlet Center-Adidas", 3.8);
        ratingMap.put("Ege Park Outlet Center-Marks & Spencer", 4.2);
        ratingMap.put("Ege Park Outlet Center-Deichmann", 4.5);
        ratingMap.put("Pulse İzmir-Sephora", 4.1);
        ratingMap.put("Pulse İzmir-MediaMarkt", 4.7);
        ratingMap.put("Folkart Time Outlet Center-Teknosa", 4.3);
        ratingMap.put("Folkart Time Outlet Center-Koton", 4.4);

        directionMap = new HashMap<>();
        directionMap.put("Forum Bornova", "Sample direction for Forum Bornova");
        directionMap.put("Agora AVM", "Sample direction for Agora AVM");
        directionMap.put("Optimum Outlet İzmir", "Sample direction for Optimum Outlet İzmir");
        directionMap.put("MaviBahçe Shopping Center", "Sample direction for MaviBahçe Shopping Center");
        directionMap.put("İzmir Optimum Outlet Center", "Sample direction for İzmir Optimum Outlet Center");
        directionMap.put("Ege Park Outlet Center", "Sample direction for Ege Park Outlet Center");
        directionMap.put("Pulse İzmir", "Sample direction for Pulse İzmir");
        directionMap.put("Folkart Time Outlet Center", "Sample direction for Folkart Time Outlet Center");

        workingHoursMap = new HashMap<>();
        workingHoursMap.put(DayOfWeek.MONDAY, "10:00 - 20:00");
        workingHoursMap.put(DayOfWeek.TUESDAY, "10:00 - 20:00");
        workingHoursMap.put(DayOfWeek.WEDNESDAY, "10:00 - 20:00");
        workingHoursMap.put(DayOfWeek.THURSDAY, "10:00 - 20:00");
        workingHoursMap.put(DayOfWeek.FRIDAY, "10:00 - 20:00");
        workingHoursMap.put(DayOfWeek.SATURDAY, "10:00 - 18:00");
        workingHoursMap.put(DayOfWeek.SUNDAY, "Closed");
    }

    private static void displayStoreList() {
        System.out.println("----- Stores -----");
        for (Map.Entry<Integer, String> entry : storeMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    private static void displayBrands(String storeName) {
        System.out.println("----- Brands at " + storeName + " -----");
        int storeNumber = getStoreNumber(storeName);

        if (storeNumber != -1) {
            String[] brands = brandMap.get(storeNumber);

            if (brands != null) {
                for (int i = 0; i < brands.length; i++) {
                    System.out.println((i + 1) + ". " + brands[i]);
                }
            }
        }
    }

    private static void displayRating(String storeName, String brand) {
        String key = storeName + "-" + brand;
        Double rating = ratingMap.get(key);

        if (rating != null) {
            System.out.println("Rating for " + brand + " at " + storeName + ": " + rating);
        } else {
            System.out.println("Rating information not available.");
        }
    }

    private static void displayWorkingHours(String storeName, String brand) {
        System.out.println("Working hours for " + brand + " at " + storeName + ":");
        for (Map.Entry<DayOfWeek, String> entry : workingHoursMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static int getStoreNumber(String storeName) {
        for (Map.Entry<Integer, String> entry : storeMap.entrySet()) {
            if (entry.getValue().equals(storeName)) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
