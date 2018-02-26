package com.sine.yys.buff.debuff;

import com.sine.yys.buff.BaseIBuff;
import com.sine.yys.inter.DamageController;
import com.sine.yys.inter.Entity;
import com.sine.yys.util.Msg;

/**
 * 持续伤害基类。
 */
public abstract class BaseDoT extends BaseIBuff implements DoT {
    private int last;

    /**
     * @param last 持续回合数。必须为正。
     * @param name buff名称。
     * @param self
     */
    public BaseDoT(int last, String name, Entity src) {
        super(name, src);
        if (last <= 0)
            throw new RuntimeException("buff持续回合不为正。");
        this.last = last;
    }

    @Override
    public final int getLast() {
        return last;
    }

    @Override
    protected final int doBeforeAction(DamageController controller, Entity self) {
        if (last <= 0) {
            log.severe("异常调用beforeAction()，buff已结束");
            return 0;
        }
        last -= 1;
        log.info(Msg.info(self, "受到持续伤害效果 " + getName()));
        double damage = handle(self);
        controller.directDamage(self, (int) damage);
        return last;
    }

    @Override
    protected final int doAfterAction(DamageController controller, Entity self) {
        return last;
    }
}
