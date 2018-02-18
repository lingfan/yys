package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.info.AttackInfoImpl;
import com.sine.yys.simulation.model.TargetResolver;
import com.sine.yys.simulation.model.entity.BaseEntity;
import com.sine.yys.simulation.model.targetresolver.EnemyEntityResolver;

/**
 * 姑获鸟-天翔鹤斩。
 */
public class TianXiangHeZhan extends SimpleGroupAttack {
    @Override
    public String getName() {
        return "天翔鹤斩";
    }

    @Override
    public TargetResolver getTargetResolver() {
        return new EnemyEntityResolver();
    }

    @Override
    public double getCoefficient() {
        return 0.33 * 1.24;
    }

    public double getFinalCoefficient() {
        return 0.88 * 1.24;
    }

    @Override
    public int getTimes() {
        return 3;
    }

    @Override
    public void doApply(BaseEntity target) {
        super.doApply(target);
        getSelf().attack(target, new AttackInfoImpl(getFinalCoefficient()));
    }
}
