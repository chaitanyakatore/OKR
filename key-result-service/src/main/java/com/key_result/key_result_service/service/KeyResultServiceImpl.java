package com.key_result.key_result_service.service;

import com.key_result.key_result_service.entity.KeyResult;
import com.key_result.key_result_service.entity.Task;
import com.key_result.key_result_service.exception.ResourceNotFoundException;
import com.key_result.key_result_service.repository.KeyResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class KeyResultServiceImpl implements KeyResultService {

    // Injecting the KeyResultRepository to interact with the database
    private final KeyResultRepository keyResultRepository;

    // Defining the TASK_SERVICE_URL to interact with Task Service via HTTP
    private final static String TASK_SERVICE_URL = "http://localhost:8083/api/tasks/";

    // Constructor-based injection for KeyResultRepository
    @Autowired
    public KeyResultServiceImpl(KeyResultRepository keyResultRepository) {
        this.keyResultRepository = keyResultRepository;
    }

    // Autowiring RestTemplate to make HTTP requests to other services (like Task Service)
    @Autowired
    private RestTemplate restTemplate;

    // Method to add a new KeyResult to the database
    @Override
    public KeyResult addKeyResult(KeyResult keyResult) {
        // Saves the provided KeyResult object and returns the saved instance
        return keyResultRepository.save(keyResult);
    }

    // Method to get all KeyResults from the database
    @Override
    public List<KeyResult> getAllKeyResult() {
        // Retrieves all KeyResults from the repository
        return keyResultRepository.findAll();
    }

    // Method to get a specific KeyResult by its ID
    @Override
    public KeyResult getKeyResult(Long id) {
        // Tries to fetch the KeyResult by ID, throws exception if not found
        return keyResultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("KeyResult not found with id: " + id));
    }

    // Method to fetch all KeyResults associated with a given Objective ID
    @Override
    public List<KeyResult> getKeyResultsByObjectiveId(Long objectiveId) {
        // Retrieves KeyResults linked to the provided Objective ID from the repository
        List<KeyResult> keyResults = keyResultRepository.findKeyResultByAssociatedObjectiveId(objectiveId);
        // If no KeyResults are found, throw a ResourceNotFoundException
        if (keyResults.isEmpty()) {
            throw new ResourceNotFoundException("No KeyResults found for Objective ID: " + objectiveId);
        }
        return keyResults;
    }

    // Method to update an existing KeyResult
    @Override
    public KeyResult updateKeyResult(Long id, KeyResult updatedKeyResult) {
        // Checks if the KeyResult exists and updates it with new data
        return keyResultRepository.findById(id)
                .map(existingKeyResult -> {
                    // Update each field of the existing KeyResult with the new values
                    existingKeyResult.setKeyResultName(updatedKeyResult.getKeyResultName());
                    existingKeyResult.setKeyResultOwnerId(updatedKeyResult.getKeyResultOwnerId());
                    existingKeyResult.setAssociatedObjectiveId(updatedKeyResult.getAssociatedObjectiveId());
                    existingKeyResult.setKeyResultAssociatedTasksId(updatedKeyResult.getKeyResultAssociatedTasksId());
                    existingKeyResult.setKeyResultAssociatedTasks(updatedKeyResult.getKeyResultAssociatedTasks());
                    existingKeyResult.setKeyResultDueDate(updatedKeyResult.getKeyResultDueDate());
                    // Save the updated KeyResult to the database
                    return keyResultRepository.save(existingKeyResult);
                })
                .orElseThrow(() -> new ResourceNotFoundException("KeyResult not found with id: " + id));
    }

    // Method to remove a KeyResult by its ID
    @Override
    public void removeKeyResult(Long id) {
        // Checks if the KeyResult exists before trying to delete it
        if (!keyResultRepository.existsById(id)) {
            throw new ResourceNotFoundException("KeyResult not found with id: " + id);
        }
        // Deletes the KeyResult from the database
        keyResultRepository.deleteById(id);
    }
}



