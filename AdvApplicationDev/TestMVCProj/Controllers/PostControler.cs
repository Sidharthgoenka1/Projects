using System;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using TestMVCProj.Models;

namespace TestMVCProj.Controllers
{
    public class PostController : Controller
    {
        private readonly AppDbContext _dbContext;

        public PostController(AppDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public IActionResult Index()
        {
            var posts = _dbContext.Posts.ToList();
            return View(posts);
        }

        public IActionResult Create()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Create(Post post)
        {
            post.CreatedAt = DateTime.Now;
            _dbContext.Posts.Add(post);
            _dbContext.SaveChanges();
            return RedirectToAction("Index");
        }
    }
}