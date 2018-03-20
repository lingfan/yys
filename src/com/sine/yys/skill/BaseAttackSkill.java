package com.sine.yys.skill;

import com.sine.yys.inter.AttackInfo;
import com.sine.yys.inter.DebuffEffect;
import com.sine.yys.skill.model.AttackInfoImpl;

import java.util.Collection;

/**
 * 攻击技能。
 * <p>
 * 对目标造成伤害的技能。
 * 定义了一个通用的技能系数；默认无附带负面效果；
 */
public abstract class BaseAttackSkill extends BaseActiveSkill {
    /**
     * 技能系数。
     * 仅仅提供一个比较通用的系数接口。部分技能可能有较复杂的逻辑，包含多个系数。
     *
     * @return 系数。1表示100%。
     */
    public abstract double getCoefficient();

    /**
     * 获取攻击时附带的debuff效果。默认无效果。
     */
    public Collection<DebuffEffect> getDebuffEffects() {
        return null;
    }

    public AttackInfo getAttack() {
        return new AttackInfoImpl(getDebuffEffects(), getCoefficient());
    }
}
