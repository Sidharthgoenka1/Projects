using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
 
class Client
{
    static async Task Main()
    {
        // Create an IP Endpoint
        //IPHostEntry ipHostInfo = await Dns.GetHostEntryAsync("host.contoso.com");
        String hostName = Dns.GetHostName();
        IPHostEntry ipHostInfo = await Dns.GetHostEntryAsync(hostName);
        IPAddress ipAddress = ipHostInfo.AddressList[0];
        IPEndPoint ipEndPoint = new(ipAddress, 11_000);
 
        // Create a Socket Client
        using Socket client = new(
            ipEndPoint.AddressFamily, 
            SocketType.Stream, 
            ProtocolType.Tcp);
 
        await client.ConnectAsync(ipEndPoint);
 
        while (true)
        {
            // Send message.
            var message = "Hi friends 👋!";
            var messageWithEOM = $"{message}END_OF_MESSAGE"; // Append the placeholder
            var messageBytes = Encoding.UTF8.GetBytes(messageWithEOM);
            try
            {
                _ = await client.SendAsync(messageBytes, SocketFlags.None);
                Console.WriteLine($"Socket client sent message: \"{message}\"");
 
                // Receive ack.
                var buffer = new byte[1_024];
                var received = await client.ReceiveAsync(buffer, SocketFlags.None);
                var response = Encoding.UTF8.GetString(buffer, 0, received);
 
                if (response == "")
                {
                    Console.WriteLine($"Socket client received acknowledgment: \"{response}\"");
                    break;
                }
            }
            catch (SocketException ex)
            {
                if (ex.SocketErrorCode == SocketError.ConnectionReset)
                {
                    // Handle the case where the server forcibly closed the connection
                    Console.WriteLine("Connection closed by the server.");
                    break;
                }
                else
                {
                    // Handle other socket exceptions
                    Console.WriteLine($"SocketException: {ex.SocketErrorCode}");
                    break;
                }
            }
        }
 
        // Shutdown the client socket
        Console.WriteLine("Press ENTER to continue...");
        Console.ReadLine();
    }
}
