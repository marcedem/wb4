package tuwien.big.formel0.picasa;

import com.google.gdata.data.photos.PhotoFeed;
import javax.faces.bean.NoneScoped;

@NoneScoped
public class Avatar {

	private String url;
	private String description;
        private String wikiUrl;
        
	public Avatar() {

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
        public String getWikiUrl() {
               return wikiUrl;
           }

        public void setWikiUrl(String wikiUrl) {
            this.wikiUrl = wikiUrl;
        }
	@Override
	
        public String toString() {
		return "Avatar [url=" + url + ", description=" + description + "]";
	}

}
