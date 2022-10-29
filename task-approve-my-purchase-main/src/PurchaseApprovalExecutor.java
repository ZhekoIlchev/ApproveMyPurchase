import common.Type;
import entity.Purchase;
import handlers.Approver;
import handlers.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Execution class of the application.
 * Be free to modify below line 14 (bellow comment line)
 */
public class PurchaseApprovalExecutor {

    public static void execute() {
        Approver manager = new Manager();
        ApprovalChainGenerator.generate(manager);
        //Be free to modify method below this line

        List<Purchase> allPurchases = PurchaseApprovalExecutor.readPurchase();
        for (Purchase allPurchase : allPurchases) {
            manager.approve(allPurchase);
        }

//        manager.approve(new Purchase(1L, 15000.00, Type.CONSUMABLES));
//        manager.approve(new Purchase(2L, 5000.00, Type.PC));
//        manager.approve(new Purchase(3L, 5000.00, Type.PC));
//        manager.approve(new Purchase(4L, 3000.00, Type.CLERICAL));
    }

    private static List<Purchase> readPurchase() {
        Scanner scanner = new Scanner(System.in);
        printInformationForUser();

        String purchaseString;
        List<Purchase> purchases = new ArrayList<>();
        while (!"END".equals(purchaseString = scanner.nextLine().toUpperCase(Locale.ROOT))) {
            String[] purchaseInformation = purchaseString.split("\\s+");

            if (!validatePurchaseInformation(purchaseInformation)) {
                continue;
            }

            Integer id = Integer.parseInt(purchaseInformation[0]);
            Double cost = Double.parseDouble(purchaseInformation[1]);
            Type type = Type.valueOf(purchaseInformation[2]);

            Purchase purchase = new Purchase(id, cost, type);
            purchases.add(purchase);
        }
        return purchases;
    }

    private static void printInformationForUser() {
        System.out.println("Please enter information for purchase in the following format: id (Numeric value) cost (Numeric value) type (Possible types are: consumables, clerical, gadgets, gaming, pc).");
        System.out.println("Separate your purchases with key \"Enter\".");
        System.out.println("When you are done with all your purchases, please type \"End\".");
        System.out.println("Example for purchase: 1 1000 consumables");
    }

    private static boolean validatePurchaseInformation(String[] purchaseInformation) {
        if (purchaseInformation.length != 3) {
            System.out.println("Every purchase must have id, cost and type!");
            return false;
        }

        if (!isNumeric(purchaseInformation[0]) || !isNumeric(purchaseInformation[1])) {
            System.out.println("Id or cost are not valid!");
            return false;
        }

        if (Integer.parseInt(purchaseInformation[0]) <= 0 || Double.parseDouble(purchaseInformation[1]) <= 0) {
            System.out.println("Purchase id and cost should be greater then zero.");
            return false;
        }

        if (checkTypeIsValid(purchaseInformation[2]) == null) {
            System.out.println("Type of purchase is not valid!");
            System.out.println("Type of purchase should be one of these: consumables, clerical, gadgets, gaming, pc");
            return false;
        }

        return true;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double number = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static Type checkTypeIsValid(String name) {
        Type result = null;
        for (Type type : Type.values()) {
            if (type.name().equalsIgnoreCase(name)) {
                result = type;
                break;
            }
        }
        return result;
    }
}