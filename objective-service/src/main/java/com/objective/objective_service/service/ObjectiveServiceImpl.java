package com.objective.objective_service.service;

import com.objective.objective_service.entity.KeyResult;
import com.objective.objective_service.entity.Objective;
import com.objective.objective_service.entity.Task;
import com.objective.objective_service.exception.ObjectiveNotFoundException;
import com.objective.objective_service.repository.ObjectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ObjectiveServiceImpl implements ObjectiveService {

    // Repository for Objective entity, responsible for database operations
    private final ObjectiveRepository objectiveRepository;

    // Logger to log important service-related information
    private static final Logger LOGGER = Logger.getLogger(ObjectiveServiceImpl.class.getName());

    // URL for communicating with the KeyResult service
    private static final String KEYRESULT_SERVICE_URL = "http://localhost:8082/api/keyresults/";

    // URL for communicating with the Task service
    private static final String TASK_SERVICE_URL = "http://localhost:8083/api/tasks/";

    // Constructor injection for ObjectiveRepository, allows dependency injection
    @Autowired
    public ObjectiveServiceImpl(ObjectiveRepository objectiveRepository){
        this.objectiveRepository = objectiveRepository;
    }

    // RestTemplate to interact with external services (KeyResult & Task services)
    @Autowired
    private RestTemplate restTemplate;

    // Fetches all objectives from the database along with associated KeyResults and Tasks from respective services
    @Override
    public List<Objective> getAllObjective() {
        LOGGER.info("Fetching all objectives from the database...");

        // Retrieve all objectives from the repository
        List<Objective> objectives = objectiveRepository.findAll();

        // Iterate through each objective to fetch related KeyResults and Tasks
        for (Objective objective : objectives) {
            Long objectiveId = objective.getObjectiveId();

            try {
                // Fetch KeyResults from KeyResult service using the objective ID
                String keyResultUrl = KEYRESULT_SERVICE_URL + "objective/" + objectiveId;
                List<KeyResult> keyResults = Optional.ofNullable(
                        restTemplate.getForObject(keyResultUrl, KeyResult[].class)
                ).map(Arrays::asList).orElse(Collections.emptyList());
                objective.setKeyResult(keyResults);

                // Fetch Tasks from Task service using the objective ID
                String taskUrl = TASK_SERVICE_URL + "objective/" + objectiveId;
                List<Task> tasks = Optional.ofNullable(
                        restTemplate.getForObject(taskUrl, Task[].class)
                ).map(Arrays::asList).orElse(Collections.emptyList());
                objective.setObjectiveTaskList(tasks);

            } catch (RestClientException e) {
                // Log error if there's an issue while fetching data from external services
                LOGGER.severe("Error fetching data for Objective ID: " + objectiveId + " - " + e.getMessage());
                e.printStackTrace(); // Log the stack trace for debugging
            }
        }

        // Return the list of objectives, now populated with KeyResults and Tasks
        return objectives;
    }

    // Fetches a specific objective by its ID, along with related KeyResults and Tasks
    @Override
    public Objective getObjective(Long id) {
        LOGGER.info("Fetching objective with ID: " + id);

        // Retrieve the objective from the database
        Optional<Objective> objectiveOpt = objectiveRepository.findById(id);
        if (!objectiveOpt.isPresent()) {
            throw new ObjectiveNotFoundException("Objective not found with ID: " + id);
        }

        Objective objective = objectiveOpt.get();

        // Fetch related KeyResults from KeyResult service
        String url = KEYRESULT_SERVICE_URL + "objective/" + id;
        LOGGER.info("Fetching key results from: " + url);

        try {
            // Get the list of KeyResults for the objective
            List<KeyResult> keyResults = Arrays.asList(
                    restTemplate.getForObject(url, KeyResult[].class)
            );
            objective.setKeyResult(keyResults);
        } catch (RestClientException e) {
            // Log error if unable to fetch key results
            LOGGER.severe("Failed to fetch key results for Objective ID: " + id + " - " + e.getMessage());
            throw new RuntimeException("Failed to fetch key results from the microservice.");
        }

        // Fetch related Tasks from Task service
        String taskurl = TASK_SERVICE_URL + "objective/" + id;
        LOGGER.info("Fetching tasks from: " + taskurl);

        try {
            // Get the list of Tasks for the objective
            List<Task> tasks = Arrays.asList(
                    restTemplate.getForObject(taskurl, Task[].class)
            );
            objective.setObjectiveTaskList(tasks);

        } catch (RestClientException e) {
            // Log error if unable to fetch tasks
            LOGGER.severe("Failed to fetch tasks for Objective ID: " + id + " - " + e.getMessage());
            throw new RuntimeException("Failed to fetch key results from the tasks microservice.");
        }

        // Return the objective with its related KeyResults and Tasks
        return objective;
    }

    // Creates a new objective and persists it to the database
    @Override
    public Objective createObjective(Objective obj) {
        LOGGER.info("Creating a new objective...");

        // Save the objective and return it
        return objectiveRepository.save(obj);
    }

    // Updates an existing objective identified by its ID
    @Override
    public Objective updateObjective(Objective objectiveToUpdate, Long objectiveId) {
        LOGGER.info("Updating objective with ID: " + objectiveId);

        // Fetch the existing objective to update
        Objective existingObjective = objectiveRepository.findById(objectiveId)
                .orElseThrow(() -> new ObjectiveNotFoundException("Objective with ID " + objectiveId + " not found."));

        // Update the existing objective's fields with the new values
        existingObjective.setObjectiveName(objectiveToUpdate.getObjectiveName());
        existingObjective.setObjectiveDescription(objectiveToUpdate.getObjectiveDescription());
        existingObjective.setMappedProject(objectiveToUpdate.getMappedProject());
        existingObjective.setAssignedTo(objectiveToUpdate.getAssignedTo());
        existingObjective.setKeyResultIds(objectiveToUpdate.getKeyResultIds());
        existingObjective.setKeyResult(objectiveToUpdate.getKeyResult());
        existingObjective.setObjectiveTaskIds(objectiveToUpdate.getObjectiveTaskIds());
        existingObjective.setObjectiveTaskList(objectiveToUpdate.getObjectiveTaskList());
        existingObjective.setObjectiveStartDate(objectiveToUpdate.getObjectiveStartDate());
        existingObjective.setObjectiveDueDate(objectiveToUpdate.getObjectiveDueDate());
        existingObjective.setObjectiveStatus(objectiveToUpdate.getObjectiveStatus());
        existingObjective.setObjectiveIsActive(objectiveToUpdate.isObjectiveIsActive());

        // Save the updated objective and return it
        Objective updatedObjective = objectiveRepository.save(existingObjective);
        LOGGER.info("Objective with ID: " + objectiveId + " updated successfully.");

        return updatedObjective;
    }

    // Removes an objective identified by its ID from the database
    @Override
    public void removeObjective(Long objectiveId) {
        LOGGER.info("Removing objective with ID: " + objectiveId);

        // Retrieve the objective before deleting it to ensure it exists
        Objective objectiveToDelete = getObjective(objectiveId);
        objectiveRepository.deleteById(objectiveId);
    }

    // Fetches all objectives associated with a specific project
    @Override
    public List<Objective> getAllObjectiveByProjectId(Long projectId) {
        LOGGER.info("Fetching all objectives for projectID: " + projectId);

        // Return objectives mapped to the given project
        return objectiveRepository.findByMappedProject(projectId);
    }

    // Calculates the progress of all objectives within a project
    @Override
    public double getProgress(Long projectId) {
        LOGGER.info("Fetching all objectives for project ID: " + projectId);

        // Fetch all objectives associated with the given project
        List<Objective> objectives = objectiveRepository.findByMappedProject(projectId);

        double totalProgress = 0.0;
        int totalObjectives = objectives.size();

        // Calculate the progress of each objective based on its tasks
        for (Objective objective : objectives) {
            Long objectiveId = objective.getObjectiveId();

            // Fetch related tasks for the objective from Task service
            String taskUrl = TASK_SERVICE_URL + "objective/" + objectiveId;
            LOGGER.info("Fetching tasks for Objective ID: " + objectiveId + " from: " + taskUrl);

            try {
                // Fetch tasks from Task microservice
                List<Task> tasks = Optional.ofNullable(
                        restTemplate.getForObject(taskUrl, Task[].class)
                ).map(Arrays::asList).orElse(Collections.emptyList());

                // Calculate the completion percentage of tasks for the objective
                double completionPercentage = 0.0;
                if (!tasks.isEmpty()) {
                    long completedTasks = tasks.stream()
                            .filter(task -> !task.isTaskIsActive()) // Active tasks are not completed
                            .count();
                    completionPercentage = ((double) completedTasks / tasks.size()) * 100;
                }

                // Add this objective's progress to the total progress
                totalProgress += completionPercentage;
            } catch (RestClientException e) {
                // Log error if unable to fetch tasks for the objective
                LOGGER.severe("Failed to fetch tasks for Objective ID: " + objectiveId + " - " + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("Failed to fetch tasks for Objective ID: " + objectiveId);
            }
        }

        // Calculate the overall project progress as the average of all objectives' progress
        return totalObjectives > 0 ? totalProgress / totalObjectives : 0.0;
    }
}

