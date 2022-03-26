namespace course5.NewDirectory1;

public class Yuan
{
    public void init()
    {
        var saas = (1, 1.2);
        Console.WriteLine(saas.Item1);
        var paas = getInit();
        Console.WriteLine(paas.Item1);
    }

    public (int, double) getInit()
    {
        return (1,2);
    }
}