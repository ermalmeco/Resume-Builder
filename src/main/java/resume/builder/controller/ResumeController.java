package resume.builder.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gitgub.eaxdev.jsonresume.validator.exeption.JsonResumeParseException;
import io.gitgub.eaxdev.jsonresume.validator.model.Resume;
import io.gitgub.eaxdev.jsonresume.validator.validator.JsonResumeValidationModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import resume.builder.ResumeApplication;
import resume.builder.excteption.RecordNotFoundException;
import resume.builder.excteption.ResumeAlreadyExists;
import resume.builder.service.ResumeService;

import javax.validation.Valid;
import javax.validation.constraints.Min;


@RestController
@Validated
public class ResumeController {

    private static final Logger logger = LogManager.getLogger(ResumeApplication.class);

    @Autowired
    private ResumeService service;

    @Autowired
    ObjectMapper mapper = new ObjectMapper().registerModule(new JsonResumeValidationModule());

    /**
     *
     *  Get the user id and based on that it retrieves all the Resume details and builds with them the Resume structure
     *  based on https://jsonresume.org/schema/. The data are converted again into json and are passed to front end
     *
     * @param userId int The user id for whom the function is getting the Resume
     * @return Object contains the json result and also the status code. Right now the status code is always OK.
     */
    @GetMapping("/resume/{userId}")
    public ResponseEntity<String> getResume(@PathVariable @Valid @Min(value = 1,message = "User Id should be a number higher than 0") int userId) {
        logger.info("Controller call /resume/{userid} where userId = "+userId);
        try {
            Resume result =  service.getResumeByUserId(userId);
            String jsonResult = mapper.writeValueAsString(result);
            logger.debug("Controller result /resume/{userId} where userId = " + userId + ": " + jsonResult);
            return new ResponseEntity<>(jsonResult, HttpStatus.OK);
        }catch (RecordNotFoundException rex){
            System.out.println(rex.getMessage());
            logger.debug("Controller failed to get resume at /resume/{userId} : " + rex.getMessage());
            throw new RecordNotFoundException(rex.getMessage());
        }catch (JsonProcessingException ex){
            System.out.println(ex.getMessage());
            logger.debug("Controller failed to parse Json at /resume/{userId} : " + ex.getMessage());
            throw new RecordNotFoundException(ex.getMessage());
        }
    }

    /**
     *
     *  Create a resume based on the json schema defined by https://jsonresume.org/schema/
     *  The function gets the json structured from the front end, validates it, save all the details in the database
     *  and then returns the resume structure. The data are converted again into json and are passed to front end.
     *
     * @param jsonResume String json based on the schema defined by https://jsonresume.org/schema/
     * @return Object contains the json result and also the status code.
     */
    @PostMapping("/addResume/{userId}")
    public ResponseEntity<Object> addResume(@RequestBody String jsonResume, @PathVariable Integer userId) throws Exception {
        logger.info("Controller call /addResume");
        try {
            Resume result = service.createNewResume(jsonResume, userId);
            String jsonResult = mapper.writeValueAsString(result);
            logger.debug("Controller result /addResume : " + jsonResult);
            return new ResponseEntity<>(jsonResult, HttpStatus.OK);
        }catch (ResumeAlreadyExists ex){
            System.out.println(ex.getMessage());
            logger.debug("Controller failed at /addResume : " + ex.getMessage());
            throw new ResumeAlreadyExists(ex.getMessage());
        }
    }

    /**
     *  Edit a resume based on the json that it retrieves. The json should be based on the json schema defined by
     *  https://jsonresume.org/schema/
     *  The function gets the json structured from the front end, validates it, edit all the details in the database
     *  and then returns the resume structure. The data are converted again into json and are passed to front end.
     *
     * @param jsonResume String json based on the schema defined by https://jsonresume.org/schema/
     * @param userId int The id of the user to whom the function is editing the details
     * @return Object contains the json result and also the status code. Right now the status code is always OK.
     * @throws JsonResumeParseException Exception that is thrown when resume is not parsed correctly
     * @throws JsonProcessingException Exception that is thrown when resume is not processed correctly
     */
    @PutMapping("/editResume/{userId}")
    public ResponseEntity<String> editResume(@RequestBody String jsonResume, @PathVariable Integer userId) throws Exception {
        try {
            logger.info("Controller call /editResume");
            Resume result = service.editResume(jsonResume, userId);
            String jsonResult = mapper.writeValueAsString(result);
            logger.debug("Controller result /editResume : " + jsonResult);
            return new ResponseEntity<>(jsonResult, HttpStatus.OK);
        }catch (RecordNotFoundException rex){
            System.out.println(rex.getMessage());
            logger.debug("Controller failed at /editResume : " + rex.getMessage());
            throw new RecordNotFoundException(rex.getMessage());
        }
    }

    /**
     *  Delete all the Resume details for the user with a given ID. The function will return only a message in front end
     *  defined on Constants as the RESUME_SUCCESS_DELETE_MESSAGE
     *
     * @param userId int The id of the user to whom we are deleting the Resume
     * @return Object contains the result message text and also the status code.
     */
    @DeleteMapping("/deleteResume/{userId}")
    public ResponseEntity<String> deleteResume(@PathVariable Integer userId) throws Exception {
        try {
            logger.info("Controller call /deleteResume");
            String result = service.deleteResume(userId);
            logger.debug("Controller result /deleteResume : " + result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (RecordNotFoundException rex){
            System.out.println(rex.getMessage());
            logger.debug("Controller failed at /deleteResume : " + rex.getMessage());
            throw new RecordNotFoundException(rex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("Controller failed to delete resume at /deleteResume : " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}