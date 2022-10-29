package handlers;

import common.Type;
import entity.Purchase;

public abstract class Approver {

    protected Approver next;

    /**
     * If needed, be free to change signature of abstract methods.
     */
    public void approve(Purchase purchase) {
        if (canApprove(purchase)) {
            System.out.printf("%s approved purchase with id %d that costs %.2f%n",
                    this.getClass().getSimpleName(),
                    purchase.getId(),
                    purchase.getCost());

            return;
        }

        System.out.printf("Purchase with id %d needs approval from higher position than %s.%n",
                purchase.getId(),
                this.getClass().getSimpleName());
        next.approve(purchase);
    }

    protected boolean canApprove(Purchase purchase) {
        Integer maxApprovalLimit = Type.valueOf(purchase.getType().name())
                .getConditions()
                .get(this.getClass().getSimpleName());

        if (maxApprovalLimit != null && purchase.getCost() <= maxApprovalLimit) {
            return true;
        }

        return false;
    }

    /**
     * Method used for registering next approver level.
     * DO NOT CHANGE IT.
     */
    public Approver registerNext(Approver next) {
        this.next = next;
        return next;
    }
}