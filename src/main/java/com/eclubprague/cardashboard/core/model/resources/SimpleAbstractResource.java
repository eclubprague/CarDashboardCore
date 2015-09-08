package com.eclubprague.cardashboard.core.model.resources;

/**
 * Created by Michael on 9. 7. 2015.
 *
 * Simple implementation of IResource interface.
 */
abstract public class SimpleAbstractResource implements IResource {
    private final int resourceId;

    public SimpleAbstractResource(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public int getResourceId() {
        return resourceId;
    }

    @Override
    public String toString() {
        return "SimpleAbstractResource{" +
                "resourceId=" + resourceId +
                '}';
    }
}
