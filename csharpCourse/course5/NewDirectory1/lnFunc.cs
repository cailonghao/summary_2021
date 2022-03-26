namespace course5.NewDirectory1;

public class Infunc
{
    public int init(int a)
    {
        a += 1;
        Console.WriteLine($"a = {a}");
        return a;
    }
    // a = 11

    struct MyStruct
    {
        public string name;
    }


    public int init()
    {
        int aa = 100;
        Console.WriteLine($"aa1 = {aa}");
        say(aa);
        Console.WriteLine($"aa3 = {aa}");
        say(ref aa);
        Console.WriteLine($"aa3 = {aa}");

        Console.WriteLine("=========================");
        MyClass cClass = new MyClass();
        cClass.ClassName = "测试";
        testClass(cClass);
        Console.WriteLine($"class = {cClass.ClassName}");
        Console.WriteLine("=========================");
        MyStruct myStruct = new MyStruct();
        myStruct.name = "小哥";
        testStruct(myStruct);
        Console.WriteLine($"class = {myStruct.name}");
        Console.WriteLine("=========================");
        string baseName = "一片漆黑";
        Console.WriteLine($"class = {baseName}");
        testStr(baseName);
        Console.WriteLine($"class = {baseName}");
        Console.WriteLine("=========================");
        return aa;
    }

    private void testStr(string baseName)
    {
        baseName = "大哥大啊啊";
        Console.WriteLine($"class = {baseName}");
    }

    private void testStruct(MyStruct myStruct)
    {
        myStruct.name = "大哥";
        Console.WriteLine($"class = {myStruct.name}");
    }

    private void testClass(MyClass cClass)
    {
        cClass.ClassName = "呵呵";
        Console.WriteLine($"class = {cClass.ClassName}");
    }

    public void say(ref int aa)
    {
        aa += 1;
        Console.WriteLine($"aa2 = {aa}");
    }

    public void say(int aa)
    {
        aa += 1;
        Console.WriteLine($"aa2 = {aa}");
    }
}