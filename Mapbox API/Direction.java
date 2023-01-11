import java.util.ArrayList;
import java.util.List;

public class Direction
{
    private String from;
    private List<String> destinations;

    public Direction(String from, List <String> destinations)
    {
        this.from = from;
        if (destinations.size() > 25 || destinations.size() < 2)
            System.err.println("The number of coordinates should be between two and 25.");
        this.destinations = new ArrayList<>(destinations);
    }

    public String getFrom()
    {
        return from;
    }

    public List<String> getDestinations()
    {
        return destinations;
    }

}
