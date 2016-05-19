package md.utm.labs.midps.githubook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class User {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String login;
	private String location;
	private String company;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Friend> friends = new HashSet<Friend>();

	@ManyToMany(mappedBy = "subscribers", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Group> groups = new HashSet<Group>();

	public User() {
	}

	public User(String name, String login, String location, String company, Set<Friend> friends) {
		this.name = name;
		this.login = login;
		this.location = location;
		this.company = company;
		this.friends = friends;
	}

	public User(String name, String login, String location, String company) {
		this.name = name;
		this.login = login;
		this.location = location;
		this.company = company;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@XmlTransient
	public Set<Friend> getFriends() {
		return friends;
	}

	public void setFriends(Set<Friend> friends) {
		this.friends = friends;
	}

	@XmlTransient
	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public void makeFriend(User user) {
		if (!this.equals(user)) {
			friends.add(new Friend(this, user));
			user.friends.add(new Friend(user, this));
		}
	}

	public boolean isFriendWith(User friend) {
		return friends.contains(new Friend(this, friend)) || friend.friends.contains(new Friend(friend, this));
	}

	public void destroyFriendshipWith(User friend) {
		friends.remove(new Friend(this, friend));
		friend.friends.remove(new Friend(friend, this));
	}

	public void sendMessage(Message message) {
		sendMessage(findFriend(message.getFrom(), message.getTo()), message);
		sendMessage(findFriend(message.getTo(), message.getFrom()), new Message(message));
	}

	private void sendMessage(Friend f, Message m) {
		if (f != null)
			f.addMessage(m);
	}

	private Friend findFriend(User first, User second) {
		Friend friend = null;
		for (Friend f : first.friends) {
			if (f.getFirstUser().equals(second) || f.getSecondUser().equals(second)) {
				friend = f;
				break;
			}
		}
		return friend;
	}

	public boolean haveNewMessages() {
		for (Friend f : friends) {
			for (Message m : f.getMessages()) {
				if (!m.isSeen())
					return true;
			}
		}
		return false;
	}

	public List<Message> getUnseenMessages() {
		List<Message> unseenMessages = new ArrayList<Message>();
		for (Friend f : friends) {
			for (Message m : f.getMessages()) {
				if (!m.isSeen())
					unseenMessages.add(m);
			}
		}
		return unseenMessages;
	}

	public List<Message> getFriendMessages(User friend) {
		for (Friend f : friends) {
			if (f.getFirstUser().equals(friend) || f.getSecondUser().equals(friend)) {
				List<Message> messages = f.getMessages();
				Collections.sort(f.getMessages(), new DescendingDateComparator());
				return messages;
			}
		}
		return Collections.emptyList();
	}

	private static class DescendingDateComparator implements Comparator<Message> {
		public int compare(Message first, Message second) {
			return -(first.getCreationDate().after(second.getCreationDate()) ? 1
					: first.getCreationDate().before(second.getCreationDate()) ? -1 : 0);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", login=" + login + ", location=" + location + ", company="
				+ company + "]";
	}
}
