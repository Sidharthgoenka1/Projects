using System;
using System.ComponentModel.DataAnnotations;

namespace TestMVCProj.Models
{
    public class Post
    {
        [Key]
        public int Id { get; set; }
        
        [Required]
        public string Title { get; set; }
        public string? Content { get; set; }
        public DateTime CreatedAt { get; set; }
    }
}
