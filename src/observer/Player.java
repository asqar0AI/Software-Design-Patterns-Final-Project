package observer;

// Observer: chess game observer
public class Player implements PlayerInterface
{
    public final String name;
    
    public Player(String name)
    {
        this.name = name;
    }
    
    public void update(String message)
    {
        System.out.println(message);
    }
}
