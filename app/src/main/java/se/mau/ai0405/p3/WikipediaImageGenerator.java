package se.mau.ai0405.p3;

import android.graphics.drawable.Drawable;
import android.util.JsonReader;
import android.util.JsonToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Class that creates a {@link Drawable} object of an image from Wikipedia.
 * It uses the Wikipedia Web API to get images.
 *
 * @author Hampus Hansson
 * @author Antoine Rebelo
 */
public class WikipediaImageGenerator {
    private final int IMAGE_HEIGHT = 200;
    private GameController controller;

    /**
     * Constructor. Sets the {@link GameController} of the object.
     *
     * @param controller The controller
     */
    public WikipediaImageGenerator(GameController controller) {
        this.controller = controller;
    }

    /**
     * Starts the thread which creates a {@link Drawable} object of a Wikipedia URL
     * through the Wikipedia API.
     *
     * @param title The title Wikipedia should search for
     */
    public void startThread(String title) {
        Thread thread = new Thread(new WikipediaRunnable(title));
        thread.start();
    }

    /**
     * Returns a {@link Drawable} object of an image from Wikipedia. Makes a HTTP
     * request and checks if the response is OK. If the request is OK, the method
     * reads from the URL page and parses the JSON object given by the API. From the
     * image source given in the JSON object, a {@link Drawable} object is created
     * through an input stream.
     *
     * @param title The title Wikipedia should search for
     * @return The Drawable object created from the Wikipedia URL
     */
    private Drawable getImage(String title) {
        Drawable drawable = null;

        try {
            URL url = new URL(generateURLString(title));
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                        inputStream, "UTF-8"));
                JsonReader reader = new JsonReader(bufferedReader);
                reader.beginObject();

                String value = read(reader, "source");

                InputStream inputStreamImage = (InputStream) new URL(value).getContent();
                drawable = Drawable.createFromStream(inputStreamImage, title);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drawable;
    }

    /**
     * Reads a JSON object and searches for the key given in the method.
     *
     * @param reader The reader of the JSON object
     * @param key The key which should be searched for
     * @return The value of the key given
     */
    private static String read(JsonReader reader, String key) {
        String result = "";
        String check;
        try {
            while (reader.hasNext()) {
                JsonToken token = reader.peek();
                switch (token) {
                    case BEGIN_OBJECT:
                        reader.beginObject();
                        check = reader.nextName();
                        if (check.equals(key)) {
                            result = reader.nextString();
                        }
                        break;
                    case END_OBJECT:
                        reader.endObject();
                        break;
                    case NAME:
                        reader.nextName();
                        break;
                    case STRING:
                        reader.nextString();
                        break;
                    case NUMBER:
                        reader.skipValue();
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Generates a Wikipedia API URL string of the title given.
     *
     * @param title The title which should be searched for
     * @return The URL string
     */
    private String generateURLString(String title) {
        return "https://en.wikipedia.org/w/api.php?action=query&prop=pageimages&titles=" +
                title.replace(" ", "+") + "&pithumbsize=" + IMAGE_HEIGHT +
                "&format=json";
    }

    /**
     * Inner class containing the tasks for the thread. Calls the private
     * method for getting an image and sets it in the controller.
     */
    private class WikipediaRunnable implements Runnable {
        private String name;

        /**
         * Constructor.
         *
         * @param title The title Wikipedia should search for
         */
        public WikipediaRunnable(String title) {
            this.name = title;
        }

        @Override
        public void run() {
            Drawable drawable = getImage(name);
            controller.setImageInFragment(drawable);
        }
    }
}

