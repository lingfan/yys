package com.sine.yys.skill.passive;

import com.sine.yys.buff.control.BingDong;
import com.sine.yys.effect.BaseDebuffEffect;
import com.sine.yys.event.AfterAddDamageEffectEvent;
import com.sine.yys.event.AttackEvent;
import com.sine.yys.inter.*;
import com.sine.yys.mitama.XueYouHun;
import com.sine.yys.transeffect.STZZ;
import com.sine.yys.util.Msg;
import com.sine.yys.util.RandUtil;

import java.util.List;

/**
 * 雪童子-霜天之织。
 * <p>
 * 当前实现非常的侵入：
 * 在{@linkplain AttackEvent 攻击时}向{@linkplain AttackType}添加{@linkplain TransferrableEffect 可传递效果}。
 * 然后需要效果作为信号触发相应事件，对可能的雪幽魂进行操作。
 * <p>
 * 规则：
 * * 攻击目标触发，不需要造成伤害。
 * * 涓流传递效果时需要对最终目标有伤害输出（不需要破盾）。
 * * 不会传递给薙魂者（薙魂后涓流不会触发）。
 * * 一次伤害中只能冰冻或者碎冰，不能同时发生；包括带雪幽魂的情况（暂定：一次攻击中没有冰冻则可继续触发雪幽魂，包括特殊情况（比如冰冻被画境抵消或者被反弹）。
 * * 打草人（未实现）也可以传递。
 * * 只能传递一次，比如从草人传递给目标，不能再经由涓流传递。
 */
public class ShuangTianZhiZhi extends BasePassiveSkill implements EventHandler<AfterAddDamageEffectEvent>, PctEffect {
    private final DebuffEffect effect = new BaseDebuffEffect(getPct(), getName()) {
        @Override
        public IBuff getDebuff(Entity self) {
            return new BingDong(getLast(), self);
        }
    };

    @Override
    public double getPct() {
        return 0.45;
    }

    public int getLast() {
        return RandUtil.success(getLast2Pct()) ? 2 : 1;
    }

    public double getBreakCoefficient() {
        return 3.0;
    }

    /**
     * @return 效果持续2回合的概率。
     */
    public double getLast2Pct() {
        return 0.5;
    }

    @Override
    public String getName() {
        return "霜天之织";
    }

    @Override
    public void doInit(Controller controller, Entity self) {
        self.getEventController().add(this);
        self.getEventController().add(new AttackHandler());
    }

    @Override
    public void handle(AfterAddDamageEffectEvent event) {
        Entity target = event.getTarget();
        log.info(Msg.trigger(getSelf(), this));
        if (target.getBuffController().contain(BingDong.class)) {
            log.info(Msg.vector(getSelf(), "对", target, "碎冰"));
            target.getBuffController().remove(BingDong.class);
            removeXueYouHun(event.getEffects());
            event.multiplyCoefficient(getBreakCoefficient());
            return;
        }
        getController().applyDebuff(getSelf(), target, effect);
        if (target.getBuffController().contain(BingDong.class)) {
            log.info(Msg.vector(getSelf(), "冰冻", target));
            removeXueYouHun(event.getEffects());
        }
    }

    private void removeXueYouHun(List<DebuffEffect> effects) {
        for (DebuffEffect debuffEffect : effects) {
            if (XueYouHun.class.isAssignableFrom(debuffEffect.getClass())) {
                log.fine("本次攻击取消雪幽魂效果");
                effects.remove(debuffEffect);
                break;
            }
        }
    }

    class AttackHandler implements EventHandler<AttackEvent> {
        @Override
        public void handle(AttackEvent event) {
            event.getAttackType().getEffects().put(STZZ.class, new STZZ(getSelf()));
        }
    }
}
