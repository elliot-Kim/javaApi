package NaverNews;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class NaverNewsSearch {

private static final String BASE_URL = "https://openapi.naver.com/v1/search/news.json";

	public static void main(String[] args) throws MalformedURLException {
		
		
        
    	String text = null;
		try {
			text = URLEncoder.encode("SK건설", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        String apiURL = "https://openapi.naver.com/v1/search/news?query="+ text; // json 결과
        //String apiURL = "https://openapi.naver.com/v1/search/news.json?query="+ text; // xml 결과
        URL url = new URL(apiURL);
     
		
		try {
			System.out.println(NaverNews.NetworkUtil.makeHTTPRequest(url));
		} catch (IOException e) {
			System.out.println("error");
		}
	
	}

}
