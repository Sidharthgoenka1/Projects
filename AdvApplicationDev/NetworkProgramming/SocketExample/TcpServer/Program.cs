using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
 
class Program
{
    static async Task Main()
    {
        // Step 1: Create an IP Endpoint
        // IPHostEntry ipHostInfo = await Dns.GetHostEntryAsync("host.contoso.com");
        String hostName = Dns.GetHostName();
        IPHostEntry ipHostInfo = await Dns.GetHostEntryAsync(hostName);
        IPAddress ipAddress = ipHostInfo.AddressList[0];
        IPEndPoint ipEndPoint = new(ipAddress, 11_000);
 
        // Step 2: Create a Socket Server
        using Socket listener = new(
            ipEndPoint.AddressFamily,
            SocketType.Stream,
            ProtocolType.Tcp);
 
        listener.Bind(ipEndPoint);
        listener.Listen(100);
 
        Console.WriteLine($"Socket server starting... Found: {ipAddress} available on port 11,000.");
        
        // Step 3: Accept incoming connections and communicate with clients
        var handler = await listener.AcceptAsync();
        while (true)
        {
            // Receive message.
            var buffer = new byte[1_024];
            var received = await handler.ReceiveAsync(buffer, SocketFlags.None);
            var response = Encoding.UTF8.GetString(buffer, 0, received);
            
            var eom = "END_OF_MESSAGE";
            if (response.Contains(eom))
            {
                response = response.Replace(eom, "");
                Console.WriteLine($"Socket server received message: \"{response}\"");
 
                var ackMessage = "ACKNOWLEDGED";
                var echoBytes = Encoding.UTF8.GetBytes(ackMessage);
                await handler.SendAsync(echoBytes, SocketFlags.None);
                Console.WriteLine($"Socket server sent acknowledgment: \"{ackMessage}\"");
 
                break;
            }
        }
 
        // Step 4: Shutdown the server socket
        Console.WriteLine("Press ENTER to continue...");
        Console.ReadLine();
    }
}