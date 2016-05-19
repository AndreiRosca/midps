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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class Post {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Group owningGroup;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate = new Date();
	private String content;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User author;

	@OneToMany(mappedBy = "owningPost", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Comment> comments = new ArrayList<Comment>();

	public Post() {
	}

	public Post(Group owningGroup, String content, User author, List<Comment> comments) {
		this.owningGroup = owningGroup;
		this.content = content;
		this.author = author;
		this.comments = comments;
	}

	public Post(Group owningGroup, String content, User author) {
		this(owningGroup, content, author, new ArrayList<Comment>());
	}

	public Post(String content, User author) {
		this(null, content, author);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	@XmlTransient
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Group getOwningGroup() {
		return owningGroup;
	}

	public void setOwningGroup(Group owningGroup) {
		this.owningGroup = owningGroup;
	}

	public void addComment(Comment comment) {
		comments.add(comment);
		comment.setOwningPost(this);
	}

	public void removeComment(Comment comment) {
		comments.remove(comment);
		comment.setOwningPost(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((owningGroup == null) ? 0 : owningGroup.hashCode());
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
		Post other = (Post) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (owningGroup == null) {
			if (other.owningGroup != null)
				return false;
		} else if (!owningGroup.equals(other.owningGroup))
			return false;
		return true;
	}

}
