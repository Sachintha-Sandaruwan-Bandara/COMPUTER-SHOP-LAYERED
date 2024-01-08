package lk.ijse.computerShop.bo;
/* 
    @author Sachi_S_Bandara
    @created 1/8/2024 - 10:02 PM 
*/

public class AddCustomer {

    private String customerName;
    private String customerEmail;
    private String customerPhoneNumber;

    // Constructor
    public AddCustomer(String name, String email, String phoneNumber) {
        this.customerName = name;
        this.customerEmail = email;
        this.customerPhoneNumber = phoneNumber;
    }

    // Getter and Setter methods
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    // Method to add customer (You can customize this method based on your needs)
    public void addCustomerToDatabase() {
        // Assuming you have some database connection and logic to add a customer
        // Example: Add customer to a MySQL database
        // You need to replace the following code with your actual database logic

        // Connection connection = DriverManager.getConnection("your_database_url", "username", "password");
        // PreparedStatement statement = connection.prepareStatement("INSERT INTO customers (name, email, phone) VALUES (?, ?, ?)");
        // statement.setString(1, customerName);
        // statement.setString(2, customerEmail);
        // statement.setString(3, customerPhoneNumber);
        // statement.executeUpdate();

        // Close resources (Connection, Statement, etc.) after use
        // statement.close();
        // connection.close();

        // Print a message to indicate success or handle exceptions accordingly
        System.out.println("Customer added successfully!");
    }

    // Main method for testing
    public static void main(String[] args) {
        // Create an instance of AddCustomer
        AddCustomer customer = new AddCustomer("John Doe", "john.doe@email.com", "1234567890");

        // Add the customer to the database
        customer.addCustomerToDatabase();
    }
}

