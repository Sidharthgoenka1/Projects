using Microsoft.AspNetCore.Mvc;
using TestMVCProj.Models;
using TestMVCProj.ViewModels;

namespace TestMVCProj.Controllers
{
    public class LibraryBranchController : Controller
    {
        private readonly AppDbContext _dbContext;

        public LibraryBranchController(AppDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public IActionResult IndexLibraryBranch()
        {
            var branches = _dbContext.LibraryBranch.ToList();
            return View(branches);
        }

        public IActionResult CreateLibraryBranch()
        {
            return View();
        }

        [HttpPost]
        public IActionResult CreateLibraryBranch(LibraryBranch branch)
        {
            
            _dbContext.LibraryBranch.Add(branch);
            _dbContext.SaveChanges();
            return RedirectToAction("IndexLibraryBranch");
        }



        // public IActionResult Details(int id)
        // {

        //     LibraryBranch branch = new LibraryBranch
        //     {
        //         LibraryBranchId = 1,
        //         BranchName = "Main Branch"
        //     };

        //     LibraryBranchViewModel viewModel = new LibraryBranchViewModel
        //     {
        //         LibraryBranchId = branch.LibraryBranchId,
        //         BranchName = branch.BranchName
        //     };

        //     return View(viewModel);
        //  }
    }
}