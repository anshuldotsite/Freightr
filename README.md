# Freightr

## Project Description
This is Package Tracking Software for businesses to maintain their shipments.

## Getting Started Guide
### Software Requirements
- IDE (Intellij IDEA)
- JDK Version - Amazon Coretto 20
- MySQL Server

### Running the application
- Open git and use this command to clone the project- git clone https://github.com/anshuldotsite/Freightr.git
- Go to git bash and establish connection with MySQL server(./dbconnect.sh script).
- Open the cloned folder in Intellij Idea.
- Go to the HelloApplication.java file in Intellij and hit run.
- Setup the application by filling in the required details asked in the application set up.
- Now you are good to use the application.

## Screenshots
### Login Page
![image](https://github.com/user-attachments/assets/e9c4e51f-98ae-4869-b22d-2fe44145fb3d)

### Creating a Package
![image](https://github.com/user-attachments/assets/aeb37aa1-d875-4e99-a7cd-444c75195030)

### All packages data
![image](https://github.com/user-attachments/assets/4670a50e-4677-4237-a31c-a177821af305)

### All Customers
![image](https://github.com/user-attachments/assets/e63b94f4-c86a-451e-b065-8328143f779c)

### Tracking Package
![image](https://github.com/user-attachments/assets/b13d7162-5827-43f3-9825-10d33fa17886)

### Updating Package
![image](https://github.com/user-attachments/assets/e1e64873-b03d-4ef1-acfa-2981302bdb75)

## Database Schema
![image](https://github.com/user-attachments/assets/29958b49-55eb-49c7-a93b-ba2b9707ee4c)

### Overview
We have a total of 7 tables in which 6 are related with each other. The main package table has relationships with two other tables, package tracking and customer packages. Whenever we create a package, data get added to these three tables. The customer table has relationship with company details table and customer pakages. The employee login table has no relationship with any other table and is solely used for employee accounts.

## Compatibility
OS- Windows, MacOS, Linux

## Authors
- Anshul Karande 
- Kautuk Prasad
- Kohinoor Singh

## License
MIT

## Acknowledgements
- Oracle Docs
- Lecture notes
- Flaticons for image
