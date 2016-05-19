package md.utm.labs.midps.githubook.gateway;

import java.util.List;

import org.hibernate.Session;

import md.utm.labs.midps.githubook.Comment;
import md.utm.labs.midps.githubook.Group;
import md.utm.labs.midps.githubook.Post;
import md.utm.labs.midps.githubook.hibernate.HibernateUtility;

public class JpaGroupGateway implements GroupGateway {

	private HibernateUtility hibernateUtility;
	
	public JpaGroupGateway(HibernateUtility hibernateUtility) {
		this.hibernateUtility = hibernateUtility;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Group> getAllGroups() {
		Session session = hibernateUtility.getSessionFactory().getCurrentSession();
		List<Group> groups = session.createQuery("from githubook_group").list();
		return groups;
	}

	@Override
	public Group getGroup(Long id) {
		Session session = hibernateUtility.getSessionFactory().getCurrentSession();
		Group group = session.byId(Group.class).load(id);
		return group;
	}

	@Override
	public Group create(Group group) {
		Session session = hibernateUtility.getSessionFactory().getCurrentSession();
		session.save(group);
		return group;
	}

	@Override
	public Group update(Group group) {
		Session session = hibernateUtility.getSessionFactory().getCurrentSession();
		session.update(group);
		return group;
	}

	@Override
	public void delete(Group group) {
		Session session = hibernateUtility.getSessionFactory().getCurrentSession();
		session.delete(group);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Post> getAllPosts(Long id) {
		Session session = hibernateUtility.getSessionFactory().getCurrentSession();
		//Group group = session.byId(Group.class).load(id);
		List<Post> posts = session.createQuery("select g.posts from githubook_group g where g.id = :id")
				.setLong("id", id).list();
		return posts;
	}

	@Override
	public Post getPost(Group group, Long postId) {
		Session session = hibernateUtility.getSessionFactory().getCurrentSession();
		group = session.byId(Group.class).load(group.getId());
		for (Post p : group.getPosts()) {
			if (p.getId().equals(postId))
				return p;
		}
		return null;
	}

	@Override
	public void deletePost(Group group, Long postId) {
		Session session = hibernateUtility.getSessionFactory().getCurrentSession();
		group = session.byId(Group.class).load(group.getId());
		for (Post p : group.getPosts()) {
			if (p.getId().equals(postId)) {
				group.removePost(p);
				break;
			}
		}
	}
	
	public void updatePost(Post post) {
		Session session = hibernateUtility.getSessionFactory().getCurrentSession();
		session.update(post);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Comment> getPostComments(Post post) {
		Session session = hibernateUtility.getSessionFactory().getCurrentSession();
		return session.createQuery("select comments from Post p where p.id = :id").setLong("id", post.getId()).list();
	}
}
