package md.utm.labs.midps.githubook.rest;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import md.utm.labs.midps.githubook.hibernate.HibernateUtility;

@Provider
public class SessionStartingRequestFilter implements ContainerRequestFilter {

	private HibernateUtility hibernateUtility = new HibernateUtility();
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		hibernateUtility.openSession();
	}
}
