package md.utm.labs.midps.githubook;

import static org.junit.Assert.*;

import org.junit.Test;

public class PostTest {
	
	User user = new User("Mike Smith", "mike", "Chisinau", "Endava");
	Group group = new Group("Metallica fan club", "Metallica fans unite!");
	Post post = new Post("Hello, users!", user);
	Comment comment = new Comment(user, "Hello, world!");

	@Test
	public void whenThePostHasNoComments_AfterAddingACommentThePostHasOnlyOneComment() {
		post.addComment(comment);
		assertEquals(1, post.getComments().size());
		assertEquals(comment.getOwningPost(), post);
		assertTrue(post.getComments().contains(comment));
	}
	
	@Test
	public void whenThePostHasOneComment_AfterRemovingItThePostHasNoComments() {
		post.addComment(comment);
		post.removeComment(comment);
		assertTrue(post.getComments().isEmpty());
		assertNull(comment.getOwningPost());
	}
}
