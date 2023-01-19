import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;

public class MapboxUtil
{
    private static final String TOKEN = "pk.eyJ1IjoibWthYWJvdWNoIiwiYSI6ImNsYzdzOWJtZTAxb2kzb3I1eTF0eGY5NjMifQ.PCAopOMvelzbkR7uWlUKBA";
    private static final String BASE_URL = "https://api.mapbox.com/directions-matrix/v1/";
    private static final String PROFILE = "mapbox/driving-traffic";
    private static final String approach = "approaches=curb;curb;curb";
    public static String getDirectionMatrix(String from, List<String> destinations)
    {
        // Set up a request with base url + driving traffic profile
        String mapboxRequest = BASE_URL + PROFILE + '/';

        // add coordinates
        String coordinates = from;
        for (String possibleDestination : destinations)
            coordinates = coordinates.concat(';' + possibleDestination);
        mapboxRequest += coordinates;

        // add approach
        mapboxRequest += "?" + approach;
        // add api token
        final String apiToken = "&access_token=" + TOKEN;
        mapboxRequest += apiToken;

        System.out.println(mapboxRequest);
        return mapboxRequest;
    }
    public static String executePost(String targetURL, String urlParameters)
    {
        final String charset = "UTF-8";
        final String param1 = "";
        final String param2 = "";
        String query = null;
        try
        {
            query = String.format("param1=%s&param2=%s",
                    URLEncoder.encode(param1, charset),
                    URLEncoder.encode(param2, charset));
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        URLConnection connection = null;
        try
        {
//            connection = new URL(targetURL + "?" + query).openConnection();
            connection = new URL(targetURL).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            InputStream response = connection.getInputStream();
            try (Scanner scanner = new Scanner(response))
            {
                String responseBody = scanner.useDelimiter("\\A").next();
                System.out.println(responseBody);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
