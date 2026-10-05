# InsightEcho 🎯

InsightEcho is an AI-powered customer service analytics application that analyzes customer call transcripts and provides useful insights for improving customer support.

## 🚀 Features

* Upload customer call transcripts
* Analyze customer sentiment and tone
* Identify emotional cues
* Generate customer service ratings
* Provide coaching tips and recommendations
* Detect escalation flags
* Store transcript and analysis data
* REST APIs for transcript management

## 🛠️ Tech Stack

### Backend

* Java
* Spring Boot
* Spring AI
* Spring Data JPA
* Hibernate
* Maven
* REST APIs
* H2 Database
* Llama 3.3

### Frontend

* Angular

## 🏗️ Project Flow

```text
Customer Call Transcript
          ↓
     Upload Transcript
          ↓
     Spring Boot API
          ↓
       Spring AI
          ↓
        LLM Analysis
          ↓
 ┌─────────────────────┐
 │ Sentiment            │
 │ Tone                 │
 │ Emotional Cues       │
 │ Rating               │
 │ Coaching Tips        │
 │ Escalation Flags     │
 └─────────────────────┘
          ↓
       Database
```

## 🔗 API Endpoints

### Upload Transcript

```http
POST /api/transcripts/upload
```

Uploads a `.txt` transcript for analysis.

### Get All Transcripts

```http
GET /api/transcripts
```

Returns all stored transcripts.

### Get Transcript by ID

```http
GET /api/transcripts/{id}
```

Returns a specific transcript and its analysis.

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/simransaka/InsightEcho-Backend.git
cd InsightEcho-Backend
```

### 2. Configure the application

Add the required AI/API configuration in your `application.properties` or environment variables.

> Do not commit API keys or other secrets to GitHub.

### 3. Run the backend

Using Maven:

```bash
mvn spring-boot:run
```

The backend will run on:

```text
http://localhost:8080
```

## 📂 Project Structure

```text
src
└── main
    ├── java
    │   └── com.CustomerService.InsightEcho
    │       ├── controller
    │       ├── service
    │       ├── repository
    │       ├── model
    │       └── ...
    │
    └── resources
        └── application.properties
```

## 🎯 Purpose

InsightEcho helps customer service teams understand conversations more effectively by automatically analyzing call transcripts and generating actionable feedback for customer service improvement.

## 👩‍💻 Author

**Simran Saka**

GitHub: [simransaka](https://github.com/simransaka?utm_source=chatgpt.com)
