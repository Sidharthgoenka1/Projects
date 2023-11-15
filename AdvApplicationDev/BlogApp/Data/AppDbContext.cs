using Microsoft.EntityFrameworkCore;

namespace BlogApp
{
public class AppDbContext : DbContext
    {
        public DbSet<Models.Post> Posts { get; set; }
        private readonly IConfiguration _configuration;

        public AppDbContext(DbContextOptions<AppDbContext> options) : base(options){
            
        }
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
