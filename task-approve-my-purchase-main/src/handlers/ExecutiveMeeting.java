package handlers;

import entity.Purchase;

/**
 * Used as a fallback in approval chain.
 * Should not contain any additional logic.
 * If abstract methods are changed, be free to edit signatures.
 */
public class ExecutiveMeeting extends Approver {
    private static final ExecutiveMeeting INSTANCE = new ExecutiveMeeting();

    public static ExecutiveMeeting getInstance() {
        return INSTANCE;
    }

    @Override
    public void approve(Purchase purchase) {

        System.out.printf("Purchase with id %d that costs %.2f requires an approval of executive meeting.%n",
                purchase.getId(),
                purchase.getCost());
    }
}