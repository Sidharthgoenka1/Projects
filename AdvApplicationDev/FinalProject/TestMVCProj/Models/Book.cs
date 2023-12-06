using System;
using System.ComponentModel.DataAnnotations;

namespace TestMVCProj.Models
{
    public class Book
    {
        public int Id { get; set; }
        public string? Title { get; set; }
        public int AuthorId { get; set; }
        public int LibraryBranchId { get; set; }
    }
}

