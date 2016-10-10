package com.fdar.apress.s2.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.core.client.EntryPoint;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTClient implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        RootPanel.get("slot1").add(new EventTableWidget());
    }

}
