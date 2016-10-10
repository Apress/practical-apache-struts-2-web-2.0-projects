package com.fdar.apress.s2.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.core.client.GWT;
import com.fdar.apress.s2.client.service.GWTServiceAsync;
import com.fdar.apress.s2.client.service.GWTService;

public class EventTableWidget extends Composite {

    private EventProvider eventProvider;
    private EventTableWidget eventTable;
    private final Grid grid;

    public EventTableWidget() {
        eventProvider = new EventProvider();
        grid = new Grid();
        initWidget(grid);
    }

    protected void onLoad() {
        eventProvider.updateRowData();
    }


    private class EventProvider {

        private final GWTServiceAsync gwtService;

        public EventProvider() {
            gwtService = (GWTServiceAsync) GWT.create(GWTService.class);
            ServiceDefTarget target = (ServiceDefTarget) gwtService;
            target.setServiceEntryPoint("/app/voting/GWTService.action");
        }

        public void updateRowData() {

            gwtService.findRecentEvents(10, new AsyncCallback() {

                public void onFailure(Throwable caught) {
                    // do nothing
                }

                public void onSuccess(Object result) {
                    pushResults((GWTEventDTO[])result);
                }

            });
        }

        private void pushResults(GWTEventDTO[] events) {
            grid.resize(events.length,3);
            for (int i = 0; i<events.length; i++) {
                GWTEventDTO event = events[i];
                grid.setText(i,0, event.getEventName());
                grid.setText(i,1, event.getStartTime());
                grid.setText(i,2,event.getLocation().getCity()+", "+event.getLocation().getState());
            }
        }
    }
}

