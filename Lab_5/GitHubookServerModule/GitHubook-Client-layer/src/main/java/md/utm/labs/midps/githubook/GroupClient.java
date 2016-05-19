package md.utm.labs.midps.githubook;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class GroupClient {
	
	private URI baseUri;
	private Client client = ClientBuilder.newClient();
	
	public GroupClient(URI baseUri) {
		this.baseUri = baseUri;
	}
	
	public List<Group> getAllGroups() {
		return client.target(baseUri.toString()).path("groups").request().get(new GenericType<List<Group>>(){});
	}
	
	public Group getGroup(Long id) {
		return client.target(baseUri.toString()).path("groups/" + id)
				.request().accept(MediaType.APPLICATION_JSON).get(Group.class);
	}
	
	public Set<User> getSubscribers(Group group) {
		return client.target(baseUri.toString()).path("/groups/" + group.getId() + "/subscribers")
				.request().get(new GenericType<Set<User>>(){});
	}
	
	public void subscribe(Group group, User user) {
		client.target(baseUri.toString()).path("/groups/" + group.getId() + "/subscribers")
			.request().put(Entity.json(user));
	}
	
	public void unsubscribe(Group group, User user) {
		client.target(baseUri.toString()).path("/groups/" + group.getId() + "/subscribers")
			.request().method("DELETE", Entity.json(user));
	}
	
	public Group createGroup(Group group) {
		return client.target(baseUri.toString()).path("groups").request().post(Entity.json(group), Group.class);
	}
	
	public Group updateGroup(Group group) {
		return client.target(baseUri.toString()).path("groups").request().put(Entity.json(group), Group.class);
	}
	
	public void deleteGroup(Group group) {
		client.target(baseUri.toString()).path("groups/" + group.getId()).request().delete();
	}
	
	public List<Post> getPosts(Group group) { 
		return client.target(baseUri.toString()).path("groups/" + group.getId() + "/posts")
				.request().get(new GenericType<List<Post>>(){});
	}
	
	public Post createPost(Group group, Post post) {
		return client.target(baseUri.toString()).path("groups/" + group.getId() + "/posts")
				.request().post(Entity.json(post), Post.class);
	}
	
	public Post updatePost(Group group, Post post) {
		return client.target(baseUri.toString()).path("groups/" + group.getId() + "/posts")
				.request().put(Entity.json(post), Post.class);
	}
	
	public Post getPost(Group group, Long postId) {
		return client.target(baseUri.toString()).path("groups/" + group.getId() + "/posts/" + postId)
				.request().get(Post.class);
	}
	
	public void deletePost(Group group, Long postId) {
		client.target(baseUri.toString()).path("groups/" + group.getId() + "/posts/" + postId)
				.request().delete();
	}
	
	public List<Comment> getPostComments(Group group, Long postId) {
		return client.target(baseUri.toString()).path("groups/" + group.getId() + "/posts/" + postId + "/comments")
				.request().get(new GenericType<List<Comment>>(){});
	}
	
	public Comment createPostComment(Group group, Long postId, Comment comment) {
		return client.target(baseUri.toString()).path("groups/" + group.getId() + "/posts/" + postId + "/comments")
				.request().post(Entity.json(comment), Comment.class);
	}
}
