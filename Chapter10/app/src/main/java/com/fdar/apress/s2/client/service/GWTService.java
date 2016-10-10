package com.fdar.apress.s2.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.fdar.apress.s2.client.GWTEventDTO;

public interface GWTService extends RemoteService {

    GWTEventDTO[] findRecentEvents(int max);
}
