
### Java 17, Maven 3.1.6, JUnit 5.8.1, Mockito 3.12.4, OpenAPI 3.x, Swagger 2, Docker, Docker Compose, MySql

## Task Management System (Microservice)
### Task Description:
You are required to develop a simple Task Management System using Java. The system should provide functionality for creating, editing, deleting, and viewing tasks. Each task should have a title, description, status (e.g., "pending," "in progress," "completed"), and priority (e.g., "high," "medium," "low"), as well as the author and assignee. Only the API implementation is required.

#### Requirements:

    1.The service should support user authentication and authorization via email and password.

    2.API access should be authenticated using JWT tokens.

    3.Users can manage their tasks: create new ones, edit existing ones, view and delete them, change the status, and assign task performers.

    4.Users can view tasks of other users, and task performers can change the status of their tasks.

    5.Comments can be added to tasks.

    6.The API should allow fetching tasks of a specific author or performer, as well as all comments related to them. Filtering and pagination of the output should be supported.

    7.The service should handle errors correctly, return clear messages, and validate incoming data.

    8.The service should be well-documented. The API should be described using OpenAPI and Swagger. Swagger UI should be set up in the service.

    9.Provide a README with instructions for local project setup. Use Docker Compose to set up the development environment.

    10.Write some basic tests to verify the core functionality of your system.
    
    11.Use Java 17+, Spring, and Spring Boot for system implementation. PostgreSQL or MySQL can be used as the database. Use Spring Security for authentication and authorization. Additional tools can be used if necessary (e.g., caching).

### Realisation
 <pre> 
ENDPOINTS

For Users
 
 
* PUT `/api/v1/user/{userTaskHolder_id}/update_task/{task_id}`
  - Update a specific task for a user task holder.

* PUT `/api/v1/user/{user_id}/update_user`
  - Update information for a user.

* POST `/api/v1/user/{userTaskHolder_id}/{task_id}/removeExecutor/{userExecutor_id}`
  - Remove an executor from a specific task within a user task holder.

* POST `/api/v1/user/{userTaskHolder_id}/{task_id}/addExecutor/{userExecutor_id}`
  - Add an executor to a specific task within a user task holder.

* POST `/api/v1/user/{userTaskHolder_id}/create_task`
  - Create a new task within a user task holder. Remove "string" from the "tasks":["string"] leaving it as "tasks": [] in the JSON Swagger. Fill in the remaining task fields.

* POST `/api/v1/user/update_task_status/{task_id}/{status_code}`
  - Update the status of a specific task.

* POST `/api/v1/user/update_task_priority/{task_id}/{priority_code}`
  - Update the priority of a specific task.

* POST `/api/v1/user/create_user`
  - Create a new user.The "executor" parameter should be set to 'true' for a task executor or 'false' for a task holder. Remove "string" from the "users":["string"] leaving it as "users": [] in the JSON Swagger. Fill in the remaining user fields.

* GET `/api/v1/user/{userTaskHolder_id}/{task_id}/getAll_task_comment_holder_id_task_id`
  - Retrieve all comments for a specific task within a user task holder.

* GET `/api/v1/user/{userTaskHolder_id}/getAll_user_task_by_status/{status}`
  - Retrieve all user tasks for a specific status within a user task holder.

* GET `/api/v1/user/{userTaskHolder_id}/getAll_user_task_by_priority/{priority}`
  - Retrieve all user tasks for a specific priority within a user task holder.

* GET `/api/v1/user/{userTaskHolder_id}/getAll_tasks_comments_holder`
  - Retrieve all comments for all tasks within a user task holder.

* GET `/api/v1/user/{userTaskExecutor_id}/getAll_tasks_comments_executor`
  - Retrieve all comments for all tasks assigned to a specific executor.

* DELETE `/api/v1/user/{user_id}/delete_user`
  - Delete a user.

* DELETE `/api/v1/user/{userTaskHolder_id}/delete_task/{task_id}`
  - Delete a specific task within a user task holder.


  

For Comment

* GET `/api/v1/comment/{task_id}/getById_comment/{comment_id}`
  - Get a specific comment by its ID for a given task.

* POST `/api/v1/comment/{task_id}/create_comment`
  - Create a new comment for a specific task.

* GET `/api/v1/comment/{task_id}/getAll_comments`
  - Retrieve all comments for a specific task.

* DELETE `/api/v1/comment/{task_id}/delete_comment/{comment_id}`
  - Delete a specific comment for a given task.

* PUT `/api/v1/comment/{task_id}/update_comment/{comment_id}`
  - Update a specific comment by its ID for a given task.
     </pre>

**Prepare and Run Project in Local Environment**

**Requirements:**
- Windows, Linux, or Mac with Docker and Docker Compose installed.

**Instructions:**

1. **Copy and Place Project Files:**
   - Copy the `task_management` folder and its contents to the root directory of Docker.
     ```bash
     cp -r task_management /path/to/docker/root
     ```

2. **Set Permissions:**
   - Give read and execute permissions to the `task_management` folder:
     ```bash
     chmod +rx /path/to/docker/root/task_management
     ```

3. **Navigate to `task_management` Directory:**
   - Change to the `task_management` directory:
     ```bash
     cd /path/to/docker/root/task_management
     ```

4. **Run Docker Compose:**
   - Execute the following command to run the project:
     ```bash
     docker-compose up
     ```
5. **Use the Swagger UI interface:**
   - Swagger UI will be accessible at the following address: http://localhost:8085/swagger-ui/index.html

6. **Stop and Remove:**
   - To stop and remove containers, networks, and volumes created by `docker-compose`, use the following command:
     ```bash
     docker-compose down -v --remove-orphans
     ```

 