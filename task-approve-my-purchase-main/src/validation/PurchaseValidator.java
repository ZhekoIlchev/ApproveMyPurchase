package validation;

import common.Type;

public class PurchaseValidator {
    public static boolean validatePurchaseInformation(String[] purchaseInformation) {
        if (purchaseInformation.length != 3) {
            System.out.println("Every purchase must have id, cost and type!");
            return false;
        }

        if (!isNumeric(purchaseInformation[0]) || !isNumeric(purchaseInformation[1])) {
            System.out.println("Id or cost are not valid!");
            return false;
        }

        if (Integer.parseInt(purchaseInformation[0]) <= 0 || Double.parseDouble(purchaseInformation[1]) <= 0) {
            System.out.println("Purchase id and cost must be greater than zero.");
            return false;
        }

        if (checkTypeIsValid(purchaseInformation[2]) == null) {
            System.out.println("Type of purchase is not valid!");
            System.out.println("Type of purchase must be one of these: consumables, clerical, gadgets, gaming, pc");
            return false;
        }

        return true;
    }

    private static boolean isNumeric(String strNum) {
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

    private static Type checkTypeIsValid(String name) {
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