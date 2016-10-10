package org.apache.struts2.dispatcher.mapper;

import org.apache.struts2.dispatcher.mapper.Restful2ActionMapper;
import org.apache.struts2.dispatcher.mapper.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.net.URLDecoder;

import com.opensymphony.xwork2.config.ConfigurationManager;


public class FallbackRestful2ActionMapper extends Restful2ActionMapper {

    List<String> knownExtenstions = new ArrayList() {{ add("css"); add("html"); add("js"); }};

    String dropExtension(String name) {
        Iterator it = knownExtenstions.iterator();
        while (it.hasNext()) {
            String extension = "." + (String) it.next();
            if (name.endsWith(extension)) {
                return null;
            }
        }
        return name;
    }

    public ActionMapping getMapping(HttpServletRequest request, ConfigurationManager configManager) {

        if (!isSlashesInActionNames()) {
            throw new IllegalStateException("This action mapper requires the setting 'slashesInActionNames' to be set to 'true'");
        }

        // ---- from DefaultActionMapper ----------------------------------------------------
        ActionMapping mapping = new ActionMapping();
        String uri = getUri(request);

        uri = dropExtension(uri);
        if (uri == null) {
            return null;
        }

        parseNameAndNamespace(uri, mapping, configManager);

        handleSpecialParameters(request, mapping);

        if ( mapping == null || mapping.getName() == null) {
            return null;
        }

        // ---- from Restful2ActionMapper ---------------------------------------------------
        String actionName = mapping.getName();

        // Only try something if the action name is specified
        if (actionName != null && actionName.length() > 0) {
            int lastSlashPos = actionName.lastIndexOf('/');

            // If a method hasn't been explicitly named, try to guess using ReST-style patterns
            if (mapping.getMethod() == null) {

                if (lastSlashPos == actionName.length() -1) {

                    // Index e.g. foo/
                    if (isGet(request)) {
                        mapping.setMethod("index");

                    // Creating a new entry on POST e.g. foo/
                    } else if (isPost(request)) {
                        mapping.setMethod("create");
                    }

                } else if (lastSlashPos > -1) {
                    String id = actionName.substring(lastSlashPos+1);

                    // Viewing the form to create a new item e.g. foo/new
                    if (isGet(request) && "new".equals(id)) {
                        mapping.setMethod("editNew");

                    // Viewing an item e.g. foo/1
                    } else if (isGet(request)) {
                        mapping.setMethod("view");

                    // Removing an item e.g. foo/1
                    } else if (isDelete(request)) {
                        mapping.setMethod("remove");

                    // Updating an item e.g. foo/1
                    }  else if (isPut(request)) {
                        mapping.setMethod("update");
                    }

                    if (getIdParameterName() != null) {
                    	if (mapping.getParams() == null) {
                            mapping.setParams(new HashMap());
                        }
                    	mapping.getParams().put(getIdParameterName(), id);
                    }
                } 

                if (getIdParameterName() != null && lastSlashPos > -1) {
                	actionName = actionName.substring(0, lastSlashPos);
                }
            }

            // Try to determine parameters from the url before the action name
            int actionSlashPos = actionName.lastIndexOf('/', lastSlashPos - 1);
            if (actionSlashPos > 0 && actionSlashPos < lastSlashPos) {
                String params = actionName.substring(0, actionSlashPos);
                HashMap<String,String> parameters = new HashMap<String,String>();
                try {
                    StringTokenizer st = new StringTokenizer(params, "/");
                    boolean isNameTok = true;
                    String paramName = null;
                    String paramValue;

                    while (st.hasMoreTokens()) {
                        if (isNameTok) {
                            paramName = URLDecoder.decode(st.nextToken(), "UTF-8");
                            isNameTok = false;
                        } else {
                            paramValue = URLDecoder.decode(st.nextToken(), "UTF-8");

                            if ((paramName != null) && (paramName.length() > 0)) {
                                parameters.put(paramName, paramValue);
                            }

                            isNameTok = true;
                        }
                    }
                    if (parameters.size() > 0) {
                        if (mapping.getParams() == null) {
                            mapping.setParams(new HashMap());
                        }
                        mapping.getParams().putAll(parameters);
                    }
                } catch (Exception e) {
                    LOG.warn(e);
                }
                mapping.setName(actionName.substring(actionSlashPos+1));
            }
        }

        return mapping;
    }

}
