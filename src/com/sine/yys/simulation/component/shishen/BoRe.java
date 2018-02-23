package com.sine.yys.simulation.component.shishen;

import com.sine.yys.simulation.component.shishen.skill.GuiXi;
import com.sine.yys.simulation.component.shishen.skill.GuiZhiJiaMian;
import com.sine.yys.simulation.component.shishen.skill.JiHenZhiXin;

import java.util.Arrays;

/**
 * 般若。
 */
public class BoRe extends BaseShiShen {
    public BoRe() {
        super(Arrays.asList(new GuiZhiJiaMian(), new JiHenZhiXin(), new GuiXi()), "般若");
    }
}
