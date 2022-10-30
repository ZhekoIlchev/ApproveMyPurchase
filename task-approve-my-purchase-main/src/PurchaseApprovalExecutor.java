import common.Type;
import entity.Purchase;
import handlers.Approver;
import handlers.Manager;
import repository.PurchaseRepository;

/**
 * Execution class of the application.
 * Be free to modify below line 14 (bellow comment line)
 */
public class PurchaseApprovalExecutor {

    public static void execute() {
        Approver manager = new Manager();
        ApprovalChainGenerator.generate(manager);
        //Be free to modify method below this line

        PurchaseRepository purchases = new PurchaseRepository();
        for (int i = 0; i < purchases.getPurchases().size(); i++) {
            manager.approve(purchases.getPurchases().get(i));
        }

//        manager.approve(new Purchase(1, 15000.00, Type.CONSUMABLES));
//        manager.approve(new Purchase(2, 5000.00, Type.PC));
//        manager.approve(new Purchase(3, 5000.00, Type.PC));
//        manager.approve(new Purchase(4, 3000.00, Type.CLERICAL));
    }
}