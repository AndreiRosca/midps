package md.utm.labs.midps.githubook.gateway;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import md.utm.labs.midps.githubook.Friend;
import md.utm.labs.midps.githubook.User;
import md.utm.labs.midps.githubook.hibernate.HibernateUtility;

public class JpaUserGateway implements UserGateway {

	private HibernateUtility hibernateUtility;

	public JpaUserGateway(HibernateUtility hibernateUtility) {
		this.hibernateUtility = hibernateUtility;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		Session session = hibernateUtility.getSessionFactory().getCurrentSession();
		List<User> userList = session.createQuery("from User").list();
		return userList;
	}

	@Override
	public User getUser(Long id) {
		Session session = hibernateUtility.getSessionFactory().getCurrentSession();
		User user = session.byId(User.class).load(id);
		return user;
	}

	@Override
	public User create(User user) {
		Session session = hibernateUtility.getSessionFactory().getCurrentSession();
		session.save(user);
		return user;
	}

	@Override
	public User update(User user) {
		Session session = hibernateUtility.getSessionFactory().getCurrentSession();
		session.merge(user);
		return user;
	}

	@Override
	public void delete(User user) {
		Session session = hibernateUtility.getSessionFactory().getCurrentSession();
		Set<Friend> friends = new HashSet<Friend>(user.getFriends());
		for (Friend f : friends) {
			user.getFriendMessages(f.getSecondUser()).clear();
			f.getMessages().clear();
			user.destroyFriendshipWith(f.getSecondUser());
		}
		session.delete(user);
	}

	@Override
	public Set<Friend> getAllFriends(Long id) {
		Session session = hibernateUtility.getSessionFactory().getCurrentSession();
		Set<Friend> friendSet = session.byId(User.class).load(id).getFriends();
		return friendSet;
	}
}
