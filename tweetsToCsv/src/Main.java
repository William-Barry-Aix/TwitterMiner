import org.apache.commons.csv.*;
import twitter4j.*;
import twitter4j.conf.*;

import java.io.*;
import java.util.*;

public class Main {
    private static Twitter twitter = initTwiter();

    public static void main(String[] args) {
        loadTweets();
    }

    private static void loadTweets(){
        List<Status> tweets = getTweets();
        if (tweets != null) {
            CSVwrite(tweets);
        }
    }

    private static List<Status> getTweets(){
        try {
            Query query = new Query("pokemon");
            query.setLang("fr");
            int count = 100;

            query.setCount(count);
            QueryResult result;

            System.out.println("Chargement des tweets...");
            result = twitter.search(query);

            List<Status> tweets = result.getTweets();
            System.out.println(count + " tweets " + query.getLang() + " ont été trouvé!");

            return tweets;

        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            return null;
        }
    }
    private static void CSVwrite(List<Status> tweets){
        String outputCSVFileName = "tweets.csv";
        String path = new File(outputCSVFileName).getAbsolutePath();
        CSVFormat csvFileFormat = CSVFormat.DEFAULT;
        try {
            FileWriter writer = new FileWriter(outputCSVFileName, false);
            CSVPrinter printer = new CSVPrinter(writer, csvFileFormat);
            for (Status tweet : tweets) {
                List<String> tweetData = new ArrayList<>();
                tweetData.add(tweet.getCreatedAt().toString());
                tweetData.add(tweet.getUser().getScreenName());
                for (String word : tweet.getText().split(" ")) {
                    word = word.replace(",","");
                    word = word.replace("(", "");
                    word = word.replace(")", "");
                    word = word.replace("\n", "");
                    word = word.toLowerCase();
                    tweetData.add(word);
                }
                printer.printRecord(tweetData);
                printer.flush();
            }
            System.out.println("Touts les tweets trouvés ont été stocké dans:\n" + path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Twitter initTwiter(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("kwDa9BQfNhXuEcpgTzN4yO4Dn")
                .setOAuthConsumerSecret("8JKYPxbwPeycHSEUrO7KRi28zPYqaulSNiMgC69no9zAOAQD3H")
                .setOAuthAccessToken("973828726621376512-BWYq8l9BIoUlCL6jTC4rU9DVMtvffaE")
                .setOAuthAccessTokenSecret("pCb1ojPwjpWbZvLEEokepMtSlSrENVxI9kYLn8mTKfvq5");
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }

}