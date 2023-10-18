using System.ComponentModel;

namespace EventHandlingDemo;

public partial class Form1 : Form
{
    private readonly EventHandlerList eventHandlers = new EventHandlerList();
    private static readonly object MouseUpEventKey = new object();
    private static readonly object MouseDownEventKey = new object();

    public Form1()
    {
        InitializeComponent();
        this.MouseUpEvent += Form1_MouseUp;
        this.MouseDownEvent += Form1_MouseDown;
    }
    public event MouseEventHandler MouseUpEvent
    {
        add { eventHandlers.AddHandler(MouseUpEventKey, value); } // Add accessor method.
        remove { eventHandlers.RemoveHandler(MouseUpEventKey, value); } // Remove accessor method.
    }

    public event MouseEventHandler MouseDownEvent
    {
        add { eventHandlers.AddHandler(MouseDownEventKey, value); } // Add accessor method.
        remove { eventHandlers.RemoveHandler(MouseDownEventKey, value); } // Remove accessor method.
    }
    
    protected override void OnMouseUp(MouseEventArgs e)
     {
         MouseEventHandler handler = (MouseEventHandler)eventHandlers[MouseUpEventKey]; // Retrieve event handler.
         handler?.Invoke(this, e); // Invoke the event handler if it's not null.
     }

     // Overrides the base class's OnMouseDown method of the form.
     protected override void OnMouseDown(MouseEventArgs e)
     {
         MouseEventHandler handler = (MouseEventHandler)eventHandlers[MouseDownEventKey]; // Retrieve event handler.
         handler?.Invoke(this, e); // Invoke the event handler if it's not null.
     }

     // Event handler for MouseUp event.
     private void Form1_MouseUp(object sender, MouseEventArgs e)
     {
         this.Text = "Mouse Up Event Triggered"; // Set the form's title when MouseUp event is triggered.
     }

     // Event handler for MouseDown event.
     private void Form1_MouseDown(object sender, MouseEventArgs e)
     {
         this.Text = "Mouse Down Event Triggered"; // Set the form's title when MouseDown event is triggered.
     }
}
