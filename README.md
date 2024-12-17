# Medify 🏥

Medify is a full-stack application designed to streamline the management of patient-doctor interactions, including appointment scheduling, prescription handling, and more. The project incorporates modern technologies and secure practices to deliver a scalable and user-friendly experience.

---

## Features 🌟

- **Patient & Doctor Management:** Role-based access control to manage user interactions (patients and doctors) securely.
- **Appointment Scheduling**: A conflict-free system that ensures no double booking for doctors and patients.
- **Prescription Tracking**: Simplified management of medical prescriptions via one-to-one appointment associations.
- **JWT-Based Authentication**: Highly secure login system that supports seamless patient-doctor interactions.
- **RESTful API Design**: Scalable and maintainable backend adhering to clean code and SOLID design practices
---

## Tech Stack 🛠️

### Frontend  
- **React.js**: For building an interactive and dynamic user interface.  
- **Tailwind CSS**: To ensure a responsive and modern design.  

### Backend  
- **Spring Boot**: For building scalable and maintainable RESTful APIs.  
- **Java**: The primary programming language for backend development.  
- **PostgreSQL**: Database for managing user, doctor, and appointment data.  
- **JWT**: Secure user authentication and authorization.  

### DevOps  
- **Docker**: For containerizing the application.  
- **Kubernetes**: For orchestrating containers in production.  
- **Jenkins**: CI/CD pipeline for seamless integration and deployment.

---

## Installation & Setup ⚙️

### Prerequisites  
- Java 17+  
- Node.js 18+  
- Docker (optional for containerized deployment)  

### Backend Setup  
1. Clone the repository:  
   ```bash
   git clone https://github.com/yourusername/medify-backend.git
   cd medify-backend
   ```
2. Build the project:  
   ```bash
   ./mvnw clean install
   ```
3. Run the application:  
   ```bash
   ./mvnw spring-boot:run
   ```

### Frontend Setup  
1. Clone the repository:  
   ```bash
   git clone https://github.com/yourusername/medify-frontend.git
   cd medify-frontend
   ```
2. Install dependencies:  
   ```bash
   npm install
   ```
3. Start the development server:  
   ```bash
   npm start
   ```

---

## Usage 🏢

1. **Patients**:  
   - Register and log in to book or cancel appointments.  
2. **Doctors**:  
   - Log in to manage appointments, update statuses, and issue prescriptions.  

---

## Project Highlights 🚀

- Implemented JWT-based authentication for secure role-based access.  
- Built RESTful APIs for modularity and easy integration with frontend.  
- Designed relational database schema with constraints to maintain data integrity (e.g., no overlapping appointments for the same doctor).  

---

## Contributing 🤝

Contributions are welcome! Please follow these steps:  
1. Fork the repository.  
2. Create a new feature branch:  
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes and push the branch:  
   ```bash
   git push origin feature-name
   ```
4. Open a pull request.  

---
