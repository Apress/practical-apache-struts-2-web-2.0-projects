/*
 * Copyright (c) 2002-2006 by OpenSymphony
 * All rights reserved.
 */
package com.opensymphony.webwork.interceptor;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.webwork.dispatcher.FlashResult;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.PreResultListener;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.OgnlValueStack;

/**
 * <!-- START SNIPPET: description -->
 * Flash interceptor ({@link FlashInterceptor}) possibly with {@link FlashResult}
 * allows current action to be available even after a redirect. It does this by
 * saving the current action into http session and pushing it back into the stack
 * next request, resulting in the nett effect of the action and its related information
 * being available across redirect.
 * <!-- END SNIPPET: description -->
 *
 *
 * <!-- START SNIPPET: parameters -->
 * <ul>
 * 	<li>key - The Http Session key under which the action will be stored,
 *                     default to {@link FlashInterceptor#DEFAULT_KEY} which is
 *                     the string '__flashAction'.</li>
 *     <li>operation - The operation mode of this interceptor, either
 *     				{@link FlashInterceptor#STORE} having a string value of 'Store' or
 *     				{@link FlashInterceptor#RETRIEVE} having a string value of 'Retrieve'
 *     				The default operation mode is {@link FlashInterceptor#RETRIEVE}</li>
 * </ul>
 * <!-- END SNIPPET: parameters -->
 *
 *
 * <!-- START SNIPPET: extending -->
 * There's no intended extension points
 * <!-- END SNIPPET: extending -->
 *
 *
 * <pre>
 * <!-- START SNIPPET: example -->
 * &lt;!-- Usage 1: (Using only Flash interceptor)  --&gt;
 * &lt;action name="store" ...&gt;
 * 	&lt;interceptor-ref name="flash"&gt;
 * 		&lt;param name="operation"&gt;Store&lt;/param&gt;
 *     &lt;/interceptor-ref&gt;
 *     &lt;interceptor-ref name="defaultStack" /&gt;
 *     &lt;result type="redirect"&gt;redirectToSomeWhere.jsp&lt;/result&gt;
 * &lt;/action&gt;
 * &lt;action name="retrieve"&gt;
 * 	&lt;interceptor-ref name="flash"&gt;
 *        &lt;param name="operation"&gt;Retrieve&lt;/param&gt;
 *     &lt;/interceptor-ref&gt;
 *     &lt;interceptor-ref name="defaultStack" /&gt;
 *     &lt;result&gt;pageWhereWeNeedFlashActionStored.jsp&lt;/result&gt;
 * &lt;/action&gt;
 *
 *
 * &lt;!-- Usage 2: (Using Flash Interceptor and Flash Result) --&gt;
 * &lt;action name="store"&gt;
 * 	<result type="flash"&lt;/redirectToSomeWhere.jsp&lt;/result&gt;
 * &lt;/action&gt;
 * &lt;action name="retrieve"&gt;
 * 	&lt;interceptor-ref name="flash"&gt;
 *        &lt;param name="operation"&gt;Retrieve&lt;/param&gt;
 *     &lt;/interceptor-ref&gt;
 *     &lt;interceptor-ref name="defaultStack" /&gt;
 *     &lt;result&gt;pageWhereWeNeedFlashActionStored.jsp&lt;/result&gt;
 * &lt;/action&gt;
 *
 * <!-- END SNIPPET: example -->
 * </pre>
 *
 *
 * @author tmjee
 * @version $Date: 2006/12/11 12:57:12 $ $Id: FlashInterceptor.java,v 1.2 2006/12/11 12:57:12 tmjee Exp $
 */
public class FlashInterceptor extends AbstractInterceptor {

    public static final String DEFAULT_KEY = "__flashAction";
    public static final String STORE = "Store";
    public static final String RETRIEVE = "Retrieve";

    private static final Log LOG = LogFactory.getLog(FlashInterceptor.class);

    private static final long serialVersionUID = -9200319895107209641L;

    private String key = DEFAULT_KEY;
    private String operation = RETRIEVE;

    public void setKey(String key) {
        this.key = key;
    }
    public String getKey() {
        return this.key;
    }

    public void setOperation(String operation) { this.operation = operation; }
    public String getOperation() { return this.operation; }


    public String intercept(ActionInvocation invocation) throws Exception {
        Map sessionMap = ActionContext.getContext().getSession();

        if (STORE.equalsIgnoreCase(operation)) {
            invocation.addPreResultListener(new PreResultListener() {
                public void beforeResult(ActionInvocation invocation, String resultCode) {
                    Map sessionMap = ActionContext.getContext().getSession();
                    Object action = invocation.getAction();
                    if (LOG.isDebugEnabled())
                        LOG.debug("inserting action ["+action+"] into session with key ["+key+"]");
                    sessionMap.put(key, action);
                }
            });
        }

        if (RETRIEVE.equalsIgnoreCase(operation)) {
            if (sessionMap.get(key) != null) {
                Object action = sessionMap.get(key);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("flash action with key ["+key+"] found in session, pushed action ["+action+"] into stack");
                }
                OgnlValueStack stack = (OgnlValueStack) ActionContext.getContext().get(ActionContext.VALUE_STACK);
                stack.push(action);
                sessionMap.remove(key);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("flash action with key ["+key+"] with actual type ["+action+"] removed from session after being pushed into the stack ");
                }
            }
            else {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("flash action key ["+key+"] not found in session, no action is pushed into stack");
                }
            }
        }

        return invocation.invoke();
    }
}
