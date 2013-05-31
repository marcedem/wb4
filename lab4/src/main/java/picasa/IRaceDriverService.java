package picasa;

import java.util.List;

public interface IRaceDriverService {

    /**
     * Used to retrieve a list of photo URLs form the Picasa album
     */
    public List<Avatar> getPhotoURLs() throws Exception;
}
