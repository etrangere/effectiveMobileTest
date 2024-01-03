
## Task Management System Development
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
 
 * PUT &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/user/{userTaskHolder_id}/update_task/{task_id}
 * PUT &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/user/{user_id}/update_user
 * POST &nbsp;&nbsp;&nbsp;/api/v1/user/{userTaskHolder_id}/{task_id}/removeExecutor/{userExecutor_id} 
 * POST &nbsp;&nbsp;&nbsp;/api/v1/user/{userTaskHolder_id}/{task_id}/addExecutor/{userExecutor_id}
 * POST &nbsp;&nbsp;&nbsp;/api/v1/user/{userTaskHolder_id}/create_task
 * POST &nbsp;&nbsp;&nbsp;/api/v1/user/update_task_status/{task_id}/{status_code}
 * POST &nbsp;&nbsp;&nbsp;/api/v1/user/update_task_priority/{task_id}/{priority_code}
 * POST &nbsp;&nbsp;&nbsp;/api/v1/user/create_user
 * GET &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/user/{userTaskHolder_id}/{task_id}/getAll_task_comment_holder_id_task_id  
 * GET &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/user/{userTaskHolder_id}/getAll_user_task_by_status/{status}
 * GET &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/user/{userTaskHolder_id}/getAll_user_task_by_priority/{priority}
 * GET &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/user/{userTaskHolder_id}/getAll_tasks_comments_holder
 * GET &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/user/{userTaskExecutor_id}/getAll_tasks_comments_executor
 * DELETE &nbsp;/api/v1/user/{user_id}/delete_user
 * DELETE &nbsp;/api/v1/user/{userTaskHolder_id}/delete_task/{task_id}
  

For Comment

  * GET &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/comment/{task_id}/getById_comment/{comment_id}  
  * POST &nbsp;&nbsp;&nbsp;/api/v1/comment/{task_id}/create_comment
  * GET &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/comment/{task_id}/getAll_comments
  * DELETE &nbsp;/api/v1/comment/{task_id}/delete_comment/{comment_id}
  * PUT &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/comment/{task_id}/update_comment/{comment_id}
    
  </pre>