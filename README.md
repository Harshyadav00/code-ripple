# API Documentation for CodeRipple

## 1. Problem API Endpoints

### **a. Create a Problem**
- **Endpoint**: `POST /api/problems`
- **Desccription**: Creates a new problem with details like description, difficulty, constraints, etc.

### **b. Get All Problems**
- **Endpoint**: `GET /api/problems`
- **Description**: Fetches all problems, optionally with pagination and filtering based on difficulty, tags, etc.

### **c. Get a Single Problem**
- **Endpoint**: `GET /api/problems/{problemId}`
- **Description**: Fetches details of a single problem by its problemId

### **d. Update a Problem**
- **Endpoint**: `PUT /api/problems/{problemId)`
- **Description**: Updates the details of an existing problem.

### **e. Delete a Problem**
- **Endpoint**: `DELETE /api/problems/{problemId}`
- **Description**: Deletes a problem by its problemId.

## 2. Submission API Endpoints

### **a. Submit Code**
- **Endpoint**: `POST /api/v1/submission`
- **Description**: Submits a user's solution to a problem.

### **b. Get All Submission for a user**
- **Endpoint**: `GET /api/v1/users/{userId}/submissions`
- **Description**: Fetches all submissions made by a specific user.
  
### **c. Get Submission Details**
- **Endpoint**: `GET /api/v1/submissions/{submissionId}`
- **Description``: Fetches the details of a specific submission, including execution time, memory used, and status.

### **d. Get All Submissions for a Problem**
- **Endpoint**: `GET /api/problems/{problemId}/submissions`
- **Description**: Fetches all submissions made for a specific problem.
