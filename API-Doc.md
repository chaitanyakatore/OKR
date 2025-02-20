Let me help you format this API documentation in a clearer markdown structure.

# API Documentation for Project Management

## Base URL
`/api/projects`

## Authentication
No authentication details provided.

## Endpoints

### 1. Create a New Project
**Endpoint:** `POST /api/projects/new`

**Request Body:** (JSON)
```json
{
  "projectName": "string",
  "projectStatus": "enum(ProjectStatus)",
  "isActive": "boolean",
  "projectManagerId": "long"
}
```

**Response:**
- **200 OK**: Returns the created project object
- **400 Bad Request**: Invalid input

**Sample Request:**
```json
POST /api/projects/new
{
  "projectName": "New Project",
  "projectStatus": "ACTIVE",
  "isActive": true,
  "projectManagerId": 123
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
    "id": 1,
    "projectName": "New Project",
    "projectStatus": "ACTIVE",
    "isActive": true,
    "projectManagerId": 123
  }
]
```

### 3. Get a Project by ID
**Endpoint:** `GET /api/projects/{id}`

**Response:**
- **200 OK**: Returns the project details
- **404 Not Found**: Project not found

**Sample Request:**
```json
GET /api/projects/1
```

### 4. Update a Project
**Endpoint:** `PUT /api/projects/{id}`

**Request Body:** (JSON)
```json
{
  "projectName": "Updated Project",
  "projectStatus": "COMPLETED",
  "isActive": false
}
```

**Response:**
- **200 OK**: Returns the updated project
- **404 Not Found**: Project not found

### 5. Delete a Project
**Endpoint:** `DELETE /api/projects/{id}`

**Response:**
- **200 OK**: Project deleted
- **404 Not Found**: Project not found

### 6. Get Completed Projects Count
**Endpoint:** `GET /api/projects/dashboard/completed`

**Response:**
- **200 OK**: Returns the count of completed projects

### 7. Get Project Count by Status
**Endpoint:** `GET /api/projects/dashboard/status/{status}`

**Response:**
- **200 OK**: Returns the count of projects with the specified status
- **400 Bad Request**: Invalid status parameter

### 8. Get Project Count by Active Status
**Endpoint:** `GET /api/projects/dashboard/active/{isActive}`

**Response:**
- **200 OK**: Returns the count of active/inactive projects

### 9. Search Projects
**Endpoint:** `GET /api/projects/search`

**Query Parameters:**
- `projectName` (optional, string)
- `projectStatus` (optional, enum)
- `isActive` (optional, boolean)
- `projectManager` (optional, long)

**Response:**
- **200 OK**: Returns a filtered list of projects

### 10. Get Objectives by Project ID
**Endpoint:** `GET /api/projects/objective/{projectId}`

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

## Error Handling
- **400 Bad Request**: Invalid input
- **404 Not Found**: Resource not found
- **500 Internal Server Error**: Unexpected errors

## Notes
- `ProjectStatus` is an enum (`ACTIVE`, `COMPLETED`, etc.)
- All project-related data should be validated before sending requests
