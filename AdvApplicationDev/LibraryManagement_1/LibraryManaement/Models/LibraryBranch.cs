using System.ComponentModel.DataAnnotations;

namespace LibraryManaement.Models
{
    public class LibraryBranch
    {
        [Key]
        public int LibraryBranchId { get; set; }

        [Required]
        public string BranchName { get; set; }
    }
}
