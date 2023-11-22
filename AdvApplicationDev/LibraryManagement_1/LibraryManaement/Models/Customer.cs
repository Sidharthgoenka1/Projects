using System.ComponentModel.DataAnnotations;

namespace LibraryManaement.Models{
    public class Customer
    {
        [Key]
        public int CustomerId { get; set; }
        [Required]
        public string Name { get; set; }
    }
}