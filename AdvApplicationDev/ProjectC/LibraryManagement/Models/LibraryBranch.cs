// LibraryBranch.cs
using System;
using System.ComponentModel.DataAnnotations;
using System.Diagnostics.CodeAnalysis;

namespace LibraryManagement.Models;
public class LibraryBranch
{
    [Key]
    public int LibraryBranchId { get; set; }
    [Required]
    public required string BranchName { get; set; }
}