package com.eclubprague.cardashboard.core.modules.base.models;

import java.io.Serializable;

/**
 * Created by Michael on 28.08.2015.
 */
public class ViewId implements Serializable {
    private static int counter = 0;
    private final int id;

    private ViewId(int id) {
        this.id = id;
    }

    public static ViewId createNew() {
        return new ViewId(counter++);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewId id1 = (ViewId) o;

        return id == id1.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "ViewId{" +
                "id=" + id +
                '}';
    }
}
