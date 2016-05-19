package md.utm.labs.midps.githubook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class UserTest {

	User user = new User("Mike Smith", "mike", "Chisinau", "Endava");
	User friend = new User("Denis Kenny", "denis", "England", "Endava");

	@Test
	public void whenUserHasNoFriends_AfterAddingAFriendTheFriendListHasOneItem() {
		user.makeFriend(friend);
		assertTrue(user.isFriendWith(friend));
		assertEquals(1, user.getFriends().size());
	}

	@Test
	public void givenAUser_ItsNotPossibleToMakeHimFriendsWithHimself() {
		User friend = new User("Mike Smith", "mike", "Chisinau", "Endava");
		user.makeFriend(friend);
		assertFalse(user.isFriendWith(friend));
	}

	@Test
	public void givenAUserAndAStranger_thereIsNoFriendRelationshipBetweenThem() {
		assertFalse(user.isFriendWith(friend));
	}

	@Test
	public void givenTwoFriends_FriendshipMustBeCommutative() {
		user.makeFriend(friend);
		assertTrue(user.isFriendWith(friend));
		assertTrue(friend.isFriendWith(user));
	}

	@Test
	public void ifTheUserHasOneFriend_AfterRemovingTheFriendshipTheFriendListIsEmpty() {
		user.makeFriend(friend);
		user.destroyFriendshipWith(friend);
		assertFalse(user.isFriendWith(friend));
		assertFalse(friend.isFriendWith(user));
	}

	@Test
	public void whenTheUserHasFriends_CanSendMessagesToThem() {
		user.makeFriend(friend);
		Message message = new Message(friend, user, "Hello, Mike!");
		user.sendMessage(message);
		assertTrue(user.haveNewMessages());
	}

	@Test
	public void whenUserHasNoFriends_CantReceiveMessagesFromStrangers() {
		Message message = new Message(friend, user, "Hello, Mike!");
		user.sendMessage(message);
		assertFalse(user.haveNewMessages());
	}

	@Test
	public void whenThereAreNoNewMessages_TheListOfTheUnseenMessagesIsEmpty() {
		List<Message> unseenMessages = user.getUnseenMessages();
		assertTrue(unseenMessages.isEmpty());
		assertFalse(user.haveNewMessages());
	}

	@Test
	public void whenThereIsOneNewMessage_TheListOfTheUnseenMessagesHasOneMessage() {
		user.makeFriend(friend);
		Message message = new Message(friend, user, "Hello, Mike!");
		user.sendMessage(message);
		List<Message> unseenMessages = user.getUnseenMessages();
		assertFalse(unseenMessages.isEmpty());
		assertTrue(user.haveNewMessages());
		assertEquals(Arrays.asList(message), unseenMessages);
	}
	
	@Test
	public void whenThereAreNoMessages_AfterSendingAMessageTheUsersCanSeeOnlyOneMessage() {
		user.makeFriend(friend);
		Message message = new Message(friend, user, "Hello, Mike!");
		user.sendMessage(message);
		List<Message> messages = user.getFriendMessages(friend);
		assertEquals(1, messages.size());
		assertEquals(Arrays.asList(message), messages);
	}
	
	@Test
	public void whenThereAreTwoMessages_TheyAreReceivedSortedByDateInDescendingOrder() throws InterruptedException {
		user.makeFriend(friend);
		Message message = new Message(friend, user, "Hello, Mike!");
		user.sendMessage(message);
		TimeUnit.MILLISECONDS.sleep(1);
		Message second = new Message(friend, user, "The second one");
		user.sendMessage(second);
		List<Message> messages = user.getFriendMessages(friend);
		assertEquals(Arrays.asList(second, message), messages);
	}
}
