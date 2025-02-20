# Project Service API Documentation

## Base URL

`/api/projects`

## Authentication

No authentication details provided.

## Project Endpoints

### 1. Create a New Project

**Endpoint:** `POST /api/projects/new`

**Request Body:** (JSON)
```json
{
    "projectId": 1,
    "projectName": "New Website Development",
    "projectDescription": "Develop a fully responsive website with an e-commerce platform.",
    "projectPriority": "MEDIUM",
    "projectStatus": "NOT_STARTED",
    "projectManagerId": 101,
    "teamsInvolvedId": [1, 2, 3],
    "objectiveId": [201, 202, 203],
    "projectCreatedAt": "2025-02-11",
    "projectDueDate": "2025-02-28",
}

```

**Response:**
- **200 OK**: Returns the created project object
- **400 Bad Request**: Invalid input

**Sample Request:**
```json
POST /api/projects/new
{
    "projectId": 1,
    "projectName": "New Website Development",
    "projectDescription": "Develop a fully responsive website with an e-commerce platform.",
    "projectPriority": "MEDIUM",
    "projectStatus": "NOT_STARTED",
    "projectManagerId": 101,
    "teamsInvolvedId": [1, 2, 3],
    "objectiveId": [201, 202, 203],
    "projectCreatedAt": "2025-02-11",
    "projectDueDate": "2025-02-28",
}
```

### 2. Get All Projects

**Endpoint:** `GET /api/projects`

**Response:**
- **200 OK**: Returns a list of all projects

**Sample Response:**
```json
[
  {
    "projectId": 1,
    "projectName": "New Website Development",
    "projectDescription": "Develop a fully responsive website with an e-commerce platform.",
    "projectPriority": "MEDIUM",
    "projectStatus": "NOT_STARTED",
    "projectManagerId": 101,
    "teamsInvolvedId": [1, 2, 3],
    "objectiveId": [201, 202, 203],
    "projectCreatedAt": "2025-02-11",
    "projectDueDate": "2025-02-28",
  }
]
```

### 3. Get a Project by project ID

**Endpoint:** `GET /api/projects/{id}` 

**Path Parameters:** 
- **id** (long, required): ID of the project.
  
**Response:**
- **200 OK**: Returns the project details
- **404 Not Found**: Project not found

**Sample Request:**
```json
GET /api/projects/1
```

### 4. Update a Project by projectId

**Endpoint:** `PUT /api/projects/{id}`

**Path Parameters:** 
- **id** (long, required): ID of the project.

**Request Body:** (JSON)
```json
{
    "projectId": 1,
    "projectName": "New Website Development",
    "projectDescription": "Develop a fully responsive website with an e-commerce platform.",
    "projectPriority": "MEDIUM",
    "projectStatus": "NOT_STARTED",
    "projectManagerId": 101,
    "teamsInvolvedId": [1, 2, 3],
    "objectiveId": [201, 202, 203],
    "projectCreatedAt": "2025-02-11",
    "projectDueDate": "2025-02-28",
}
```

**Response:**
- **200 OK**: Returns the updated project
- **404 Not Found**: Project not found

### 5. Delete a Project by project Id

**Endpoint:** `DELETE /api/projects/{id}`

**Path Parameters:** 
- **id** (long, required): ID of the project.

**Response:**
- **200 OK**: Project deleted
- **404 Not Found**: Project not found

### 6. Get Completed Projects Count

**Endpoint:** `GET /api/projects/dashboard/completed`

**Response:**
- **200 OK**: Returns the count of completed projects

### 7. Get Project Count by Status

**Endpoint:** `GET /api/projects/dashboard/status/{status}`

**Path Parameters:** 
- **status** (enum, required): Status of projects to count (ACTIVE, INACTIVE, COMPLETED).

**Response:**
- **200 OK**: Returns the count of projects with the specified status
- **400 Bad Request**: Invalid status parameter

### 8. Get Project Count by Active Status

**Endpoint:** `GET /api/projects/dashboard/active/{isActive}`

**Path Parameters:** 
- **isActive** (boolean, required): isActive of project to see project active(true, false).

**Response:**
- **200 OK**: Returns the count of active/inactive projects

### 9. Search Projects by attribute name

**Endpoint:** `GET /api/projects/search`

**Query Parameters:**
- `projectName` (optional, string)
- `projectStatus` (optional, enum)
- `isActive` (optional, boolean)
- `projectManager` (optional, long)

**Response:**
- **200 OK**: Returns a filtered list of projects

### 10. Get Objectives of project by Project ID

**Endpoint:** `GET /api/projects/objective/{projectId}`

**Path Parameters:** 
- **projectId** (long, required): projectId is the Id project.

**Response:**
- **200 OK**: Returns a list of objectives for the project

