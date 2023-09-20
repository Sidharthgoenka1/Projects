using Microsoft.AspNetCore.Mvc;
using TestMVCProj.Models;
using TestMVCProj.ViewModels;

namespace TestMVCProj.Controllers
{
    public class CustomerController : Controller
    {
        public IActionResult Details(int id)
        {
            Customer customer = new Customer
            {
               CustomerId = 1,
               Name = "Bob Doe"
            };

            CustomerViewModel viewModel = new CustomerViewModel
            {
                CustomerId = customer.CustomerId,
                Name = customer.Name,
            };

            return View(viewModel);
         }
    }
}