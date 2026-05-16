# 🏥 Medical Center System

A web-based system for managing a medical center, including patients, doctors, and appointments.

---

## 🚀 Features

* 👨‍⚕️ Manage doctors and their schedules
* 🧑‍🤝‍🧑 Manage patients
* 📅 Book and manage appointments
* 🔐 User authentication (Login & Register)
* 🗂️ Organized database structure

---

## 🛠️ Technologies Used

* Java (JSP & Servlets)
* Maven
* Microsoft SQL Server
* HTML / CSS / JavaScript
* GlassFish Server

---

## 🗄️ Database Setup

### Option 1 (Recommended)

1. Open SQL Server Management Studio (SSMS)
2. Create a new database:
   MedicalCenter
3. Open the file:
   database/medical_center.sql
4. Execute the script

---

### Option 2 (Backup)

1. Open SSMS
2. Right-click on Databases → Restore Database
3. Select the file:
   database/Medical_Center.bak
4. Restore the database

---

## ⚙️ Configuration

Update the database connection in your project:

Example:
jdbc:sqlserver://localhost:1433;databaseName=MedicalCenter

Make sure SQL Server is running.

---

## ▶️ How to Run

1. Open the project in NetBeans
2. Build the project (Maven)
3. Deploy using GlassFish Server
4. Open in browser:
   http://localhost:8080/MedicalCenterSystem

---

## 📁 Project Structure

MedicalCenterSystem/
│── src/
│── database/
│   ├── medical_center.sql
│   └── Medical_Center.bak
│── pom.xml

---

## 👨‍💻 Author

Abdelrahman Zaher

---

## 📌 Notes

* Make sure SQL Server service is running
* Update DB credentials if needed
* Use the SQL file for best compatibility

Documentation:
https://drive.google.com/drive/u/2/folders/1jLBxSanezHjI-WuPO-1uRyOTaC-RMSZh
---
