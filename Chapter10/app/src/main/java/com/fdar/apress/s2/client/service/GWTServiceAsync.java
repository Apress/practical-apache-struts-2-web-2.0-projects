package com.fdar.apress.s2.client.service;


import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GWTServiceAsync {

    void findRecentEvents(int max, AsyncCallback callback);
}
