/*
 * Copyright (c) 2002-2006 by OpenSymphony
 * All rights reserved.
 */
package com.opensymphony.webwork.dispatcher;

import java.util.Map;

import com.opensymphony.webwork.interceptor.FlashInterceptor;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionInvocation;
import org.apache.struts2.dispatcher.ServletRedirectResult;

/**
 * <!-- START SNIPPET: description -->
 *  A flash result, that save the current action into the http session before
 *  invoking <code>super.doExecute(...)</code>, which actually just do
 *  a redirect to a specific location just as a normal {@link ServletRedirectResult}
 *  would.
 *  <!-- END SNIPPET: description -->
 *
 *  <!-- START SNIPPET: params -->
 *  <ul>
 *  	key - The key under which current action is stored in Http Session. Default to
 *              {@link FlashInterceptor#DEFAULT_KEY} which is the string '__flashAction'
 *  </ul>
 *  <!-- END SNIPPET: params -->
 *
 *  <pre>
 *  <!-- START SNIPPET: example -->
 *
 *  &lt;action name="store"&gt;
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
 *  <!-- END SNIPPET: example -->
 *  </pre>
 *
 *
 * @author Patrick Lightbody
 * @version $Date: 2006/12/11 12:57:11 $ $Id: FlashResult.java,v 1.2 2006/12/11 12:57:11 tmjee Exp $
 */
public class FlashResult extends ServletRedirectResult {

	private static final long serialVersionUID = -8956841683709714038L;

	private String key = FlashInterceptor.DEFAULT_KEY;

	/**
	 * A flash result, that save the current action into the http session before
	 * invoking <code>super.doExecute(...)</code>.
	 *
	 * @see com.opensymphony.webwork.dispatcher.ServletRedirectResult#doExecute(java.lang.String, com.opensymphony.xwork.ActionInvocation)
	 */
	protected void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {

		// before we redirect, let's save the state in to the session
        Object action = invocation.getAction();
        Map session = invocation.getInvocationContext().getSession();
        session.put(FlashInterceptor.DEFAULT_KEY, action);

		super.doExecute(finalLocation, invocation);
	}

	/**
	 * Set the key used to store the current action in http session.
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Get the key used to store the current action in http session.
	 * @return String
	 */
	public String getKey() {
		return key;
	}

}
