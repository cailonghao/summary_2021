namespace course3;

public class BuyBook
{
    public void bug(int price)
    {
        if (price < 10)
        {
            Console.WriteLine("只能购买狗粮");
        }
        else if (price > 10 && price < 20)
        {
            Console.WriteLine("购买一只狗");
        }
        else
        {
            Console.WriteLine("买来尊重");
        }
    }

    public void buyWater(String drink)
    {
        switch (drink)
        {
            case "water":
                Console.WriteLine("矿泉水");
                break;
            case "cole":
                Console.WriteLine("可怜");
                break;
        }
    }
}