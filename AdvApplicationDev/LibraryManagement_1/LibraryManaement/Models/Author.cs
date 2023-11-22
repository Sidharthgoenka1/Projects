using System.ComponentModel.DataAnnotations;

namespace LibraryManaement.Models{
    public class Author
    {
        [Key]
        public int AuthorId { get; set; }

        [Required]
        public string Name { get; set; }
    }
}