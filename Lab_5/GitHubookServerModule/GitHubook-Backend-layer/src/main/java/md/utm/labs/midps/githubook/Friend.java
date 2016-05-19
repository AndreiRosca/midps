package md.utm.labs.midps.githubook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class Friend {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User firstUser;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User secondUser;

	private Date creationDate = new Date();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Message> messages = new ArrayList<Message>();

	public Friend() {
	}

	public Friend(User firstUser, User secondUser, List<Message> messages) {
		this.firstUser = firstUser;
		this.secondUser = secondUser;
		this.messages = messages;
	}

	public Friend(User firstUser, User secondUser) {
		this.firstUser = firstUser;
		this.secondUser = secondUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getFirstUser() {
		return firstUser;
	}

	public void setFirstUser(User firstUser) {
		this.firstUser = firstUser;
	}

	public User getSecondUser() {
		return secondUser;
	}

	public void setSecondUser(User secondUser) {
		this.secondUser = secondUser;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@XmlTransient
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void addMessage(Message message) {
		messages.add(message);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstUser == null) ? 0 : firstUser.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((secondUser == null) ? 0 : secondUser.hashCode());
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
		Friend other = (Friend) obj;
		if (firstUser == null) {
			if (other.firstUser != null)
				return false;
		} else if (!firstUser.equals(other.firstUser))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (secondUser == null) {
			if (other.secondUser != null)
				return false;
		} else if (!secondUser.equals(other.secondUser))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Friend [id=" + id + ", firstUser=" + firstUser + ", secondUser=" + secondUser + ", creationDate="
				+ creationDate + ", messages=" + messages + "]";
	}
	
	
}
