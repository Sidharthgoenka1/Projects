using System.ComponentModel.DataAnnotations;

namespace LibraryManagementProject.Models{
    public class Author
    {
        [Key]
        public int AuthorId { get; set; }

        [Required]
        public string Name { get; set; }
    }
}