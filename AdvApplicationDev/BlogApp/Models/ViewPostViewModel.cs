using System.Collections.Generic;
namespace BlogApp.Models
{
    public class ViewPostViewModel
    {
        public List<Post> Posts { get; set; }
        public Post SelectedPost { get; set; }
        public Post PreviousPost { get; set; }
        public Post NextPost { get; set; }
        public bool HasPrevious { get; set; }
        public bool HasNext { get; set; }
    }
}
