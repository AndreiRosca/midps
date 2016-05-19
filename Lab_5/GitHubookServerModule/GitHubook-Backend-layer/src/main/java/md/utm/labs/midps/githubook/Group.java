package md.utm.labs.midps.githubook;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity(name = "githubook_group")
@XmlRootElement
public class Group {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<User> subscribers = new HashSet<User>();
	
	@OneToMany(mappedBy = "owningGroup", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Post> posts = new ArrayList<Post>();
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate = new Date();
	private String description;

	public Group() {
	}

	public Group(String name, String description, Set<User> subscribers, List<Post> posts) {
		this(name, description);
		this.subscribers = subscribers;
		this.posts = posts;
	}

	public Group(String name, String description) {
		this.name = name;
		this.description = description;
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

	@XmlTransient
	public Set<User> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(Set<User> subscribers) {
		this.subscribers = subscribers;
	}

	@XmlTransient
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void addSubscriber(User user) {
		subscribers.add(user);
		user.getGroups().add(this);
	}

	public void removeSubscriber(User user) {
		subscribers.remove(user);
		user.getGroups().remove(this);
	}

	public void addPost(Post post) {
		post.setOwningGroup(this);
		posts.add(post);
	}

	public void removePost(Post post) {
		posts.remove(post);
		post.setOwningGroup(null);
	}

	public void updatePost(Post post) {
		for (int i = 0; i < posts.size(); ++i) {
			Post p = posts.get(i);
			if (p.getId().equals(post.getId()))
				posts.set(i, post);
		}
	}

	public List<Comment> getComments() {
		List<Comment> groupComments = new ArrayList<Comment>();
		for (Post p : posts) {
			groupComments.addAll(p.getComments());
		}
		return groupComments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Group other = (Group) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
