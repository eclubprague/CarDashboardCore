package com.eclubprague.cardashboard.core.modules.base.models;

import java.io.Serializable;

/**
 * Created by Michael on 21. 7. 2015.
 * <p/>
 * Module id for maps and intents (bundles).
 */
public class ModuleId implements Serializable {
    private static int counter = 0;
    private final int id;

    private ModuleId(int id) {
        this.id = id;
    }

    public static ModuleId createNew() {
        return new ModuleId(counter++);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModuleId id1 = (ModuleId) o;

        return id == id1.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
