package md.utm.labs.midps.githubook.rest;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import md.utm.labs.midps.githubook.Friend;
import md.utm.labs.midps.githubook.Message;
import md.utm.labs.midps.githubook.User;
import md.utm.labs.midps.githubook.gateway.JpaUserGateway;
import md.utm.labs.midps.githubook.gateway.UserGateway;
import md.utm.labs.midps.githubook.hibernate.HibernateUtility;

public abstract class UserResource {
	
	protected UserGateway userGateway = new JpaUserGateway(new HibernateUtility());

	public UserResource() {
		super();
	}

	public UserResource(UserGateway userGateway) {
		this.userGateway = userGateway;
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract List<User> getAllUsers();

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract User getUser(@PathParam("id") Long id);

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract User createUser(User user);

	@PUT
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract User updateUser(User user);

	@DELETE
	@Path("{id}")
	public abstract void delete(@PathParam("id") Long id);

	@GET
	@Path("{id}/friends")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract Set<Friend> getAllFriends(@PathParam("id") Long id);

	@PUT
	@Path("{id}/friends")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract void createNewFriend(@PathParam("id") Long id, User newFriend);

	@DELETE
	@Path("{id}/friends")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract void destroyFriendship(@PathParam("id") Long id, User friend);

	@GET
	@Path("{userId}/friends/{friendId}/messages")
	public abstract List<Message> getFriendMessages(@PathParam("userId") Long userId, @PathParam("friendId") Long friendId);

	@POST
	@Path("{userId}/friends/{friendId}/messages")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract void sendMessage(@PathParam("userId") Long userId, @PathParam("friendId") Long friendId,
			Message message);
}
