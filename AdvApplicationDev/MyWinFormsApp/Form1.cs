namespace MyWinFormsApp;

public partial class Form1 : Form
{
    private Random random = new Random();
    public Form1()
    {
        InitializeComponent();
        Resize += new EventHandler(Form1_Resize);
    }

    private void Form1_Resize(object sender, EventArgs e)
    {
        // Generate random RGB values
        int r = random.Next(256);
        int g = random.Next(256);
        int b = random.Next(256);

        // Set the form's background color
        BackColor = Color.FromArgb(r, g, b);
    }

}
