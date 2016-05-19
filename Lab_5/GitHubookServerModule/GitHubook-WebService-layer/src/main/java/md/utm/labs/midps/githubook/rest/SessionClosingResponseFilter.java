package md.utm.labs.midps.githubook.rest;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import md.utm.labs.midps.githubook.hibernate.HibernateUtility;

@Provider
public class SessionClosingResponseFilter implements ContainerResponseFilter {

	private HibernateUtility hibernateUtility = new HibernateUtility();
	
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		hibernateUtility.closeSession();
	}
}
