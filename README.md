Hospital Management System (Java + MySQL)

A simple console-based Hospital Management System built using Java, MySQL, JDBC, and OOP concepts.
This project allows hospital staff to manage patients, doctors, and appointments efficiently.

🚀 Features
👨‍⚕️ Doctor Management

View list of doctors

Check doctor availability before booking

Stores doctor specialization and ID

🧑‍🦽 Patient Management

Add new patients

View patient details

Search patient by ID

📅 Appointment Management

Book appointment between patient and doctor

Validates doctor availability using date + doctor ID

Prevents duplicate appointments for same doctor/date

Stores full appointment record in MySQL

🛠️ Technologies Used
Technology	Purpose
Java	Business logic & console UI
MySQL	Database storage
JDBC	Database connection in Java
OOP Concepts	Classes for Patient, Doctor, Appointments
Scanner (Java)	User input handling
🗂️ Project Structure
HospitalManagementSystem/
│
├── HospitalManagementSystem.java   # Main file
├── Patient.java                    # Patient module
├── Doctor.java                     # Doctor module
├── appointments table              # MySQL table
└── patients/doctors tables         # MySQL schema
