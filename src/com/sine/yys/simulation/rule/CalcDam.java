package com.sine.yys.simulation.rule;

import com.sine.yys.simulation.info.AttackInfo;
import com.sine.yys.simulation.info.IProperty;

/**
 * 伤害计算。
 */
public class CalcDam {
    /**
     * @param defense 防御值。
     * @return 受伤百分比，即受到伤害时要乘以的系数。
     */
    public static double injuryPct(double defense) {
        return 300.0 / (300.0 + defense);
    }

    /**
     * 计算某式神攻击目标的期望伤害。
     * 包括暴击的计算（但没有反馈信息）。
     */
    public static double expect(IProperty self, IProperty target, AttackInfo attackInfo, boolean critical) {
        final double criticalCoefficient = critical ? self.getCriticalDamage() : 1.0;
        final double finalDefense = (target.getDefense() - attackInfo.getIgnoreDefense()) * (1.0 - attackInfo.getIgnoreDefensePct());
        return attackInfo.getCoefficient() * injuryPct(finalDefense) * self.getAttack() * criticalCoefficient;
    }
}
