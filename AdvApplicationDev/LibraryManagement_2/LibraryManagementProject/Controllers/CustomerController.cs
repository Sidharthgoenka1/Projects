using System;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using LibraryManagementProject.Models;
using LibraryManagementProject.Data;

namespace LibraryManagementProject.Controllers
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
           if(customer.Name == null){
            return RedirectToAction("Errors");
           }else {
            _dbContext.Customers.Add(customer);
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

