package Util;

import com.mapbox.services.api.directions.v5.DirectionsCriteria;
import com.mapbox.services.api.directions.v5.MapboxDirections;
import org.springframework.data.geo.Point;

// https://docs.mapbox.com/api/navigation/matrix/
public class MapboxApi
{
    /* void findDistance(Point clientPosition, ArrayList <Point> hospitalsPosition)
    {
        MapboxMatrix matrixApiClient = MapboxMatrix.builder()
                .accessToken(MAPBOX_ACCESS_TOKEN)
                .profile(DirectionsCriteria.PROFILE_DRIVING)
                .coordinates(listOfCoordinates)
                .build();

        MapboxDirections client = MapboxDirections.builder()
                .origin(origin)
                .destination(destination)
                .overview(DirectionsCriteria.OVERVIEW_FULL)
                .profile(DirectionsCriteria.PROFILE_DRIVING)
                .accessToken(MAPBOX_ACCESS_TOKEN)
                .build();
    } */
}
