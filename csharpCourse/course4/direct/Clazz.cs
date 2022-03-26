namespace course4.direct;

public class Clazz
{
    private string name;
    private string color;
    private int size = 0;

    public string Name
    {
        get => name;
        set => name = value ?? throw new ArgumentNullException(nameof(value));
    }

    public string Color
    {
        get => color;
        set => color = value ?? throw new ArgumentNullException(nameof(value));
    }

    public int Size
    {
        get => size;
        set => size = value;
    }
}