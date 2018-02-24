package com.sine.yys.buff.buff;

import com.sine.yys.buff.NumIBuff;

/**
 * 防御增减buff（按百分比）。
 */
public abstract class DefenseIBuff extends NumIBuff {
    /**
     * @param last   持续回合数。必须为正。
     * @param defPct 防御百分比。
     */
    public DefenseIBuff(int last, String name, double defPct) {
        super(last, name, defPct);
    }

    @Override
    public double getDefPct() {
        return value;
    }
}
