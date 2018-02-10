package com.sine.yys.simulation.component.event;

import com.sine.yys.simulation.model.entity.Entity;

public class CommonAttackEvent implements Event {
    private Entity self;
    private Entity target;

    public CommonAttackEvent(Entity self, Entity target) {
        this.self = self;
        this.target = target;
    }

    public Entity getSelf() {
        return self;
    }

    public Entity getTarget() {
        return target;
    }
}
