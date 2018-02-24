package com.sine.yys.inter;

import com.sine.yys.info.Combinable;
import com.sine.yys.info.IBuffProperty;

/**
 * buff和debuff控制器（包括护盾），包括添加、查找等操作。
 * 通过{@link IBuffProperty}接口返回所有buff相应属性的合计。
 */
public interface BuffController extends IBuffProperty {
    <T> void add(Combinable<T> buff);

    /**
     * 获取唯一的buff，不包括附加buff。
     */
    <T> T get(Class<T> clazz);

    /**
     * @return 是否存在封印御魂buff。
     */
    boolean mitamaSealed();

    /**
     * @return 是否存在封印被动buff。
     */
    boolean passiveSealed();

    /**
     * 移除特定类型的buff。
     */
    <T> void remove(Class<T> clazz);
}
