package tuwien.big.formel0.picasa;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.media.mediarss.MediaKeywords;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex
 */
public class RaceDriverLoader implements IRaceDriverService {

    @Override
    public List<RaceDriver> getRaceDrivers() throws IOException, ServiceException {
        URL url;
        url = new URL(
                "https://picasaweb.google.com/data/feed/api/user/107302466601293793664/albumid/5868849825181458161");

        PicasawebService myservice = new PicasawebService("mine");

        List<RaceDriver> list = new ArrayList<RaceDriver>();

        AlbumFeed feed = myservice.getFeed(url, AlbumFeed.class);

        for (PhotoEntry photo : feed.getPhotoEntries()) {
            MediaKeywords tags = photo.getMediaKeywords();
            String wikiUrl = "";
            if (tags.getKeywords().contains("Driver")) {
                List<String> tagList = tags.getKeywords();
                for (int i = 0; i < tagList.size(); i++) {
                    if (tagList.get(i).contains("wiki:")) {
                        wikiUrl = tagList.get(i).substring(5);
                        break;
                    }
                }
                
                RaceDriver driver = new RaceDriver();
                driver.setName(photo.getDescription().getPlainText());
                driver.setWikiUrl(wikiUrl);
                driver.setUrl(photo.getMediaContents().get(0).getUrl());
                list.add(driver);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        RaceDriverLoader loader = new RaceDriverLoader();
        System.out.println("TEST RACE DRIVER LOADER");
        try {
            List<RaceDriver> drivers = loader.getRaceDrivers();
            //Zum Testen
            System.out.println(drivers.size());
            for (int i = 0; i < drivers.size(); i++) {
                RaceDriver raceDriver = drivers.get(i);
                System.out.println(raceDriver.getName());
                System.out.println(raceDriver.getWikiUrl());
                System.out.println(raceDriver.getUrl());
                System.out.println("");
            }
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        } catch (ServiceException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
