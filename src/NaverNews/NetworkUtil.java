package NaverNews;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class NetworkUtil {
    /**
     * Helper method
     * make HTTP connection
     *
     * @param url from createUrl()
     * @return Json response
     * @throws IOException
     */
    public static String makeHTTPRequest(URL url) throws IOException{
        // state empty jsonResponse
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        // state empty URLConnection instance
        HttpURLConnection urlConnection = null;
        // state empty inputStream instance
        InputStream inputStream = null;
        
        String clientId = "rK35A01OE9goo5cGNKUi";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "jEzZZe5sPb";//애플리케이션 클라이언트 시크릿값";

        // try HTTP connection with given url
        try {
            // set connection
            urlConnection = (HttpURLConnection) url.openConnection();
            // set request method as 'GET'
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("X-Naver-Client-Id", clientId);
            urlConnection.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            // connect
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            // using helper method 'readFromStream()'
            System.out.println(urlConnection.getResponseCode());
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            // whether connection is success or not
            // disconnect urlConnection & close inputStream
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

	private static String readFromStream(InputStream inputStream) {
        //state empty StringBuilder for
        // taking json response
        StringBuilder output = new StringBuilder();

        // check given inputStream is null or not
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            try {
                // state empty string and take 1 line from bufferedReader instance
                String line = reader.readLine();

                // append to output to the end of bufferedReader instance
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // return StringBuilder instance as jsonResponse
        return output.toString();
    }
}
