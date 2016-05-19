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

import md.utm.labs.midps.githubook.Comment;
import md.utm.labs.midps.githubook.Group;
import md.utm.labs.midps.githubook.Post;
import md.utm.labs.midps.githubook.User;
import md.utm.labs.midps.githubook.gateway.GroupGateway;
import md.utm.labs.midps.githubook.gateway.JpaGroupGateway;
import md.utm.labs.midps.githubook.gateway.JpaUserGateway;
import md.utm.labs.midps.githubook.gateway.UserGateway;
import md.utm.labs.midps.githubook.hibernate.HibernateUtility;

public abstract class GroupResource {

	protected GroupGateway groupGateway = new JpaGroupGateway(new HibernateUtility());
	protected UserGateway userGateway = new JpaUserGateway(new HibernateUtility());

	public GroupResource() {
		super();
	}

	public GroupResource(GroupGateway groupGateway) {
		this.groupGateway = groupGateway;
	}

	public GroupResource(GroupGateway groupGateway, UserGateway userGateway) {
		this.groupGateway = groupGateway;
		this.userGateway = userGateway;
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract List<Group> getAllGroups();

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract Group getGroup(@PathParam("id") Long id);

	@PUT
	@Path("{id}/subscribers")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract void subscribeUser(@PathParam("id") Long id, User user);

	@DELETE
	@Path("{id}/subscribers")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract void unsubscribeUser(@PathParam("id") Long id, User user);

	@GET
	@Path("{id}/subscribers")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract Set<User> getGroupSubscribers(@PathParam("id") Long id);

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract Group createNewGroup(Group group);

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract Group updateGroup(Group group);

	@DELETE
	@Path("{id}")
	public abstract void deleteGroup(@PathParam("id") Long id);

	@GET
	@Path("{id}/posts")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract List<Post> getGroupPosts(@PathParam("id") Long id);

	@POST
	@Path("{groupId}/posts")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract Post createPost(@PathParam("groupId") Long groupId, Post post);

	@PUT
	@Path("{groupId}/posts")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract Post updatePost(@PathParam("groupId") Long groupId, Post post);

	@DELETE
	@Path("{groupId}/posts/{postId}")
	public abstract void deletePost(@PathParam("groupId") Long groupId, @PathParam("postId") Long postId);

	@GET
	@Path("{groupId}/posts/{postId}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract Post getPost(@PathParam("groupId") Long groupId, @PathParam("postId") Long postId);

	@GET
	@Path("{groupId}/posts/{postId}/comments")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract List<Comment> getPostComments(@PathParam("groupId") Long groupId, @PathParam("postId") Long postId);

	@POST
	@Path("{groupId}/posts/{postId}/comments")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public abstract Comment addComment(@PathParam("groupId") Long groupId, @PathParam("postId") Long postId,
			Comment comment);

}
