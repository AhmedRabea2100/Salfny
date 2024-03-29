<!-- markdownlint-configure-file {
  "MD013": {
    "code_blocks": false,
    "tables": false
  },
  "MD033": false,
  "MD041": false
} -->

<div align="center">
  
# Salfny 



![logo](https://user-images.githubusercontent.com/77025553/209586849-10f38728-ca0b-44da-83cc-23b1945aeb04.png)


Salfny is a classified app which enables users to offer their items for
rent or to rent others’ items and provides them with variety of tools to
run their business. It is a customer-to-customer app model. Organize
your products into categories to help your customers find what they’re
looking for. Present your products at their best with product
descriptions and images. Salfny's flexible and easy-to-use backend will
make your rental process easier.

Implementation using <a href="https://angular.io" target="_blank" rel="noreferrer"> <img src="https://angular.io/assets/images/logos/angular/angular.svg" alt="angular" width="40" height="40"/> <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> <a href="https://www.mysql.com/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original-wordmark.svg" alt="mysql" width="40" height="40"/>

# Demo 
---
https://user-images.githubusercontent.com/77179015/224142152-940ff826-e731-47a9-920e-b64cb8cd3ba3.mp4

</div>


# How To Run
  
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


  
---  
# Features
There are two types of system users

**User with account** can do the following:
  -The user can rent items.
  - search for products by name, category, or both.
  - star products to save them.
  - The user can upload a profile picture and edit his information.
  - user able to see his favorite and uploaded posts.
  - The user can delete his uploaded post. 
  - In signing up, the user enters his preferred categories.
  
---
A **guest user** can do the following:
  - The user can view the products of others.
  - will not have permission to rent his product.

