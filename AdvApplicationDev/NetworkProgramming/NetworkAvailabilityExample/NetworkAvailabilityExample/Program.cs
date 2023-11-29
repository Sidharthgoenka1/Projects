 using System;
using System.Net.NetworkInformation;
using System.Linq;
 
class Program
{
    static void Main()
    {
        
        NetworkChange.NetworkAvailabilityChanged += OnNetworkAvailabilityChanged;
        Console.WriteLine("Listening changes in network availability. Press any key to continue.");
        Console.ReadLine();
        NetworkChange.NetworkAvailabilityChanged -= OnNetworkAvailabilityChanged;
 
        NetworkChange.NetworkAddressChanged += OnNetworkAddressChanged;
        Console.WriteLine("Listening for address changes. Press any key to continue.");
        Console.ReadLine();
        NetworkChange.NetworkAddressChanged -= OnNetworkAddressChanged;
        
        ShowStatistics(NetworkInterfaceComponent.IPv4);
        ShowStatistics(NetworkInterfaceComponent.IPv6);
 
        PingRemoteHost("fdu.edu").Wait();
    }
 
    static void OnNetworkAvailabilityChanged(object? sender, NetworkAvailabilityEventArgs networkAvailability)
    {
        Console.WriteLine($"Network is available: {networkAvailability.IsAvailable}");
    }
 
    static void OnNetworkAddressChanged(object? sender, EventArgs args)
    {
        foreach ((string name, OperationalStatus status) in
            NetworkInterface.GetAllNetworkInterfaces()
                .Select(networkInterface =>
                    (networkInterface.Name, networkInterface.OperationalStatus)))
        {
            Console.WriteLine($"{name} is {status}");
        }
    }
 
    static void ShowStatistics(NetworkInterfaceComponent version)
    {
        var properties = IPGlobalProperties.GetIPGlobalProperties();
        var stats = version switch
        {
            NetworkInterfaceComponent.IPv4 => properties.GetTcpIPv4Statistics(),
            _ => properties.GetTcpIPv6Statistics()
        };
 
        Console.WriteLine($"TCP/{version} Statistics");
        Console.WriteLine($"  Minimum Transmission Timeout : {stats.MinimumTransmissionTimeout:#,#}");
        Console.WriteLine($"  Maximum Transmission Timeout : {stats.MaximumTransmissionTimeout:#,#}");
        Console.WriteLine("  Connection Data");
        Console.WriteLine($"      Current :                  {stats.CurrentConnections:#,#}");
        Console.WriteLine($"      Cumulative :               {stats.CumulativeConnections:#,#}");
        Console.WriteLine($"      Initiated  :               {stats.ConnectionsInitiated:#,#}");
        Console.WriteLine($"      Accepted :                 {stats.ConnectionsAccepted:#,#}");
        Console.WriteLine($"      Failed Attempts :          {stats.FailedConnectionAttempts:#,#}");
        Console.WriteLine($"      Reset :                    {stats.ResetConnections:#,#}");
        Console.WriteLine("  Segment Data");
        Console.WriteLine($"      Received :                 {stats.SegmentsReceived:#,#}");
        Console.WriteLine($"      Sent :                     {stats.SegmentsSent:#,#}");
        Console.WriteLine($"      Retransmitted :            {stats.SegmentsResent:#,#}");
        Console.WriteLine();
    }
 
    static async System.Threading.Tasks.Task PingRemoteHost(string hostName)
    {
        using Ping ping = new();
        PingReply reply = await ping.SendPingAsync(hostName);
 
        Console.WriteLine($"Ping status for ({hostName}): {reply.Status}");
        if (reply is { Status: IPStatus.Success })
        {
            Console.WriteLine($"Address: {reply.Address}");
            Console.WriteLine($"Roundtrip time: {reply.RoundtripTime}");
            Console.WriteLine($"Time to live: {reply.Options?.Ttl}");
            Console.WriteLine();
        }
    }
}
