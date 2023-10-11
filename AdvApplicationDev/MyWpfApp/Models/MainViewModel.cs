using System.ComponentModel;
namespace MyWpfApp
{
    public class MainViewModel : INotifyPropertyChanged
    {
        private string? userName;
        public string UserName
        {
        get { return userName; }
        set
        {
            if (userName != value)
            {
                userName = value;
                OnPropertyChanged(nameof(UserName));
            }
        }
        }

        public event PropertyChangedEventHandler? PropertyChanged;

        protected virtual void OnPropertyChanged(string propertyName)
        {
            if (PropertyChanged != null)
             {
                PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
             }
        }
    }
}
