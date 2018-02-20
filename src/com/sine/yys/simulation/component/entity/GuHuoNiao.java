package com.sine.yys.simulation.component.entity;

import com.sine.yys.simulation.component.skill.SanJian;
import com.sine.yys.simulation.component.skill.TianXiangHeZhan;
import com.sine.yys.simulation.component.skill.XieZhan;

import java.util.Arrays;

/**
 * 姑获鸟。
 */
public class GuHuoNiao extends ShiShen {
    public GuHuoNiao() {
        super(Arrays.asList(new SanJian(), new XieZhan(), new TianXiangHeZhan()), "姑获鸟");
    }
}
