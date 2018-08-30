package com.wipro.assignment.util;

/**
 * Created by varora on 28-08-2018.
 */

public class ConnectionState {

    public final static int CONNECTED           =   1;
    public final static int NOT_CONNECTED       =   2;
    private int connectionState;

    public int getConnectionState() {
        return connectionState;
    }

    public void setConnectionState(final int connectionState) {
        this.connectionState = connectionState;
    }
}
