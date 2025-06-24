package raf.dsw.classycraft.app.state;

import lombok.Getter;
import raf.dsw.classycraft.app.state.concrete.*;

public class StateManager {
    @Getter
    private State current = new SelectState();
    private final AddComopsitionState addComopsitionState = new AddComopsitionState();
    private final AddDependencyState addDependencyState = new AddDependencyState();
    private final AddAggregationState addAggregationState = new AddAggregationState();
    private final AddGeneralisationState addGeneralisationState = new AddGeneralisationState();
    private final AddContentState addContentState = new AddContentState();
    private final AddInterClassState addInterClassState = new AddInterClassState();
    private final DeleteState deleteState = new DeleteState();
    private final SelectState selectState = new SelectState();
    private final MoveState moveState = new MoveState();

    public void setAddComopsitionState() {
        current = addComopsitionState;
    }

    public void setAddDependencyState() {
        current = addDependencyState;
    }

    public void setAddGeneralisationState() {
        current = addGeneralisationState;
    }

    public void setAddAggregationState() {
        current = addAggregationState;
    }

    public void setAddContentState() {
        current = addContentState;
    }

    public void setAddInterClassState() {
        current = addInterClassState;
    }

    public void setDeleteState() {
        current = deleteState;
    }

    public void setSelectState() {
        current = selectState;
    }

    public void setMoveState() {
        current = moveState;
    }
}
