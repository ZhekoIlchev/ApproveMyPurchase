package common;

import constant.Condition;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Type {
    CONSUMABLES(Condition.CONSUMABLES),
    CLERICAL(Condition.CLERICAL),
    GADGETS(Condition.GADGETS),
    GAMING(Condition.GAMING),
    PC(Condition.PC);

    private Map<String, Integer> conditions;

    Type(String conditionString) {
        this.setConditions(conditionString);
    }

    public Map<String, Integer> getConditions() {
        return Collections.unmodifiableMap(conditions);
    }

    private void setConditions(String condition) {
        Map<String, Integer> conditions = new HashMap<>();
        String[] tokens = condition.split(";");

        for (String token : tokens) {
            String[] currentCondition = token.split("\\s+");
            String managerType = currentCondition[0];
            Integer maxApprovalLimit = Integer.parseInt(currentCondition[1]);
            conditions.putIfAbsent(managerType, maxApprovalLimit);
        }

        this.conditions = conditions;
    }
}