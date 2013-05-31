package twitterpublisher;

import twitter4j.Status;
import twitter4j.TwitterException;

public interface ITwitterClient {

	/** Used to post a message on twitter */
	public Status postMessage(Score score) throws TwitterException;

}
