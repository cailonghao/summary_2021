// See https://aka.ms/new-console-template for more information

using course4.direct;

Console.WriteLine("Hello, World!");
//枚举
Week aa = Week.fri;
Console.WriteLine((byte)aa);
//结构体
Fly fly;
fly.name = "xxx";
fly.color = "white";
fly.size = 12;
Console.WriteLine(fly.size);
Console.WriteLine(fly.name);
Console.WriteLine(fly.color);
//类
Clazz clazz = new Clazz();
Console.WriteLine(clazz.Size);
//数组
int[] ii = new int[3];
ii[0] = 1234;
Console.WriteLine(ii[0]);
