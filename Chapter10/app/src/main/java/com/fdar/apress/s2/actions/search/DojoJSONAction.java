package com.fdar.apress.s2.actions.search;

import com.googlecode.jsonplugin.annotations.SMDMethod;
import com.googlecode.jsonplugin.annotations.JSON;
import com.fdar.apress.s2.actions.search.ShowRecentEventsAction;
import com.fdar.apress.s2.services.EventDTO;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Ian Roughley
 * @version $Id$
 */
public class DojoJSONAction extends ShowRecentEventsAction {

    @JSON(name="findRecentEvents")
    public List<EventDTO> findRecentEvents(int max) throws Exception {
        execute();
        return getDtoResults();
    }

}
