package com.sine.yys.simulation.component.model.event;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.Entity;

/**
 * 被暴击事件。
 */
public class BeCriticalEvent extends BaseVectorEvent implements Event {
    public BeCriticalEvent(Controller controller, Entity entity, Entity target) {
        super(controller, entity, target);
    }
}
