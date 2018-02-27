package com.sine.yys.rule.buff;

import com.sine.yys.inter.IBuff;

import java.util.Collection;

/**
 * 对同一类型buff属性的叠加规则，默认操作为直接加减。
 * 比如2个破防30%叠加就是破防60%。
 * 数值含义参考：
 *
 * @see com.sine.yys.info.IBuffProperty
 */
public interface Composition {
    /**
     * 计算集合中所有buff的总和。每个实例只能调用一次此函数。
     *
     * @param lists
     * @return 最终加成数值。
     * @see com.sine.yys.simulation.component.BuffControllerImpl
     */
    double calc(Collection<IBuff>... lists);
}
