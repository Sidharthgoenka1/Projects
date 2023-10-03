using System;
using System.Data;
using System.Data.SQLite;
class Program
{
    static void Main()
    {
        // Connection string for SQLite database 
        string connectionString = "Data Source=BookSeller.db;Version=3;";
        using (SQLiteConnection connection = new SQLiteConnection(connectionString))
        {
            connection.Open();

            // Create the Customer table
            string createCustomerTable = "CREATE TABLE IF NOT EXISTS Customer (" +
                    "CustomerId INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "FirstName TEXT," + "LastName TEXT," +
                    "Email TEXT," +
                    "Phone TEXT," +
                    "Address TEXT)";

            string insertCustomerData = "INSERT INTO Customer " +
              "(FirstName, LastName, Email, Phone, Address) VALUES " +
              "('John', 'Doe', 'john@example.com', '123-456-7890', '123 Main St'), " +
              "('Alice', 'Smith', 'alice@example.com', '987-654-3210', '456 Elm St'), " +
              "('Bob', 'Johnson', 'bob@example.com', '555-123-4567', '789 Oak St')";

            /*using (SQLiteCommand createCustomerCmd = new SQLiteCommand(insertCustomerData, connection))
            {
                createCustomerCmd.ExecuteNonQuery();
            }
            Console.WriteLine("Tables created successfully.");*/

            // SQL SELECT statement to retrieve data from the Customer table
            /*string selectCustomerData = "SELECT * FROM Customer";

            using (SQLiteCommand selectCustomerCmd = new SQLiteCommand(selectCustomerData, connection))
            {
                using (SQLiteDataReader reader = selectCustomerCmd.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        // Access columns by their names or indices (0-based)
                        int customerId = reader.GetInt32(reader.GetOrdinal("CustomerId"));
                        string firstName = reader.GetString(reader.GetOrdinal("FirstName"));
                        string lastName = reader.GetString(reader.GetOrdinal("LastName"));
                        string email = reader.GetString(reader.GetOrdinal("Email"));
                        string phone = reader.GetString(reader.GetOrdinal("Phone"));
                        string address = reader.GetString(reader.GetOrdinal("Address"));

                        // Display retrieved data
                        Console.WriteLine($"Customer ID: {customerId}");
                        Console.WriteLine($"Name: {firstName} {lastName}");
                        Console.WriteLine($"Email: {email}");
                        Console.WriteLine($"Phone: {phone}");
                        Console.WriteLine($"Address: {address}");
                    }
                }
            }*/


            /*// Define the customer ID you want to update
            int customerIdToUpdate = 1;

            // Define the new values for the customer
            string newEmail = "updated@example.com";

            // SQL UPDATE statement to update the customer's information
            string updateCustomerData = "UPDATE Customer " +
                "SET Email = @Email " +
                "WHERE CustomerId = @CustomerId";
            using (SQLiteCommand updateCustomerCmd = new SQLiteCommand(updateCustomerData, connection))
            {
                // Set parameters to avoid SQL injection
                updateCustomerCmd.Parameters.AddWithValue("@Email", newEmail);
                updateCustomerCmd.Parameters.AddWithValue("@CustomerId", customerIdToUpdate);

                // Execute the UPDATE statement
                int rowsAffected = updateCustomerCmd.ExecuteNonQuery();

                if (rowsAffected > 0)
                {
                    Console.WriteLine($"Customer with ID {customerIdToUpdate} updated successfully.");
                }
                else
                {
                    Console.WriteLine($"No customer found with ID {customerIdToUpdate}.");
                }
            }*/


            int customerIdToDelete = 1;

            // SQL DELETE statement to delete the customer's record
            string deleteCustomerData = "DELETE FROM Customer WHERE CustomerId = @CustomerId";

            using (SQLiteCommand deleteCustomerCmd = new SQLiteCommand(deleteCustomerData, connection))
            {
                // Set parameter to specify the customer ID to be deleted
                deleteCustomerCmd.Parameters.AddWithValue("@CustomerId", customerIdToDelete);

                // Execute the DELETE statement
                int rowsAffected = deleteCustomerCmd.ExecuteNonQuery();

                if (rowsAffected > 0)
                {
                    Console.WriteLine($"Customer with ID {customerIdToDelete} deleted successfully.");
                }
                else
                {
                    Console.WriteLine($"No customer found with ID {customerIdToDelete}.");
                }
            }


            connection.Close();
        }
    }
}