### 11. Get Active Project Count from Given IDs

**Endpoint:** `POST /api/projects/active/count`

**Request Body:** (JSON)
```json
[1, 2, 3, 4]
```

**Response:**
- **200 OK**: Returns the count of active projects from the provided IDs

-----------------------------------------------------------------------------------------------------------------------------

## Objective Endpoints

## Base URL

`/api/objective`


### 12. Create a New Objective

**Endpoint:** `POST /api/objective`

**Request Body:** (JSON)
```json
{
  "objectiveId": 1
  "objectiveName": "Launch New Mobile App",
  "mappedProject": 4,
  "assignedToTeam": [107, 108],
  "keyResultIds": [307, 308],
  "objectiveStartDate": "2025-09-10",
  "objectiveDueDate": "2025-09-10",
  "objectiveStatus": "AT_RISK",
  "objectiveIsActive": true
}
```

**Response:**
- **201 Created**: Returns the created objective

### 13. Get All Objectives

**Endpoint:** `GET /api/objective`

**Response:**
- **200 OK**: Returns a list of all objectives with associated Key Results

### 14. Get an Objective by ID

**Endpoint:** `GET /api/objective/{id}`

**Path Parameters:** 
- **id** (long, required): id is the Id of objective.

**Response:**
- **200 OK**: Returns the objective details with associated key Results 
- **404 Not Found**: Objective not found

### 15. Update an Objective

**Endpoint:** `PUT /api/objective/{id}`

**Path Parameters:** 
- **id** (long, required): ID of the objective.

**Request Body:** (JSON)
```json
{
  "objectiveId": 1
  "objectiveName": "Launch New Mobile App",
  "mappedProject": 4,
  "assignedToTeam": [107, 108],
  "keyResultIds": [307, 308],
  "objectiveStartDate": "2025-09-10",
  "objectiveDueDate": "2025-09-10",
  "objectiveStatus": "AT_RISK",
  "objectiveIsActive": true
}
```

**Response:**
- **200 OK**: Returns the updated objective

### 16. Delete an Objective

**Endpoint:** `DELETE /api/objective/{id}`

**Path Parameters:** 
- **id** (long, required): ID of the objective.

**Response:**
- **204 No Content**: Objective deleted successfully

### 17. Get all objectives associated with a specific project ID.

**Endpoint:** `GET /project/{projectId}`

**Path Parameters:** 
- **projectId** (long, required): projectId of the project.

**Response:**
- **200 OK:** return List of objectives associated with project.

### 18. progress of a project based on its objectives.

**Endpoint:** `GET /project/progress/{projectId}`

**Path Parameters:** 
- **projectId** (long, required): projectId of the project.

**Response:**
- **200 OK:** return progress of project.

### 19. get all objectives and active objectives based on projectIds list.

**Endpoint:** `POST /by-projectIds`

**Request Body:** (JSON)
```json 
[1,2,3]
````
**Response:**
- **200 OK:** return map of list activeObjectives and allObjectives.

### 20. get all objectives  based on its projectsIds.

**Endpoint:** `POST /all/by-projects`

**Request Body:** (JSON)
```json 
[1,2,3]
````
**Response:**
- **200 OK:** return list of objectives.

----------------------------------------------------------------------------------------------------------------------------

## Key Result Endpoints

## Base URL

`/api/keyresults`


### 21. Create a New Key Result

**Endpoint:** `POST /api/key-result`

**Request Body:** (JSON)
```json
{
  "keyResultName": "Launch New Feature",
  "keyResultOwnerId": 127,
  "associatedObjectiveId": 2,
  "currentVal": 0,
  "TargetVal": 100,
  "keyResultPriority": "MEDIUM",
  "keyResultAssociatedTasksId": [109, 110],
  "keyResultDueDate": "2025-11-01",
  "teamId": 205
}

```

**Response:**
- **201 Created**: Returns the created key result

### 18. Get All Key Results

**Endpoint:** `GET /api/key-result`

**Response:**
- **200 OK**: Returns a list of all key results

### 22. Get a Key Result by ID

**Endpoint:** `GET /api/key-result/{id}`

**Path Parameters:** 
- **id** (long, required): id of the key Result.

**Response:**
- **200 OK**: Returns the key result details
- **404 Not Found**: Key result not found

### 23. Update a Key Result

**Endpoint:** `PUT /api/key-result/{id}`

**Path Parameters:** 
- **id** (long, required): id of the key Result.

**Request Body:** (JSON)
```json
{
  "keyResultName": "Launch New Feature",
  "keyResultOwnerId": 127,
  "associatedObjectiveId": 2,
  "currentVal": 0,
  "TargetVal": 100,
  "keyResultPriority": "MEDIUM",
  "keyResultAssociatedTasksId": [109, 110],
  "keyResultDueDate": "2025-11-01",
  "teamId": 205
}
```

