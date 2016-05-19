package md.utm.labs.midps.githubook.rest;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Path;

import md.utm.labs.midps.githubook.Comment;
import md.utm.labs.midps.githubook.Group;
import md.utm.labs.midps.githubook.Post;
import md.utm.labs.midps.githubook.User;
import md.utm.labs.midps.githubook.gateway.GroupGateway;

@Path("/groups")
public class GroupResourceService extends GroupResource {

	public GroupResourceService() {
	}

	public GroupResourceService(GroupGateway groupGateway) {
		this.groupGateway = groupGateway;
	}

	public List<Group> getAllGroups() {
		return groupGateway.getAllGroups();
	}

	public Group getGroup(Long id) {
		return groupGateway.getGroup(id);
	}

	public void subscribeUser(Long id, User user) {
		Group group = groupGateway.getGroup(id);
		group.addSubscriber(userGateway.getUser(user.getId()));
	}

	public void unsubscribeUser(Long id, User user) {
		Group group = groupGateway.getGroup(id);
		group.removeSubscriber(userGateway.getUser(user.getId()));
	}

	public Set<User> getGroupSubscribers(Long id) {
		return groupGateway.getGroup(id).getSubscribers();
	}

	public Group createNewGroup(Group group) {
		groupGateway.create(group);
		return group;
	}

	public Group updateGroup(Group group) {
		return groupGateway.update(group);
	}

	public void deleteGroup(Long id) {
		Group group = groupGateway.getGroup(id);
		groupGateway.delete(group);
	}

	public List<Post> getGroupPosts(Long id) {
		return groupGateway.getAllPosts(id);
	}

	public Post createPost(Long groupId, Post post) {
		Group group = groupGateway.getGroup(groupId);
		post.setAuthor(userGateway.getUser(post.getAuthor().getId()));
		group.addPost(post);
		return post;
	}

	public Post updatePost(Long groupId, Post post) {
		Group group = groupGateway.getGroup(groupId);
		Post p = groupGateway.getPost(group, post.getId());
		p.setContent(post.getContent());
		post.setOwningGroup(group);
		return post;
	}

	public void deletePost(Long groupId, Long postId) {
		Group group = groupGateway.getGroup(groupId);
		groupGateway.deletePost(group, postId);
	}

	public Post getPost(Long groupId, Long postId) {
		Group group = groupGateway.getGroup(groupId);
		return groupGateway.getPost(group, postId);
	}

	public List<Comment> getPostComments(Long groupId, Long postId) {
		Group group = groupGateway.getGroup(groupId);
		return groupGateway.getPostComments(groupGateway.getPost(group, postId));
	}

	public Comment addComment(Long groupId, Long postId, Comment comment) {
		Group group = groupGateway.getGroup(groupId);
		Post post = groupGateway.getPost(group, postId);
		comment.setOwningPost(post);
		comment.setAuthor(userGateway.getUser(comment.getAuthor().getId()));
		post.addComment(comment);
		return comment;
	}
}
