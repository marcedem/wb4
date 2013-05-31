package tuwien.big.formel0.picasa;

import java.util.ArrayList;
import java.util.List;


public class AvatarControl {
	
	private static List<Avatar> avatarlist = new ArrayList<Avatar>();

	public static void addAvatar(Avatar avatar) {
		avatarlist.add(avatar);
	}

	public static Avatar getAvatar(String description) {
		for (Avatar avatar : avatarlist) {
			if (avatar.getDescription().equals(description)) {
				return avatar;
			}
		}
		return null;
	}

	public static List<Avatar> getAvatarList() {
		return avatarlist;
	}

	public static void updateAvatarList() {
		RaceDriver loader = new RaceDriver();

		try {
			avatarlist = loader.getPhotoURLs();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static boolean isEmpty() {
		AvatarControl.updateAvatarList();
		if (getAvatarList().size() > 0)
			return false;
		else
			return true;
	}
}
