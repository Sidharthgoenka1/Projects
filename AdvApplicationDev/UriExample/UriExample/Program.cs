using System;
 
class Program
{
    static void Main()
    {
        const string uriString = "https://www.fdu.edu/campuses/vancouver-campus/about-vancouver-campus/this-month/campus-updates/#ugtuition";
        Uri canonicalUri = new(uriString);
 
        Console.WriteLine(canonicalUri.Host);
        Console.WriteLine(canonicalUri.PathAndQuery);
        Console.WriteLine(canonicalUri.Fragment);
    }
}