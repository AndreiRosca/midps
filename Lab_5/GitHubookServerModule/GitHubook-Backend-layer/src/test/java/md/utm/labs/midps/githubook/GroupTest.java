package md.utm.labs.midps.githubook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GroupTest {

	User user = new User("Mike Smith", "mike", "Chisinau", "Endava");
	Group group = new Group("Metallica fan club", "Metallica fans unite!");
	Post post = new Post("Hello, users!", user);
	
	@Before
	public void setUp() {
		group.addSubscriber(user);
	}

	@Test
	public void whenThereAreNoSubscribedGroups_AfterSubscribingToOneGroupTheUserIsInOneGroup() {
		assertEquals(1, group.getSubscribers().size());
		assertEquals(1, user.getGroups().size());
		assertTrue(group.getSubscribers().contains(user));
		assertTrue(user.getGroups().contains(group));
	}

	@Test
	public void whenTheUserIsInOneGroup_AfterUnsubscribingTheUserHasNoGroups() {
		group.removeSubscriber(user);
		assertTrue(group.getSubscribers().isEmpty());
		assertTrue(user.getGroups().isEmpty());
	}

	@Test
	public void whenTheGroupHasNoPosts_AfterAddindAPostTheGroupHasOnlyOnePost() {
		group.addPost(post);
		assertEquals(1, group.getPosts().size());
		assertTrue(group.getPosts().contains(post));
		assertEquals(post.getOwningGroup(), group);
	}

	@Test
	public void whenTheGroupHasOnePost_AfterRemovingItTheGroupHasNoPosts() {
		group.addPost(post);
		group.removePost(post);
		assertTrue(group.getPosts().isEmpty());
		assertNull(post.getOwningGroup());
	}

	@Test
	public void whenTwoPostsHaveComments_AfterRetrievingTheCommentListFromGroupItHasCommentsFromBothPosts() {
		group.addPost(post);
		Post secondPost = new Post("Make love now war!", user);
		group.addPost(secondPost);
		Comment first = new Comment(user, "Hello!");
		Comment second = new Comment(user, "Life is amazing!");
		post.addComment(first);
		secondPost.addComment(second);
		List<Comment> comments = group.getComments();
		assertEquals(2, comments.size());
		assertTrue(comments.contains(first));
		assertTrue(comments.contains(second));
	}
}
