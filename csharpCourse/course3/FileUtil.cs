namespace course3;

public class FileUtil
{
    public void readFile()
    {
        DirectoryInfo directory = new DirectoryInfo(@"D:\secret");
        if (directory.Exists)
        {
            FileSystemInfo[] fileSystemInfos = directory.GetFileSystemInfos();
            foreach (var fsi in fileSystemInfos)
            {
                Console.WriteLine(fsi.Attributes);
                if((fsi.Attributes & FileAttributes.Directory) != 0)
                {
                    Console.WriteLine(fsi.Name);
                }
            }
            // FileInfo[] fileInfo = directory.GetFiles();
            // foreach (var file in fileInfo)
            // {
            //     Console.WriteLine(file.Name);
            // }
        }
    }
}