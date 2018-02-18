package com.sine.yys.simulation.model.skill;

import com.sine.yys.simulation.info.Named;

/**
 * 技能。
 * 获取CD等，提供step函数。
 */
public interface Skill extends Named {
    String getDetail();

    int getCD();

    int getMAXCD();

    /**
     * 每回合调用，以减少CD。
     *
     * @return 剩余CD。0表示可用
     */
    int step();
}
