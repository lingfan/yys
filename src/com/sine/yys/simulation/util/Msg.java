package com.sine.yys.simulation.util;

import com.sine.yys.simulation.model.battle.ActionContext;
import com.sine.yys.simulation.model.battle.Camp;
import com.sine.yys.simulation.model.entity.Entity;
import com.sine.yys.simulation.model.skill.ActiveSkill;

public class Msg {

    public static String turn(Camp own, Entity entity) {
        return String.format("%s行动", formatCampEntity(own, entity));
    }

    public static String action(Camp own, Entity entity, ActiveSkill skill) {
        return String.format("%s使用了 %s", formatCampEntity(own, entity), skill.getName());
    }

    public static String damage(ActionContext context, Entity target, int damage) {
        String src = formatCampEntity(context.getOwn(), context.getSelf());
        String dst = formatCampEntity(context.getEnemy(), target);
        return String.format("%s攻击%s造成% 5d伤害", src, dst, damage);
    }

    private static String formatCampEntity(Camp camp, Entity entity) {
        return chStr("[" + camp.getName() + "]", 8) + chStr(entity.getName(), -9);
    }

    /**
     * 为包含中文（宽度2）的字符串，格式化到指定宽度。
     */
    public static String chStr(final String string, int width) {
        final boolean left = width < 0;
        if (left)
            width = -width;
        char[] chs = string.toCharArray();
        for (char ch : chs) {
            if (isChinese(ch))
                width -= 1;
        }
        return String.format("%" + (left ? "-" : "") + width + "s", string);
    }

    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }
}
