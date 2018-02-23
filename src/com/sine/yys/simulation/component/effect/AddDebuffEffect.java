package com.sine.yys.simulation.component.effect;

import com.sine.yys.simulation.component.model.EventHandler;
import com.sine.yys.simulation.component.model.buff.Debuff;
import com.sine.yys.simulation.component.model.event.DamageEvent;
import com.sine.yys.simulation.info.PctEffect;

/**
 * 造成伤害时概率附加效果。
 */
public abstract class AddDebuffEffect extends BaseEffect implements PctEffect, EventHandler<DamageEvent> {
    private final double pct;

    AddDebuffEffect(double pct, String name) {
        super(name);
        this.pct = pct;
    }

    @Override
    public final double getPct() {
        return pct;
    }

    /**
     * 子类重写此函数，以定义附加的buff。
     */
    public abstract Debuff getDebuff();

    @Override
    public void handle(DamageEvent event) {
        event.getController().applyDebuff(this, event.getTarget(), getDebuff());
    }
}
