package md.utm.labs.midps.githubook.rest;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Path;

import md.utm.labs.midps.githubook.Friend;
import md.utm.labs.midps.githubook.Message;
import md.utm.labs.midps.githubook.User;
import md.utm.labs.midps.githubook.gateway.UserGateway;

@Path("/users")
public class UserResourceService extends UserResource {

	public UserResourceService() {
		super();
	}

	public UserResourceService(UserGateway userGateway) {
		super(userGateway);
	}

	public List<User> getAllUsers() {
		return userGateway.getAllUsers();
	}

	public User getUser(Long id) {
		return userGateway.getUser(id);
	}

	public User createUser(User user) {
		return userGateway.create(user);
	}

	public User updateUser(User user) {
		return userGateway.update(user);
	}

	public void delete(Long id) {
		userGateway.delete(userGateway.getUser(id));
	}

	public Set<Friend> getAllFriends(Long id) {
		return userGateway.getAllFriends(id);
	}

	public void createNewFriend(Long id, User newFriend) {
		User user = userGateway.getUser(id);
		User friend = userGateway.getUser(newFriend.getId());
		user.makeFriend(friend);
	}

	public void destroyFriendship(Long id, User friend) {
		User user = userGateway.getUser(id);
		user.destroyFriendshipWith(userGateway.getUser(friend.getId()));
	}

	public List<Message> getFriendMessages(Long userId, Long friendId) {
		User user = userGateway.getUser(userId);
		User friend = userGateway.getUser(friendId);
		return user.getFriendMessages(friend);
	}

	public void sendMessage(Long userId, Long friendId, Message message) {
		User user = userGateway.getUser(userId);
		User friend = userGateway.getUser(friendId);
		message.setFrom(user);
		message.setTo(friend);
		user.sendMessage(message);
	}
}
