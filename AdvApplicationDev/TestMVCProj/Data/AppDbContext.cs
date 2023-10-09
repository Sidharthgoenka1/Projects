using Microsoft.EntityFrameworkCore;

namespace TestMVCProj
{
public class AppDbContext : DbContext
    {
        public DbSet<Models.Post> Posts { get; set; }

        public DbSet<Models.Book> Books { get; set; }

        private readonly IConfiguration _configuration;
        public AppDbContext(IConfiguration configuration)
        {
            _configuration = configuration;
        }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlite(_configuration.GetConnectionString("DefaultConnection"));
        }
    }
}
