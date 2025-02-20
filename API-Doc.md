# Project Management API Documentation

## Table of Contents
- [Base URL](#base-url)
- [Authentication](#authentication)
- [Project Endpoints](#project-endpoints)
- [Objective Endpoints](#objective-endpoints)
- [Key Result Endpoints](#key-result-endpoints)
- [Error Handling](#error-handling)
- [Notes](#notes)

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

## Objective Endpoints

### 12. Create a New Objective

**Endpoint:** `POST /api/objective`

**Request Body:** (JSON)
```json
{
  "objectiveName": "string",
  "projectId": "long",
  "status": "enum(ObjectiveStatus)"
}
```

**Response:**
- **201 Created**: Returns the created objective

### 13. Get All Objectives

**Endpoint:** `GET /api/objective`

**Response:**
- **200 OK**: Returns a list of all objectives

### 14. Get an Objective by ID

**Endpoint:** `GET /api/objective/{id}`

**Response:**
- **200 OK**: Returns the objective details
- **404 Not Found**: Objective not found

### 15. Update an Objective

**Endpoint:** `PUT /api/objective/{id}`

**Request Body:** (JSON)
```json
{
  "objectiveName": "Updated Objective",
  "status": "COMPLETED"
}
```

**Response:**
- **200 OK**: Returns the updated objective

### 16. Delete an Objective

**Endpoint:** `DELETE /api/objective/{id}`

**Response:**
- **204 No Content**: Objective deleted successfully

## Key Result Endpoints

### 17. Create a New Key Result

**Endpoint:** `POST /api/key-result`

**Request Body:** (JSON)
```json
{
  "keyResultName": "string",
  "objectiveId": "long",
  "status": "enum(KeyResultStatus)"
}
```

**Response:**
- **201 Created**: Returns the created key result

### 18. Get All Key Results

**Endpoint:** `GET /api/key-result`

**Response:**
- **200 OK**: Returns a list of all key results

### 19. Get a Key Result by ID

**Endpoint:** `GET /api/key-result/{id}`

**Response:**
- **200 OK**: Returns the key result details
- **404 Not Found**: Key result not found

### 20. Update a Key Result

**Endpoint:** `PUT /api/key-result/{id}`

**Request Body:** (JSON)
```json
{
  "keyResultName": "Updated Key Result",
  "status": "COMPLETED"
}
```

**Response:**
- **200 OK**: Returns the updated key result

### 21. Delete a Key Result

**Endpoint:** `DELETE /api/key-result/{id}`

**Response:**
- **204 No Content**: Key result deleted successfully

## Error Handling

Common error responses across all endpoints:

- **400 Bad Request**: Invalid input
- **404 Not Found**: Resource not found
- **500 Internal Server Error**: Unexpected errors

## Notes

- `ProjectStatus`, `ObjectiveStatus`, and `KeyResultStatus` are enums (`ACTIVE`, `COMPLETED`, etc.)
- Ensure all project, objective, and key result-related data is validated before sending requests
- All endpoints return JSON responses
- Dates should be formatted in ISO 8601 format
- IDs are of type `long`
