package md.utm.labs.midps.githubook.gateway;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import md.utm.labs.midps.githubook.Friend;
import md.utm.labs.midps.githubook.Message;
import md.utm.labs.midps.githubook.User;

public class InMemoryUserGateway implements UserGateway {

	private static Map<Long, User> users = new ConcurrentHashMap<Long, User>();
	private static AtomicLong counter = new AtomicLong(3);
	
	static {
		User first = new User("Mike Smith", "mike", "Chisinau", "Endava");
		User second = new User("John Doe", "john", "Moskow", "Microsoft");
		first.setId(1L);
		second.setId(2L);
		first.makeFriend(second);
		users.put(1L, first);
		users.put(2L, second);	
		first.sendMessage(new Message(first, second, "How you doin'?"));
	}
	
	@Override
	public List<User> getAllUsers() {
		ArrayList<User> userList = new ArrayList<User>();
		for (Map.Entry<Long, User> entry : users.entrySet())
			userList.add(entry.getValue());
		return userList;
	}

	@Override
	public User getUser(Long id) {
		return users.get(id);
	}

	@Override
	public User create(User user) {
		user.setId(counter.incrementAndGet());
		users.put(user.getId(), user);
		return user;
	}

	@Override
	public User update(User user) {
		users.put(user.getId(), user);
		return user;
	}

	@Override
	public void delete(User user) {
		users.remove(user.getId());
	}

	@Override
	public Set<Friend> getAllFriends(Long id) {
		return users.get(id).getFriends();
	}
}
