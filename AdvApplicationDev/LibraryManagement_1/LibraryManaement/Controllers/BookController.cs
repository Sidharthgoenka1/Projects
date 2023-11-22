using System;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using LibraryManaement.Models;
using LibraryManaement.Data;

namespace LibraryManaement.Controllers
{
    public class BookController : Controller
    {
        private readonly ApplicationDbContext _dbContext;

        public BookController(ApplicationDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public IActionResult Index()
        {
            var books = _dbContext.Books.ToList();
            return View(books);
        }

        public IActionResult Create()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Create(Book book)
        {
           
            _dbContext.Books.Add(book);
            _dbContext.SaveChanges();
            return RedirectToAction("Index");
        }
    }
}