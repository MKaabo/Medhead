package Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class MapboxUtil
{
    private static Logger logger = LoggerFactory.getLogger(MapboxUtil.class);
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

        logger.debug(mapboxRequest);
        return mapboxRequest;
    }
}
