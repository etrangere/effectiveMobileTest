ENDPOINTS
 <pre> 
For Users
 
  * GET &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/user/getAll_users
  * GET &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/user/{user_id}/getById_user
  * POST &nbsp;&nbsp;&nbsp;/api/v1/user/create_user
  * DELETE &nbsp;/api/v1/user/{user_id}/delete_user
  * PUT &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/user/{user_id}/update_user
  * POST &nbsp;&nbsp;&nbsp;/api/v1/user/{userTaskHolder_id}/create_task
  * GET &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/user/{userTaskHolder_id}/getAll_tasks
  * DELETE &nbsp;/api/v1/user/{userTaskHolder_id}/delete_task/{task_id}
  * PUT &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/user/{userTaskHolder_id}/update_task/{task_id}
  * GET &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/user/{userTaskHolder_id}/getAll_tasks_comments
  * GET &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/user/{userTaskHolder_id}/{task_id}/getAll_tasks_comments
  * POST &nbsp;&nbsp;&nbsp;/api/v1/user/{userTaskHolder_id}/{task_id}/addExecutor/{userExecutor_id}
  * POST &nbsp;&nbsp;&nbsp;/api/v1/user/{userTaskHolder_id}/{task_id}/removeExecutor/{userExecutor_id} 
  * GET &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/user/{userExecutor_id}/getAll_tasks
  * POST &nbsp;&nbsp;&nbsp;/api/v1/user/{userExecutor_id}/update_task_status/{task_id}

For Comment

  * GET &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/comment/{task_id}/getById_comment/{comment_id}  
  * POST &nbsp;&nbsp;&nbsp;/api/v1/comment/{task_id}/create_comment
  * GET &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/comment/{task_id}/getAll_comments
  * DELETE &nbsp;/api/v1/comment/{task_id}/delete_comment/{comment_id}
  * PUT &nbsp;&nbsp;&nbsp;&nbsp;/api/v1/comment/{task_id}/update_comment/{comment_id}
    
  </pre>