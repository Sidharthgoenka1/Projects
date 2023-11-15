using System;
using System.Threading.Tasks;
using BlogApp.Models;
using BlogApp.Controllers;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.InMemory; 
using Xunit;
namespace BlogApp.UnitTests
{
public class PostControllerShould
    {
    [Fact]
        public async Task CreateNewPostWithCreatedDate()
        {
            var options = new DbContextOptionsBuilder<AppDbContext>()
            .UseInMemoryDatabase(databaseName: "test").Options;
            // Set up a context (connection to the "DB") for writing
            using (var context = new AppDbContext(options))
            {
            var service = new PostController(context);
            service.Create(new Post
                {
                    Id =123,
                    Title ="Test Post",
                    Content ="This is a test post",
                    CreatedAt= DateTime.Now
                });
            }
            // Use a separate context to read data back from the "DB"
            using (var context = new AppDbContext(options))
            {
                var itemsInDatabase = await context
                .Posts.CountAsync();
                Assert.Equal(1, itemsInDatabase);
                var item = await context.Posts.FirstAsync();
                Assert.Equal(123, item.Id);
                Assert.Equal("Test Post", item.Title);
                Assert.Equal("This is a test post", item.Content);
                // Item should be due 3 days from now (give or take a second)
                var difference = DateTime.Now - item.CreatedAt;
                Assert.True(difference < TimeSpan.FromSeconds(5));
            }
        }
    }
}