**Response:**
- **200 OK**: Returns the updated key result

### 24. Delete a Key Result

**Endpoint:** `DELETE /api/key-result/{id}`

**Path Parameters:** 
- **id** (long, required): id of the key Result.

**Response:**
- **204 No Content**: Key result deleted successfully

### 25. Get all Key Results associated with a specific Objective ID

**Endpoint:** `GET /objective/{objectiveId}`

**Path Parameters:** 
- **objectiveId** (long, required): objectiveId of the objective.

**Response:**
- **200 OK**: List of Key results.

### 26. Get all Key Results associated with a specific Objective ID

**Endpoint:** `POST /by-objectives`

**Request Body:**
```json
[1,2,3]
```
**Response:**
- **200 OK**: List of Key results.

-------------------------------------------------------------------------------------------------------------------------------------------

# Task Service API Documentation

## Base URL
```
/api/tasks
```

## Endpoints

### 27. Create a New Task
Creates a new task in the system.

**Endpoint:** `POST /api/tasks`

**Request Body:**
```json
{
  "taskName": "Complete Sprint Planning",
  "taskOwnerId": 101,
  "associatedObjectiveId": 5,
  "associatedKeyResultId": 12,
  "taskStatus": "IN_PROGRESS",
  "taskPriority": "HIGH",
  "taskDueDate": "2025-12-01",
  "assignedUserId": 204
}
```

**Response:**
- **201 Created**: Returns the created task

### 28. Get All Tasks
Retrieves all tasks from the system.

**Endpoint:** `GET /api/tasks`

**Response:**
- **200 OK**: Returns a list of all tasks

### 29. Get a Task by ID
Retrieves a specific task by its ID.

**Endpoint:** `GET /api/tasks/{id}`

**Path Parameters:**
- `id` (long, required): ID of the task

**Response:**
- **200 OK**: Returns the task details
- **404 Not Found**: Task not found

### 30. Update a Task
Updates an existing task.

**Endpoint:** `PUT /api/tasks/{id}`

**Path Parameters:**
- `id` (long, required): ID of the task

**Request Body:**
```json
{
  "taskName": "Complete Sprint Planning",
  "taskOwnerId": 101,
  "associatedObjectiveId": 5,
  "associatedKeyResultId": 12,
  "taskStatus": "IN_PROGRESS",
  "taskPriority": "HIGH",
  "taskDueDate": "2025-12-01",
  "assignedUserId": 204
}
```

**Response:**
- **200 OK**: Returns the updated task
- **404 Not Found**: Task not found

### 31. Delete a Task
Deletes a specific task.

**Endpoint:** `DELETE /api/tasks/{id}`

**Path Parameters:**
- `id` (long, required): ID of the task

**Response:**
- **204 No Content**: Task successfully deleted
- **404 Not Found**: Task not found

### 32. Get All Tasks by Objective ID
Retrieves all tasks associated with a specific objective.

**Endpoint:** `GET /api/tasks/objective/{objectiveId}`

**Path Parameters:**
- `objectiveId` (long, required): ID of the associated objective

**Response:**
- **200 OK**: Returns a list of tasks for the specified objective

### 33. Get All Tasks by Key Result ID
Retrieves all tasks associated with a specific key result.

**Endpoint:** `GET /api/tasks/keyresult/{keyresultId}`

**Path Parameters:**
- `keyresultId` (long, required): ID of the associated key result

**Response:**
- **200 OK**: Returns a list of tasks for the specified key result

### 34. Get All Tasks by User ID
Retrieves all tasks assigned to a specific user.

**Endpoint:** `GET /api/tasks/users/{userId}`

**Path Parameters:**
- `userId` (long, required): ID of the user

**Response:**
- **200 OK**: Returns a list of tasks assigned to the user

### 35. Get All Active Tasks by User ID
Retrieves all active tasks assigned to a specific user.

**Endpoint:** `GET /api/tasks/active-task/users/{userId}`

**Path Parameters:**
- `userId` (long, required): ID of the user

**Response:**
- **200 OK**: Returns a list of active tasks assigned to the user

### 36. Approve a Task
Approves a specific task.

**Endpoint:** `PUT /api/tasks/{taskId}/approve`

**Path Parameters:**
- `taskId` (long, required): ID of the task

**Response:**
- **200 OK**: Task approved successfully
- **404 Not Found**: Task not found


## Notes

- `ProjectStatus`, `ObjectiveStatus`, and `KeyResultStatus` are enums (`ACTIVE`, `COMPLETED`, etc.)
- Ensure all project, objective, and key result-related data is validated before sending requests
- All endpoints return JSON responses
- Dates should be formatted in ISO 8601 format
- IDs are of type `long`
