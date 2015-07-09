package com.eclubprague.cardashboard.core.modules.models;

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
}
