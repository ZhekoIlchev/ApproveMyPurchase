package repository;

import common.Type;
import entity.Purchase;
import validation.PurchaseValidator;

import java.util.*;

public class PurchaseRepository {
    private List<Purchase> purchases;

    public PurchaseRepository() {
        this.setPurchases(PurchaseRepository.read());
    }

    public List<Purchase> getPurchases() {
        return Collections.unmodifiableList(this.purchases);
    }

    private void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    private static List<Purchase> read() {
        Scanner scanner = new Scanner(System.in);
        printInformationForUser();

        String purchaseString;
        List<Purchase> purchases = new ArrayList<>();
        while (!"END".equals(purchaseString = scanner.nextLine().toUpperCase(Locale.ROOT))) {
            String[] purchaseInformation = purchaseString.split("\\s+");

            if (!PurchaseValidator.validatePurchaseInformation(purchaseInformation)) {
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
        System.out.println("Separate your purchases with pressing \"Enter\".");
        System.out.println("When you are done with all your purchases, please type \"End\".");
        System.out.println("Example for purchase: 1 1000 consumables");
    }
}