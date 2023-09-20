using Microsoft.AspNetCore.Mvc;
using TestMVCProj.Models;
using TestMVCProj.ViewModels;

namespace TestMVCProj.Controllers
{
    public class LibraryBranchController : Controller
    {
        public IActionResult Details(int id)
        {

            LibraryBranch branch = new LibraryBranch
            {
                LibraryBranchId = 1,
                BranchName = "Main Branch"
            };

            LibraryBranchViewModel viewModel = new LibraryBranchViewModel
            {
                LibraryBranchId = branch.LibraryBranchId,
                BranchName = branch.BranchName
            };

            return View(viewModel);
         }
    }
}