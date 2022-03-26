namespace course5.NewDirectory1;

public class MyClass
{
    private string className;

    public string ClassName
    {
        get => className;
        set => className = value ?? throw new ArgumentNullException(nameof(value));
    }
}