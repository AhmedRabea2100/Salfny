# Salfny
Online rental website. Implementation using Angular, Springboot, and MySQL JPA.
---

![logo](https://user-images.githubusercontent.com/77025553/209586849-10f38728-ca0b-44da-83cc-23b1945aeb04.png)

Salfny is a classified app which enables users to offer their items for
rent or to rent others’ items and provides them with variety of tools to
run their business. It is a customer-to-customer app model. Organize
your products into categories to help your customers find what they’re
looking for. Present your products at their best with product
descriptions and images. Salfny's flexible and easy-to-use backend will
make your rental process easier.





## Features
There are two types of system users: **User with account** can do the following:
  - He can sell item for rent.
  - search for products by name or by category or by both.
  -  he able to starred products to save them.
  -   
  
---
In addition to what user can do, a **guest user** can do the following:
  - user can view the products of others.
  - will not have permission to sell product.

---
## How To Run
### Front-End
Required to have any release of [Node.js](https://nodejs.org/en/download/) and [Angular](https://angular.io/quick-start) (would be preferrable to have the latest releases).\
Run the following lines in the frontend directory:\
`npm install`\
`ng serve`
### Back-End
Required to have the latest release of [Java](https://www.java.com/en/download/).\
Run `LibrarySystemApplication.java` on any IDE that runs java.
### Database
Create a MySQL workbench connection with the specified details in `application.properties` and run the SQL script in `schema.sql` and `category.sql`.

 
