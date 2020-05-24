package se.mau.ai0405.p3;

import android.util.JsonReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 *
 *
 * @author Antoine Rebelo
 * @author Sara Dalvig
 * @author Anton Tiwe
 */

public class TestAPI extends Thread {
    private GameController controller;
    private boolean quoteSet = false;
    private Quote quote;

    /**
     *Constructor
     *
     * @param controller = GameController
     */
    public TestAPI(GameController controller) {
        quote = new Quote();
        this.controller = controller;
    }

    /**
     *Makes connection to the API, using JsonReader to get the object from the API and sets the quote and author for the quote object.
     */
    private void fetchQuote() {
        try {
            URL quoteAPI = new URL("https://talaikis.com/api/quotes/random/");
            HttpsURLConnection myConnection = (HttpsURLConnection) quoteAPI.openConnection();
            int responseCode = myConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream isConnection = myConnection.getInputStream();
                InputStreamReader isConnectionReader =
                        new InputStreamReader(isConnection, "UTF-8");
                JsonReader jsonReader = new JsonReader(isConnectionReader);
                jsonReader.beginObject();
                String key = jsonReader.nextName();
                String authorString;
                if (!quoteSet) {
                    if (key.equals("quote")) {
                        String quoteString = jsonReader.nextString();
                        quote.setQuote1(quoteString);
                        key = jsonReader.nextName();
                    }

                    if (key.equals("author")) {
                        authorString = jsonReader.nextString();
                        quote.setCorrectAuthor(authorString);
                    }
                } else {
                    jsonReader.nextString();
                    key = jsonReader.nextName();
                    if (key.equals("author")) {
                        authorString = jsonReader.nextString();
                        setFakeAuthor(authorString);
                    }
                }
                quoteSet = true;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *Sets fakeAuthor1, 2 or 3 to the incomming string.
     *
     * @param author = author to set in the fakeAuthor variable
     */
    private void setFakeAuthor(String author) {
        if (quote.getFakeAuthor1().equals("")) {
            quote.setFakeAuthor1(author);
        } else if (quote.getFakeAuthor2().equals("")) {
            quote.setFakeAuthor2(author);
        } else if (quote.getFakeAuthor3().equals("")) {
            quote.setFakeAuthor3(author);
        }
    }

    @Override
    public void run() {
        quote = new Quote();
        for (int i = 0; i < 4; i++) {
            fetchQuote();
        }
        controller.newQuote(quote);
    }
}


