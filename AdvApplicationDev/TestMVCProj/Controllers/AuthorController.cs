using Microsoft.AspNetCore.Mvc;
using TestMVCProj.Models;
using TestMVCProj.ViewModels;

namespace TestMVCProj.Controllers
{
    public class AuthorController : Controller
    {
        public IActionResult Details()
        {

            Author author = new Author
            {
               AuthorId = 1,
               Name = "John Doe"
            };


            AuthorViewModel viewModel = new AuthorViewModel
            {
                AuthorId = author.AuthorId,
                Name = author.Name
            };

            return View(viewModel);
         }
    }
}