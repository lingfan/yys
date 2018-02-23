package com.sine.yys.event;

import com.sine.yys.inter.Controller;

/**
 * 战斗开始事件。
 * 触发火灵、被动（龙首之玉、福运昌隆、赤团华等）等等。
 */
public class BattleStartEvent extends BaseEvent implements Event {
    public BattleStartEvent(Controller controller) {
        super(controller);
    }
}
