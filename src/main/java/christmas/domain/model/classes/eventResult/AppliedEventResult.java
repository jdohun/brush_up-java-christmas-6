package christmas.domain.model.classes.eventResult;

import christmas.domain.model.enums.AppliedEventPolicyName;

import java.util.Map;
import java.util.Optional;

public class AppliedEventResult {
    private Optional<Map<AppliedEventPolicyName, EventResult>> appliedEventResult;

    public AppliedEventResult(Optional<Map<AppliedEventPolicyName, EventResult>> appliedEventResult) {
        this.appliedEventResult = appliedEventResult;
    }

    public boolean isPresent() {
        return appliedEventResult.isPresent();
    }

    public boolean isEmpty() {
        return appliedEventResult.isEmpty();
    }

    public Map<AppliedEventPolicyName, EventResult> get() {
        return appliedEventResult.get();
    }

    public void toDto() {
        
    }
}
