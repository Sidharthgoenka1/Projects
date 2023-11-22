using System;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using LibraryManaement.Models;
using LibraryManaement.Data;

namespace LibraryManaement.Controllers
{
    public class CustomerController : Controller
    {
        private readonly ApplicationDbContext _dbContext;

        public CustomerController(ApplicationDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public IActionResult Index()
        {
            var customers = _dbContext.Customers.ToList();
            return View(customers);
        }

        public IActionResult Create()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Create(Customer customer)
        {
           
            _dbContext.Customers.Add(customer);
            _dbContext.SaveChanges();
            return RedirectToAction("Index");
        }
    }
}

