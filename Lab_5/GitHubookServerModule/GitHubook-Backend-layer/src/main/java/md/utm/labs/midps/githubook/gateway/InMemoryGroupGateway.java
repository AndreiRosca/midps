package md.utm.labs.midps.githubook.gateway;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import md.utm.labs.midps.githubook.Comment;
import md.utm.labs.midps.githubook.Group;
import md.utm.labs.midps.githubook.Post;
import md.utm.labs.midps.githubook.User;

public class InMemoryGroupGateway implements GroupGateway {

	private static Map<Long, Group> groups = new ConcurrentHashMap<Long, Group>();
	private static AtomicLong counter = new AtomicLong(3);

	static {
		User user = new User("Mike Smith", "mike", "Chisinau", "Endava");
		Group first = new Group("Metallica fans", "Metallica fan club.");
		first.setId(1L);
		Group second = new Group("C++", "C++ Q&A");
		second.setId(2L);
		groups.put(1L, first);
		groups.put(2L, second);
		first.addPost(new Post("This is my first post", user));
	}

	@Override
	public List<Group> getAllGroups() {
		return new ArrayList<Group>(groups.values());
	}

	@Override
	public Group getGroup(Long id) {
		return groups.get(id);
	}

	@Override
	public Group create(Group group) {
		group.setId(counter.incrementAndGet());
		groups.put(group.getId(), group);
		return group;
	}

	@Override
	public Group update(Group group) {
		groups.put(group.getId(), group);
		return group;
	}

	@Override
	public void delete(Group group) {
		groups.remove(group.getId());
	}

	@Override
	public List<Post> getAllPosts(Long id) {
		return groups.get(id).getPosts();
	}

	@Override
	public Post getPost(Group group, Long postId) {
		for (Post p : group.getPosts())
			if (p.getId().equals(postId))
				return p;
		return null;
	}

	@Override
	public void deletePost(Group group, Long postId) {
		for (Post p : group.getPosts()) {
			if (p.getId().equals(postId)) {
				group.removePost(p);
				break;
			}
		}
	}

	@Override
	public void updatePost(Post post) {
		
	}

	@Override
	public List<Comment> getPostComments(Post post) {
		return post.getComments();
	}
}
