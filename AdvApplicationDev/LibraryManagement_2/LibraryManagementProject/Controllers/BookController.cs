using System;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using LibraryManagementProject.Models;
using LibraryManagementProject.Data;

namespace LibraryManagementProject.Controllers
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
           if(book.Title == null){
            return RedirectToAction("Errors");
           }else {
            _dbContext.Books.Add(book);
            _dbContext.SaveChanges();
            return RedirectToAction("Index");
           }
        }

        public IActionResult Errors()
        {
            return View("Errors");
        }
    }
}