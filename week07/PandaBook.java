package week07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Social network for pandas with some basic methods
 * 
 * @author mestanov
 *
 */
public class PandaBook {
	private Map<Panda, Set<Panda>> pandas;
	int size;

	public PandaBook() {
		pandas = new HashMap<>();
		size = 0;
	}

	/**
	 * Adds a panda to the network if it doesn't belong already
	 * 
	 * @param panda
	 * @return
	 */
	public boolean addPanda(Panda panda) {
		if (hasPanda(panda)) {
			return false;
		}

		pandas.put(panda, new HashSet<>());
		size++;
		return true;
	}

	public boolean hasPanda(Panda panda) {
		return pandas.containsKey(panda);
	}

	public void makeFriends(Panda p1, Panda p2) {
		if (p1 == null || p2 == null) {
			throw new NullPointerException("Panda doesn't exist.");
		}

		addPanda(p1);
		addPanda(p2);

		pandas.get(p1).add(p2);
		pandas.get(p2).add(p1);
	}

	public boolean areFriends(Panda p1, Panda p2) {
		if (p1 == null || p2 == null) {
			throw new NullPointerException("Panda doesn't exist.");
		}

		// No need to check to check the other way around - addPanda adds to
		// both
		return getFriends(p1).contains(p2);
	}

	/**
	 * Finds all friends of a given panda, throws exception if it's not a member
	 * 
	 * @param panda
	 * @return list of all its friends
	 */
	public List<Panda> friendsOf(Panda panda) {
		if (!hasPanda(panda)) {
			throw new IllegalArgumentException("The given panda is not a member.");
		}

		return new ArrayList<>(getFriends(panda));
	}

	/**
	 * Given two pandas returns the level of connection they are in. If they're
	 * friends, it's 1, if they're friends of friends it's 2 and so on.
	 * 
	 * @param panda
	 *            1
	 * @param panda
	 *            2
	 * @return level of connection if it exists, -1 otherwise
	 */
	public int connectionLevel(Panda p1, Panda p2) {
		if (p1 == null || p2 == null) {
			throw new NullPointerException("The given panda doesn't exist.");
		}

		// Sadly, we assume a panda is not a friend of itself
		if (p1 == p2) {
			return -1;
		}

		Set<Panda> thisLevel = new HashSet<>();

		Set<Panda> visited = new HashSet<>();
		visited.add(p1);
		thisLevel.add(p1);

		int level = 1;

		while (!thisLevel.isEmpty()) {
			Set<Panda> nextLevel = new HashSet<>();

			for (Panda panda : thisLevel) {
				Set<Panda> friends = getFriends(panda);

				if (friends.contains(p2)) {
					return level;
				}

				nextLevel.addAll(friends);
			}

			nextLevel.removeAll(visited);
			visited.addAll(nextLevel);
			thisLevel = nextLevel;
			level++;
		}

		return -1;
	}

	public boolean areConnected(Panda p1, Panda p2) {
		if (p1 == null || p2 == null) {
			throw new NullPointerException("The given panda doesn't exist.");
		}

		return -1 != connectionLevel(p1, p2);
	}

	/**
	 * Finds all pandas members of a given gender at a given level
	 * 
	 * @param level
	 * @param panda
	 * @param gender
	 * @return count of the pandas
	 */
	public int genderInLevel(int level, Panda panda, String gender) {
		int count = 0;
		Set<Panda> friends = new HashSet<>();
		friends.addAll(getFriends(panda));

		for (int i = 1; i < level; i++) {
			Set<Panda> theirFriends = new HashSet<>();

			theirFriends.add(panda);
			for (Panda friend : friends) {
				theirFriends.addAll(getFriends(friend));
			}

			theirFriends.remove(panda);
			friends = theirFriends;
		}

		for (Panda friend : friends) {
			if (friend.getGender() != null && friend.getGender().equals(gender)) {
				count++;
			}
		}

		return count;
	}

	private Set<Panda> getFriends(Panda panda) {
		if (!hasPanda(panda)) {
			return null;
		}

		return pandas.get(panda);
	}

}
