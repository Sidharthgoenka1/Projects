using System;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using BlogApp.Models;

namespace BlogApp.Controllers
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

    public IActionResult View(int id)
    {
     var post = _dbContext.Posts.Find(id);   
     
     if (post == null)
     {
      return NotFound();
     } 
     
     var posts = _dbContext.Posts.ToList();
     var index = posts.FindIndex(p => p.Id == id); 
     var viewModel = new ViewPostViewModel
     {
      Posts = posts,
      SelectedPost = post,
      HasPrevious = index > 0,
      HasNext = index < posts.Count - 1,
      PreviousPost = index > 0 ? posts[index - 1] : null,
      NextPost = index < posts.Count - 1 ? posts[index + 1] : null
     }; 
     return View(viewModel);
 }

    }
}