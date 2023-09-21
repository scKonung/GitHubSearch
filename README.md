# GitHub Repositories Search By Username

### 👨‍💻 Description
To use the application, you need to provide the GitHub username as a command-line argument. The application will then retrieve the user's repositories that are not forks and display their details in format:
```json
{
        "name": "${repository_name}",
        "ownerLogin": "${owner_login}",
        "branches": [
            {
                "name": "${branche_name}",
                "commitSha": "${last_commit_sha}"
            }
        ]
}


```

### 💫 API Endpoint
- GET `/get`

### 💬: Response Format
- Header: “Accept: `application/json`”
- Body:
  ```json
  {
  "username":"${username}"
  }
  ```

### ⁉️ Error Handling
The application handles different scenarios as specified in the acceptance criteria:
When user give username, which is not exict, application return:

- If the user does not exist, a `404` response is returned with an appropriate error message.
  ```json
  {
  "status": 404,
    "message": "User with username:${username} not found in GitHub"
  }
  ```
- If the `Accept` header is set to `application/xml`, a `406` response is returned with a message.
   ```json
    {
  "status": 406,
    "message": "Unsupported xml type. Only JSON is supported."
  }
   ```
### 👨‍🏫: Tecnology Stack
- Java 17
- Maven 4.0
- Spring Boot 3.1.2
### ✈️: Getting Started
  1. Clone this repository to your local machine.
  2. Navigate to the project's root directory and execute the following commands in your terminal:
     ```bash
     ./mvnw clean install
     ./mvnw spring-boot:run
     ```
  3. Once the application is successfully started, you can access it by opening your web browser and going to: `http://localhost:8080/get`
