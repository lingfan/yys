package com.sine.yys.buff;

import com.sine.yys.inter.Entity;
import com.sine.yys.inter.IBuff;

/**
 * 单数值buff。
 * 包括攻击、防御、速度、命中抵抗等等。
 * 具体语义由子类给出。
 */
public abstract class NumIBuff extends BaseCommonIBuff {
    protected final double value;

    /**
     * @param last  持续回合数。必须为正。
     * @param name  名称。
     * @param value 数值。
     * @param src   来源式神。
     */
    public NumIBuff(int last, String name, double value, Entity src) {
        super(last, name, src);
        this.value = value;
    }

    @Override
    public int compareTo(IBuff o) {
        final double ovalue = ((NumIBuff) o).value;
        if (value == ovalue)
            return super.compareTo(o);
        return (int) (value - ovalue);
    }
}
