package com.sine.yys.simulation;

import com.sine.yys.entity.EntityImpl;
import com.sine.yys.entity.ShikigamiEntityImpl;
import com.sine.yys.impl.CampInfo;
import com.sine.yys.impl.EntityInfo;
import com.sine.yys.impl.EventControllerImpl;
import com.sine.yys.impl.PositionImpl;
import com.sine.yys.inter.*;
import com.sine.yys.inter.base.JSONable;
import com.sine.yys.util.JSON;
import com.sine.yys.util.RandUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class BaseCamp implements Camp, JSONable {
    protected final Logger log = Logger.getLogger(getClass().getName());
    private final EventControllerImpl eventController = new EventControllerImpl();
    private final String name;
    private final String fullName;
    private final CampInfo infos;
    private final List<PositionImpl<EntityImpl>> positions = new ArrayList<>();
    private Camp opposite;

    public BaseCamp(String name, CampInfo info) {
        this.name = name;
        this.fullName = "[" + name + "]";
        this.infos = info;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final String getFullName() {
        return fullName;
    }

    @Override
    public final List<EntityImpl> getAllAlive() {
        List<EntityImpl> entities = new ArrayList<>();
        for (Position<EntityImpl> position : positions) {
            if (position.getCurrent() != null)
                entities.add(position.getCurrent());
        }
        return entities;
    }

    @Override
    public final List<ShikigamiEntityImpl> getAllShikigami() {
        List<ShikigamiEntityImpl> entities = new ArrayList<>();
        for (Position<EntityImpl> position : positions) {
            if (position.getCurrent() instanceof ShikigamiEntityImpl)
                entities.add((ShikigamiEntityImpl) position.getCurrent());
        }
        return entities;
    }

    @Override
    public final boolean contain(Entity entity) {
        for (Position position : positions) {
            if (position.getCurrent() == entity)
                return true;
        }
        return false;
    }

    @Override
    public final Position getPosition(Entity entity) {
        for (Position position : positions) {
            if (position.getCurrent() == entity)
                return position;
        }
        return null;
    }

    @Override
    public final Position getPositionBySrc(Entity entity) {
        for (Position position : positions) {
            if (position.getSource() == entity)
                return position;
        }
        return null;
    }

    @Override
    public final EventController getEventController() {
        return eventController;
    }

    public void init(Camp enemy, Controller controller) {
        opposite = enemy;
        for (EntityInfo info : infos.infos) {
            final ShikigamiEntityImpl shikigami = new ShikigamiEntityImpl(info, infos.lifeTimes);
            shikigami.setCamp(this);
            shikigami.init(controller);
            positions.add(new PositionImpl<>(shikigami));
        }
    }

    @Override
    public final Entity randomTarget() {
        return RandUtil.choose(getAllAlive());
    }

    @Override
    public final Camp getOpposite() {
        return opposite;
    }

    @Override
    public List<ShikigamiEntityImpl> getRevivable() {
        List<ShikigamiEntityImpl> list = new ArrayList<>();
        for (PositionImpl position : positions) {
            if (position.getCurrent() == null) {
                final Entity source = position.getSource();
                if (source instanceof ShikigamiEntityImpl)
                    list.add((ShikigamiEntityImpl) source);
            }
        }
        return list;
    }

    @Override
    public ShikigamiEntity getLeastLifeShikigami() {
        double least = 1.0;
        ShikigamiEntityImpl current = null;
        for (ShikigamiEntityImpl shikigamiEntity : getAllShikigami()) {
            if (least >= shikigamiEntity.getLife()) {
                least = shikigamiEntity.getLife();
                current = shikigamiEntity;
            }
        }
        return current;
    }

    @Override
    public String toString() {
        return getFullName();
    }

    @Override
    public String toJSON() {
        return JSON.format("name", fullName, "position", positions);
    }
}
