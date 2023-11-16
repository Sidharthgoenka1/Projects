using System;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using TestMVCProj.Models;
using TestMVCProj.Data;

namespace TestMVCProj.Controllers
{
    public class AuthorController : Controller
    {
        private readonly AppDbContext _dbContext;

        public AuthorController(AppDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public IActionResult Index()
        {
            var authors = _dbContext.Authors.ToList();
            return View(authors);
        }

        public IActionResult Create()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Create(Author author)
        {
           
            _dbContext.Authors.Add(author);
            _dbContext.SaveChanges();
            return RedirectToAction("Index");
        }
    }
}