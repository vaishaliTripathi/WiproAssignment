package com.wipro.assignment.util;

import com.squareup.otto.Bus;

/**
 * Created by varora on 28-08-2018.
 */

public class OttoClient {

    private static OttoClient client;
    private MainThreadBus mainThreadBus;

    private OttoClient() {
        mainThreadBus = new MainThreadBus();
    }

    public static OttoClient getInstance() {
        if (client == null) {

            synchronized (OttoClient.class) {
                if (client == null) {
                    client = new OttoClient();
                }
            }
        }
        return client;
    }

    public Bus getOttoBus() {
        return mainThreadBus;
    }

}
