# E-Commerce Platform

## Description

This project is a simplified **e-commerce platform** that allows users to browse products, add them to a shopping cart, place orders, and view reports on the orders made. The platform differentiates between standard products and taxable products (where VAT is applied to the price). The data is managed in a relational database, and the platform is built with JavaFX for the UI and MySQL for the database.

## Features

### Home Page

- Displays all products available in the database with the following details:
  - **Title** of the product.
  - **Price** (with VAT included if applicable).
  - **"View Details" Button** to access the product page.
  
### Product Details Page

When a user clicks "View Details," they are redirected to the product's detailed page. This page includes:

- **Title** of the product.
- **Description** of the product.
- **Category/ies**: A product can belong to one or more categories.
- **Price** (with VAT included if applicable).
- **Quantity Selector**: The user can select the quantity to add to the cart.
- **"Add to Cart" Button**.

### Cart Page

The cart page displays a table of products the user has added to their cart. The table includes:

- **Order Number**: The number of the product in the cart.
- **Product Name**: The name of the product.
- **Quantity**: The quantity of the product selected by the user.
- **Price**: The unit price of the product (with VAT included if applicable).
- **Total**: The total cost for each product (`price * quantity`).
- **Delete Button**: The user can remove a product from the cart.
- **"Checkout" Button**: Redirects the user to the checkout page.

### Checkout Page

The checkout page includes a form for the user to input their details:

- **Name**
- **Email**
- **Phone Number**
- **Address**

The total amount of the order is displayed below the form. Upon submitting the form:

- The order is saved to the database.
- A success message is displayed to the user.

### Report Page

The report page displays orders containing products that match the following criteria:

- **Products priced below 100 lei**.
- **Products belonging to categories that contain more than 3 products**.

The table includes the following columns:

- **Order ID**
- **Order Date**
- **Customer Name**
- **Customer Email**
- **Number of Products in the Order**

The table is sorted by the number of products in the order. The data is directly fetched from the database.

## Technologies Used

- **JavaFX**: Used for building the user interface.
- **MySQL**: Used for data storage.
- **Java**: Used for backend logic and database interaction.
- **JDBC**: Used to connect Java to the MySQL database.

## Database Structure

The project uses the following database tables:

- **orders**: Contains order information (e.g., customer details, order total).
- **categories**: Stores information about the product categories.
- **products**: Stores information about non-taxable products.
- **taxable_products**: Stores information about taxable products (price includes VAT).
- **order_items**: Stores information about which products are part of an order.

## How to Use

1. **Browse Products**: On the home page, view all available products with their prices and click "View Details" for more information.
2. **Add to Cart**: On the product details page, select the quantity and add the product to the cart.
3. **Checkout**: Once in the cart, proceed to the checkout page, fill in your details, and place your order.
4. **View Reports**: On the report page, view a list of orders containing products priced under 100 lei from categories with more than 3 products.

## Future Improvements

- Implement user authentication for a more personalized shopping experience.
- Add pagination for large product listings.
