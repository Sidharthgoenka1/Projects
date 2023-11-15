using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using Xunit;
using Microsoft.AspNetCore.Mvc.Testing;
namespace BlogApp.IntegrationTests
{
    public class BlogAppRouteShould : IClassFixture<WebApplicationFactory<Program>>
    {
        private readonly WebApplicationFactory<Program> _factory;
        public BlogAppRouteShould(WebApplicationFactory<Program> factory)
        {
            _factory = factory;
        }
        [Theory]
        //[InlineData("/")]
        [InlineData("/Home")]
        [InlineData("/Home/Privacy")]
        //[InlineData("/Post/View/1")]
        public async Task CheckPageStatus(string url)
        {
        // Arrange
        var client = _factory.CreateClient();
        // Act
        var response = await client.GetAsync(url);
        // Assert
        
        Assert.Equal(HttpStatusCode.OK, response.StatusCode);
        Assert.Equal("text/html; charset=utf-8",response.Content.Headers.ContentType.ToString());
        }
    }
    
}
