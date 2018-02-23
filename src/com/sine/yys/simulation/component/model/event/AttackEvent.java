package com.sine.yys.simulation.component.model.event;

import com.sine.yys.simulation.component.Controller;
import com.sine.yys.simulation.component.Entity;

/**
 * 攻击目标事件，多段攻击也算。
 * 用于附加效果，目前在攻击前触发。
 */
public class AttackEvent extends BaseVectorEvent {
    public AttackEvent(Controller controller, Entity entity, Entity target) {
        super(controller, entity, target);
    }
}
