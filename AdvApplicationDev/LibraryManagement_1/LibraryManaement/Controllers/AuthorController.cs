using System;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using LibraryManaement.Models;
using LibraryManaement.Data;

namespace LibraryManaement.Controllers
{
    public class AuthorController : Controller
    {
        private readonly ApplicationDbContext _dbContext;

        public AuthorController(ApplicationDbContext dbContext)
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