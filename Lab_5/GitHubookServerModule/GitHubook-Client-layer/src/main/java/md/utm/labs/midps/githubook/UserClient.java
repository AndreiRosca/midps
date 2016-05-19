package md.utm.labs.midps.githubook;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class UserClient {

	private URI baseUri;
	private Client client = ClientBuilder.newClient();
	
	public UserClient(URI webServiceURI) {
		this.baseUri = webServiceURI;
	}
	
	public List<User> getAllUsers() {
		return client.target(baseUri.toString()).path("users").request()
				.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<User>>(){});
	}
	
	public User getUser(Long id) {
		return client.target(baseUri.toString()).path("users/" + id).request()
				.accept(MediaType.APPLICATION_JSON).get(User.class);
	}
	
	public void createUser(User user) {
		client.target(baseUri.toString()).path("users").request().post(Entity.json(user));
	}
	
	public void updateUser(User user) {
		client.target(baseUri.toString()).path("users").request().put(Entity.json(user));
	}
	
	public void deleteUser(User user) {
		throw new UnsupportedOperationException();
	}
	
	public List<Friend> getFriends(User user) {
		return client.target(baseUri.toString()).path("users/" + user.getId() + "/friends")
				.request().accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Friend>>(){});
	}
	
	public void createFriend(User first, User second) {
		client.target(baseUri.toString()).path("users/" + first.getId() + "/friends")
		.request().put(Entity.json(second));
	}
	
	public void destroyFriendship(User first, User second) {
		throw new UnsupportedOperationException();
	}
	
	public List<Message> getFriendMessages(Friend frienship) {
		return client.target(baseUri.toString())
				.path("users/" + frienship.getFirstUser().getId() + "/friends/" + 
						frienship.getSecondUser().getId() + "/messages")
				.request().accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Message>>(){});
	}
	
	public void sendMessageToFriend(Friend frienship, Message message) {
		client.target(baseUri.toString())
				.path("users/" + frienship.getFirstUser().getId() + "/friends/" + 
						frienship.getSecondUser().getId() + "/messages")
				.request().post(Entity.json(message));
	}
	
	public void dispose() {
		client.close();
	}
	
	public static void main(String[] args) {
		UserClient client = new UserClient(URI.create("http://localhost:8080/GitHubook-WebService-layer/rest"));
		for (User u : client.getAllUsers()) {
			System.out.println(client.getFriends(u));
		}
		client.dispose();
	}
}
