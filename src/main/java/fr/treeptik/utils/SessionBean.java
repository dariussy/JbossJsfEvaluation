package fr.treeptik.utils;

/*
 *  Copyright 2011 Blue Lotus Software, LLC.
 *  Copyright 2011 John Yeary <jyeary@bluelotussoftware.com>.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
/*
 *  $Id:$
 */

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author John Yeary <jyeary@bluelotussoftware.com>
 * @version 1.0
 */
@Stateless
@SessionScoped
public class SessionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public SessionBean() {
	}

	/**
	 * Logs the current user out by invalidating the session.
	 * 
	 * @return &quot;logout&quot; which is used by the
	 *         {@literal faces-config.xml} to redirect back to the
	 *         {@literal index.xhtml} page.
	 */
	public String logout() {
		// FacesContext facesContext = FacesContext.getCurrentInstance();
		// ExternalContext externalContext = facesContext.getExternalContext();
		// externalContext.invalidateSession();
		try {
			getRequest().logout();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "logout";
	}

	public HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public boolean isOnRoleAdmin() {
		return getRequest().isUserInRole("ADMIN");
	}

	public boolean isOnRoleUser() {
		return getRequest().isUserInRole("USER");
	}
}