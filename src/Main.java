import twitter4j.*;
import twitter4j.conf.*;
import java.util.List;
public class Main {

    public static void main(String[] args) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("kwDa9BQfNhXuEcpgTzN4yO4Dn")
                .setOAuthConsumerSecret("8JKYPxbwPeycHSEUrO7KRi28zPYqaulSNiMgC69no9zAOAQD3H")
                .setOAuthAccessToken("973828726621376512-BWYq8l9BIoUlCL6jTC4rU9DVMtvffaE")
                .setOAuthAccessTokenSecret("pCb1ojPwjpWbZvLEEokepMtSlSrENVxI9kYLn8mTKfvq5");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try {
            Query query = new Query("pokemon");
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    //System.out.println(tweet.getLang());
                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
    }
}
