# Resume Automation System

A powerful resume automation web application built using Spring Boot that simplifies the job-seeking and recruitment process.

## 🔍 Overview

This application provides a two-user system:

- **Job Seekers**: Can upload, update, and delete their resumes without the need for registration or login.
- **Recruiters**: Can log in with credentials to:
    - Automatically send emails to selected job seekers.
    - Access the top-k ranked job seekers based on ATS (Applicant Tracking System) scores.

The ATS score is generated using the Ollama LLaMA 3.2 1B model.

---

## 🚀 Features

- Resume upload, update, and delete for job seekers.
- Secure login for recruiters via Spring Security.
- Automated mailing to shortlisted candidates.
- ATS score generation for all resumes.
- Recruiter dashboard to filter top-K candidates.

---

## 🛠️ Tech Stack

- **Backend**: Spring Boot, Spring MVC, Spring Security
- **Database**: PostgreSQL, JPA (Hibernate)
- **Email Service**: Java Mail Sender
- **Model Integration**: Ollama `llama3.2:1b` for ATS scoring
- **Lombok**: For reducing boilerplate code
- **Others**: PostgreSQL JDBC Driver, Spring Boot Starter Web

---

## 🧠 Prerequisites

- Java 17 or higher
- Maven
- PostgreSQL
- Ollama with `llama3.2:1b` model

---

## ⚙️ Running the Project

1. Clone the repository.
2. Set up your database and email credentials in the application's property files.
3. Start the Ollama `llama3.2:1b` model locally.
4. Run the Spring Boot application using your preferred IDE or terminal.

---

## 🔐 Authentication

- **Job Seekers**: No login required.
- **Recruiters**: Must log in with a username and password.

---

## 📫 Email Automation

Recruiters can send selection or rejection emails automatically to job seekers based on filters such as top-K ATS score.

---

## 📁 Project Structure

```plaintext
├── src
│   ├── main
│   │   ├── java
│   │   │   └── org.example.hiring
│   │   │       ├── controller
│   │   │       ├── DAO
│   │   │       ├── model
│   │   │       └── service
│   │   └── resources
│   │       ├── application.properties
│   │       ├── static
│   │       └── templates
│   └── test
```

---

## 👨‍💻 Author

**Tornov Dutta**  
*BCA Student | Java & Spring Boot Developer*  
[Email](mailto:tornovdutta@gmail.com) | [LinkedIn](https://www.linkedin.com/in/tornov-dutta/)

---

## 📜 License

This project is licensed under the **MIT License**. See the `LICENSE` file for details.
