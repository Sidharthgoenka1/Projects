using System;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using TestMVCProj.Models;
using TestMVCProj.Data;

namespace TestMVCProj.Controllers
{
    public class CustomerController : Controller
    {
        private readonly AppDbContext _dbContext;

        public CustomerController(AppDbContext dbContext)
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



// using Microsoft.AspNetCore.Mvc;
// using TestMVCProj.Models;
// using TestMVCProj.ViewModels;

// namespace TestMVCProj.Controllers
// {
//     public class CustomerController : Controller
//     {
//         public IActionResult Details(int id)
//         {
//             Customer customer = new Customer
//             {
//                CustomerId = 1,
//                Name = "Bob Doe"
//             };

//             CustomerViewModel viewModel = new CustomerViewModel
//             {
//                 CustomerId = customer.CustomerId,
//                 Name = customer.Name,
//             };

//             return View(viewModel);
//          }
//     }
// }