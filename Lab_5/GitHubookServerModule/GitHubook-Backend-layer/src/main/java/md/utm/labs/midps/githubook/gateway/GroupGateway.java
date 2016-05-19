package md.utm.labs.midps.githubook.gateway;

import java.util.List;

import md.utm.labs.midps.githubook.Comment;
import md.utm.labs.midps.githubook.Group;
import md.utm.labs.midps.githubook.Post;

public interface GroupGateway {
	List<Group> getAllGroups();
	Group getGroup(Long id);
	Group create(Group group);
	Group update(Group group);
	void delete(Group group);
	List<Post> getAllPosts(Long id);
	Post getPost(Group group, Long postId);
	void deletePost(Group group, Long postId);
	void updatePost(Post post);
	List<Comment> getPostComments(Post post);
}
