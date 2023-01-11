import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    private static String from = "43.499034,5.510685";
    private static String centreHospitalierAix = "43.535123,5.442709";
    private static String centreHospitalierMontPerrin = "43.520810,5.439959";

    public static void main(String args[])
    {
        List <String> destinations = new ArrayList<>();
        destinations.add(centreHospitalierAix);
        destinations.add(centreHospitalierMontPerrin);
        Direction direction = new Direction(from, destinations);

        String mapboxRequest = ApiUtil.createMapboxRequest(direction);
        ApiUtil.executePost(mapboxRequest, ""  );
    }
}
