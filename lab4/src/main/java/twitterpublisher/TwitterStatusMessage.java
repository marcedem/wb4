package twitterpublisher;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStatusMessage implements ITwitterClient {

	@Override
	public Status postMessage(Score score) throws TwitterException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
				.setOAuthConsumerKey("GZ6tiy1XyB9W0P4xEJudQ")
				.setOAuthConsumerSecret(
						"gaJDlW0vf7en46JwHAOkZsTHvtAiZ3QUd2mD1x26J9w")
				.setOAuthAccessToken(
						"1366513208-MutXEbBMAVOwrbFmZtj1r4Ih2vcoHGHE2207002")
				.setOAuthAccessTokenSecret(
						"RMPWOePlus3xtURWRVnv1TgrjTyK7Zk33evp4KKyA");
		Twitter twitter = new TwitterFactory(cb.build()).getInstance();

		try {
			return twitter.updateStatus(score.getTwitterPublicationString());
		} catch (TwitterException e) {
			System.out.println("twitterfehler");
			e.printStackTrace();
		}
		return null;
	}
}
