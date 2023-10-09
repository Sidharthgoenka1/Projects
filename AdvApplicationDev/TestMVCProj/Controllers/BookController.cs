using System;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using TestMVCProj.Models;

namespace TestMVCProj.Controllers
{
    public class BookController : Controller
    {

        private readonly AppDbContext _dbContext;

        public BookController(AppDbContext dbContext)
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
            // book.CreatedAt = DateTime.Now;
            _dbContext.Books.Add(book);
            _dbContext.SaveChanges();
            return RedirectToAction("BookDetails");
        }
    }
}
