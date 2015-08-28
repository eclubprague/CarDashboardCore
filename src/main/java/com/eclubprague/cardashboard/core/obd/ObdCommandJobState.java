package com.eclubprague.cardashboard.core.obd;

/**
 * Created by Lukas on 28. 8. 2015.
 */
public enum ObdCommandJobState {
    NEW,
    RUNNING,
    FINISHED,
    EXECUTION_ERROR,
    QUEUE_ERROR,
    NOT_SUPPORTED
}
