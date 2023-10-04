namespace MyWinFormsApp;

partial class Form1
{
    /// <summary>
    ///  Required designer variable.
    /// </summary>
    private System.ComponentModel.IContainer components = null;

    /// <summary>
    ///  Clean up any resources being used.
    /// </summary>
    /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
    protected override void Dispose(bool disposing)
    {
        if (disposing && (components != null))
        {
            components.Dispose();
        }
        base.Dispose(disposing);
    }

    #region Windows Form Designer generated code

    /// <summary>
    ///  Required method for Designer support - do not modify
    ///  the contents of this method with the code editor.
    /// </summary>
    private void InitializeComponent()
    {
        this.components = new System.ComponentModel.Container();
        this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
        this.ClientSize = new System.Drawing.Size(800, 450);
        this.Text = "Form1";
        Button btn = new Button();
        btn.Size = new System.Drawing.Size(100,50);
        btn.Location = new System.Drawing.Point(50,50);
        btn.Text = "Click";
        btn.ForeColor = Color.White;
        btn.BackColor = Color.Blue;
        btn.Font = new Font("Arial", 12, FontStyle.Bold);
        btn.Click += (sender,e) => MessageBox.Show("Clicked");
        this.Controls.Add(btn);
    }

    #endregion
}
