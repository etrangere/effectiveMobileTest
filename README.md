ENDPOINTS
 <pre> 
For Users
 
  * GET &nbsp;&nbsp;&nbsp;&nbsp;/user/getAll_users
  * GET &nbsp;&nbsp;&nbsp;&nbsp;/user/{user_id}/getById_user
  * POST &nbsp;&nbsp;&nbsp;/user/{user_id}/create_user
  * DELETE &nbsp;/user/{user_id}/delete_user
  * PUT &nbsp;&nbsp;&nbsp;&nbsp;/user/{user_id}/update_user
  * POST &nbsp;&nbsp;&nbsp;/user/{userTaskHolder_id}/create_task
  * GET &nbsp;&nbsp;&nbsp;&nbsp;/user/{userTaskHolder_id}/getAll_tasks
  * DELETE &nbsp;/user/{userTaskHolder_id}/delete_task/{task_id}
  * PUT &nbsp;&nbsp;&nbsp;&nbsp;/user/{userTaskHolder_id}/update_task/{task_id}
  * GET &nbsp;&nbsp;&nbsp;&nbsp;/user/{userTaskHolder_id}/getAll_tasks_comments
  * GET &nbsp;&nbsp;&nbsp;&nbsp;/user/{task_id}/getAll_tasks_comments
 
For Task
 
  * GET &nbsp;&nbsp;&nbsp;/task/{userExecutor_id}/getAll_tasks
  * POST &nbsp;&nbsp;/task/{userExecutor_id}/update_task_status/{task_id}

For Comment
    
  * POST &nbsp;&nbsp;&nbsp;/comment/{task_id}/create_comment
  * GET &nbsp;&nbsp;&nbsp;&nbsp;/comment/{task_id}/getAll_comments
  * DELETE &nbsp;/comment/{task_id}/delete_comment
  * PUT &nbsp;&nbsp;&nbsp;&nbsp;/comment/{task_id}/update_comment
    
  </pre>