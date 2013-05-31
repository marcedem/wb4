package picasa;

import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import com.google.gdata.client.photos.*;
import com.google.gdata.data.photos.*;
import com.google.gdata.util.ServiceException;
import java.io.IOException;

public class RaceDriver implements IRaceDriverService {

	@Override
	public List<Avatar> getPhotoURLs() throws Exception {
		URL url = new URL(
				"https://picasaweb.google.com/data/feed/api/user/107302466601293793664");
		PicasawebService myservice = new PicasawebService("mine");

		List<Avatar> list = new ArrayList<Avatar>();

		AlbumFeed feed = myservice.getFeed(url, AlbumFeed.class);

		for (PhotoEntry photo : feed.getPhotoEntries()) {
			Avatar avatar = new Avatar();
			avatar.setDescription(photo.getDescription().getPlainText());
			avatar.setUrl(photo.getMediaContents().get(0).getUrl());
                      
                        //TODO donno if work
                       // avatar.setWikiUrl(photo.getFeed("wiki"));
     
			list.add(avatar);
		}
		return list;
	}

	public static void main(String[] args) {
		RaceDriver loader = new RaceDriver();
		try {
			loader.getPhotoURLs();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
