using System;
using System.ComponentModel.DataAnnotations;

namespace TestMVCProj.Models
{
    public class Book
    {
        [Key]
        public int BookId { get; set; }
        
        [Required]
        public string Title { get; set; }

        [Required]
        public int AuthorId { get; set; }
    }
}