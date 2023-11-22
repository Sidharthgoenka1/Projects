using System;
using System.Linq;
using Microsoft.AspNetCore.Mvc;
using LibraryManaement.Models;
using LibraryManaement.Data;

namespace LibraryManaement.Controllers
{
    public class LibraryBranchController : Controller
    {
        private readonly ApplicationDbContext _dbContext;

        public LibraryBranchController(ApplicationDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public IActionResult Index()
        {
            var branches = _dbContext.LibraryBranches.ToList();
            return View(branches);
        }

        public IActionResult Create()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Create(LibraryBranch branch)
        {
           
            _dbContext.LibraryBranches.Add(branch);
            _dbContext.SaveChanges();
            return RedirectToAction("Index");
        }
    }
}