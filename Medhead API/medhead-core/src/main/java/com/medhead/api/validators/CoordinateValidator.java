package com.medhead.api.validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoordinateValidator
{
    private static Logger logger = LoggerFactory.getLogger(CoordinateValidator.class);
    public static boolean coordinateIsValid(String coordinate)
    {
        boolean isValid = true;
        final String MIN_COORD_EXAMPLE = "000.0,000,0";
        if (coordinate.isEmpty())
            isValid = false;
        else if (coordinate.length() < MIN_COORD_EXAMPLE.length())
            isValid = false;
        else if (!coordinate.contains(","))
            isValid = false;
        else if (!coordinate.contains("."))
            isValid = false;
        else if (!coordinate.contains(" "))
            isValid = false;
        else if (!checkCoordinateValues(coordinate))
            isValid = false;
        if (!isValid)
            logger.error("Coordinates \"" + coordinate + "\" are not valid.");
        return isValid;
    }

    private static boolean checkCoordinateValues(String coordinate)
    {
        String longitude;
        String latitude;
        int comaSeparatorIndex = coordinate.indexOf(",");
        longitude = coordinate.substring(0, comaSeparatorIndex);
        latitude = coordinate.substring(comaSeparatorIndex + 1);
        try
        {
            if (Double.valueOf(longitude) > 180 ||Double.valueOf(longitude) < -180)
                return false;
            else if (Double.valueOf(latitude) > 180 ||Double.valueOf(latitude) < -180)
                return false;
        }
        catch (NumberFormatException exception)
        {
            logger.error("NumberFormatException when trying to parse value of longitude or latitude to double.");
            return false;
        }
        return true;
    }
}
