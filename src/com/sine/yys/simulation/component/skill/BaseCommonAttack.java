package com.sine.yys.simulation.component.skill;

import com.sine.yys.simulation.component.Entity;
import com.sine.yys.simulation.component.model.event.CommonAttackEvent;
import com.sine.yys.simulation.component.skill.targetresolver.EnemyEntityResolver;
import com.sine.yys.simulation.component.skill.targetresolver.TargetResolver;
import com.sine.yys.simulation.info.AttackInfo;
import com.sine.yys.simulation.info.AttackInfoImpl;

/**
 * 普攻通用逻辑。
 * 在普攻结束后触发普攻事件。
 */
public abstract class BaseCommonAttack extends BaseActiveSkill implements CommonAttack {
    @Override
    public final int getFire() {
        return 0;
    }

    /**
     * @param self 自身
     * @return 攻击次数（段数）。
     */
    public int getTimes(Entity self) {
        return 1;
    }

    @Override
    public double getCoefficient() {
        return 1.0;
    }

    /**
     * 攻击信息。
     * 默认以getCoefficient的系数，没有破防效果。
     *
     * @return 攻击信息。
     */
    @Override
    public AttackInfo getAttack() {
        return new AttackInfoImpl(getCoefficient());
    }

    @Override
    public final TargetResolver getTargetResolver() {
        return new EnemyEntityResolver();
    }

    @Override
    public final void afterApply(Entity self, Entity target) {
        // 触发普攻事件
        self.getCamp().getEventController().trigger(new CommonAttackEvent(self, target));
    }

    /**
     * 协战的具体操作。
     * 默认调用doApply。具体技能根据需要重写。
     *
     * @param self   自身。
     * @param target 协战目标。
     */
    @Override
    public void xieZhan(Entity self, Entity target) {
        doApply(self, target);
    }

    /**
     * 普攻的具体操作。无需触发普攻事件。
     * 默认以getAttack的攻击，对target攻击getTimes次。
     *
     * @param self   自身。
     * @param target 攻击目标。
     */
    @Override
    protected void doApply(Entity self, Entity target) {
        for (int i = 0; i < getTimes(self); i++) {
            if (target.isDead())
                break;
            self.attack(target, getAttack());
        }
    }
}
