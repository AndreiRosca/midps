package md.utm.labs.midps.githubook.gateway;

import java.util.List;
import java.util.Set;

import md.utm.labs.midps.githubook.Friend;
import md.utm.labs.midps.githubook.User;

public interface UserGateway {
	List<User> getAllUsers();
	User getUser(Long id);
	User create(User user);
	User update(User user);
	void delete(User user);
	Set<Friend> getAllFriends(Long id);
}
