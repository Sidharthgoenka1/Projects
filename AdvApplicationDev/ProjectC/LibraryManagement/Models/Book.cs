using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace LibraryManagement.Models;
    public class Book
    {
        [Key]
        public int BookId { get; set; }
        [Required]
        public string Title { get; set; }
        [ForeignKey("Author")][Required]
        public int AuthorId { get; set; }
        [ForeignKey("LibraryBranch")][Required]
        public int LibraryBranchId { get; set; }
    }