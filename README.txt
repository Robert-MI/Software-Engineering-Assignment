Software Engineering Assignment  
Title: Building a 3-Layered Application: NATS Service Subscriber with PostgreSQL Persistence  
Language: Java 17

This project is a simple 3-layered architecture Java application that subscribes to a NATS service, processes incoming messages, and stores them in a PostgreSQL database.

Architecture Overview:

1. API Layer  
Class: NatsSubscriber  
Responsible for connecting to the public NATS server at 'demo.nats.io', subscribing to the subject 'messages', and passing incoming messages to the service layer.

2. Service Layer  
Class: MessageService  
Validates message content and processes messages before sending them to the data layer.

3. Data Layer  
Class: MessageRepository  
Responsible for creating the 'messages' table (if not already created) and inserting messages into PostgreSQL with a timestamp.

PostgreSQL Configuration:

For Localhost:  
- Port: 5432  
- Username: robert  
- Password: 12345678  
- Database: mydb

For Docker container (named 'assignmenttest'):  
- Port: 5433  
- Username: robert  
- Password: 12345678  
- Database: mydb

Running the Application:

1. Clone the repository:  
'git clone https://github.com/your-username/Software-Engineering-Assignment.git'  
'cd Software-Engineering-Assignment'

2. Start the application using Docker Compose:  
'docker compose up --build'

This command will spin up the PostgreSQL container on port 5433 and run the Java application. It will automatically create the necessary table in the database.

3. Send a test message using the NATS CLI:  
'nats pub -s demo.nats.io messages "Hello World!"'

Expected output in the console:  
'Received message: Hello World!'  
'Valid message received: Hello World!'  
'Message saved to DB.'

Database Table Schema:

The table is created automatically on startup if it does not exist.  
The schema:  
'CREATE TABLE IF NOT EXISTS messages (  
    id SERIAL PRIMARY KEY,  
    content TEXT NOT NULL,  
    received_at TIMESTAMP NOT NULL  
);'

Testing:

Basic tests are provided for both the service and data layers.  

Test coverage includes:  
- Verifying that valid messages are stored in the database  
- Ensuring that empty or null messages are not processed  
- Making sure no exceptions are thrown during the save process

Project Details:

- Java version: 17  
- PostgreSQL database  
- NATS public demo server  
- Docker Compose setup for easy launch  
- Clear 3-layer separation (API, Service, Data)  
- GitHub repository name: Software-Engineering-Assignment
