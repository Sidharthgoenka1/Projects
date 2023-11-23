using System.ComponentModel.DataAnnotations;

namespace LibraryManagementProject.Models
{
    public class LibraryBranch
    {
        [Key]
        public int LibraryBranchId { get; set; }

        [Required]
        public string BranchName { get; set; }
    }
}
