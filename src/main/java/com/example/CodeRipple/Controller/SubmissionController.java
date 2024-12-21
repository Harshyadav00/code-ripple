package com.example.CodeRipple.Controller;

import com.example.CodeRipple.Entities.Submission;
import com.example.CodeRipple.Services.SubmissionService;
import com.example.CodeRipple.Wrapper.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/submissions")
public class SubmissionController {
    @Autowired
    private SubmissionService submissionService;


    private static final String ABSOLUTE_PATH = "C:\\Users\\yaduv\\OneDrive\\Desktop\\code-sandbox"; // Configurable path for sandbox environment
    private static final String OUTPUT_FILE_PATH = "/sandbox/output.txt";
    private static final String INPUT_FILE_PATH = "/sandbox/input.txt";

    @PostMapping
    public ResponseEntity<Submission> submitCode(@RequestBody Submission submission) {
        submission.setStatus("Pending");
        Submission savedSubmission = submissionService.saveSubmission(submission);

        try {
            String code = submission.getCode();
            String language = submission.getLanguage();

            // Create code file
            File codeFile = new File(ABSOLUTE_PATH, "HelloWorld" + getFileExtension(language));
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(codeFile))) {
                writer.write(code);
            }

            // Execute code
            String command = buildDockerCommand(language, ABSOLUTE_PATH, codeFile.getName());
            Process process = Runtime.getRuntime().exec(command);
            int exitCode = process.waitFor();

            String result = new String(process.getInputStream().readAllBytes());
            String error = new String(process.getErrorStream().readAllBytes());

            if (exitCode != 0 || !error.isEmpty()) {
                savedSubmission.setStatus("Failed");
                if (error.length() >= 65535) { // Assuming TEXT type (64 KB limit)
                    error = error.substring(0, 65035);
                }
                savedSubmission.setOutput(error);
            } else {
                File outputFile = new File(ABSOLUTE_PATH + "/output.txt");
                if (outputFile.exists() && outputFile.length() > 0) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
                        StringBuilder outputBuilder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            outputBuilder.append(line).append(System.lineSeparator());
                        }
                        savedSubmission.setOutput(outputBuilder.toString().trim());
                    }
                } else {
                    savedSubmission.setStatus("Failed");
                    savedSubmission.setOutput("Output file is missing or empty.");
                }
            }
            savedSubmission.setStatus("Completed");
        } catch (IOException | InterruptedException e) {
            savedSubmission.setStatus("Failed");
            savedSubmission.setOutput("Execution Error: " + e.getMessage());
        }

        submissionService.saveSubmission(savedSubmission);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSubmission);
    }


    // Method to build the Docker command for running the code
    private String buildDockerCommand(String language, String location, String codeFileName) {
        return String.format("docker run --rm -v %s:/sandbox code-sandbox %s /sandbox/%s %s %s",
                location, language, codeFileName, INPUT_FILE_PATH, OUTPUT_FILE_PATH);
    }

    // Method to update the submission status and output after execution
    private Submission updateSubmissionStatus(Submission savedSubmission, File codeFile) throws IOException {
        File outputFile = new File(ABSOLUTE_PATH, "output.txt");
        if (outputFile.exists()) {
            savedSubmission.setStatus("Completed");
            String output = readFile(outputFile);
            savedSubmission.setOutput(output);
        } else {
            savedSubmission.setStatus("Failed");
            savedSubmission.setOutput("Output file not found.");
        }

        return submissionService.saveSubmission(savedSubmission);
    }

    // Method to read the content of the output file
    private String readFile(File outputFile) throws IOException {
        StringBuilder outputBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                outputBuilder.append(line).append(System.lineSeparator());
            }
        }
        return outputBuilder.toString().trim();
    }

    // Method to handle execution errors
    private Submission handleExecutionError(Submission savedSubmission, Exception e) {
        savedSubmission.setStatus("Failed");
        savedSubmission.setOutput("Error during code execution: " + e.getMessage());
        return submissionService.saveSubmission(savedSubmission);
    }

    // Method to get the file extension based on the programming language
    private String getFileExtension(String language) {
        switch (language.toLowerCase()) {
            case "python3":
                return ".py";
            case "java":
                return ".java";
            case "cpp":
                return ".cpp";
            case "javascript":
                return ".js";
            default:
                throw new IllegalArgumentException("Unsupported language: " + language);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Submission>> getSubmissionById(@PathVariable Long id) {
        return submissionService.getSubmissionById(id)
                .map(submission -> ResponseEntity.ok(new ApiResponse<>(true, submission, null)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, null, "Submission not found")));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Submission>>> getSubmissionByUserId(@PathVariable String userId){
        return submissionService.getSubmissionByUserId(userId);
    }

    @GetMapping("/problem/{problemId}")
    public ResponseEntity<ApiResponse<List<Submission>>> getSubmissionByProblemId(@PathVariable Long problemId){
        return submissionService.getSubmissionByProblemId(problemId);
    }

    @GetMapping("/{problemId}/{userId}")
    public ResponseEntity<ApiResponse<List<Submission>>> getSubmissionByUserId(@PathVariable String userId, @PathVariable Long problemId){
        return submissionService.getSubmissionByProblemIdAndUserId(problemId, userId);
    }

}

