package com.sine.yys.simulation.component.skill;

import com.sine.yys.simulation.component.InitContext;
import com.sine.yys.simulation.component.effect.PctEffect;
import com.sine.yys.simulation.component.model.EventHandler;
import com.sine.yys.simulation.component.model.buff.debuff.FengYin;
import com.sine.yys.simulation.component.model.event.AttackEvent;

/**
 * 般若-嫉恨之心。
 */
public class JiHenZhiXin extends BasePassiveSkill implements PassiveSkill, PctEffect, EventHandler<AttackEvent> {
    @Override
    public String getName() {
        return "嫉恨之心";
    }

    @Override
    public double getPct() {
        return 0.4;
    }

    /**
     * @return 封印持续回合数。
     */
    public int getLast() {
        return 2;
    }

    @Override
    public void handle(AttackEvent event) {
        event.getSelf().applyDebuff(this, event.getTarget(), new FengYin(getLast()));
    }

    public void init(InitContext context) {
        context.getSelf().getEventController().add(this);
    }
}
