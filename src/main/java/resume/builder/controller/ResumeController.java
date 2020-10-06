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
import resume.builder.utils.Constants;
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
     * @throws JsonProcessingException
     */
    @GetMapping("/resume/{userId}")
    public ResponseEntity<String> getResume(@PathVariable @Valid @Min(value = 1,message = "User Id should be a number higher than 0") int userId) throws JsonProcessingException {
        logger.info("Controller call /getResume/{userid} where userId = "+userId);
        Resume result =  service.getResumeByUserId(userId);
        String jsonResult = mapper.writeValueAsString(result);
        logger.debug("Controller result /getResume/{userid} where userId = " + userId + ": " + jsonResult);
        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
    }

    /**
     *
     *  Create a resume based on the json schema defined by https://jsonresume.org/schema/
     *  The function gets the json structured from the front end, validates it, save all the details in the database
     *  and then returns the resume structure. The data are converted again into json and are passed to front end.
     *
     * @param jsonResume String json based on the schema defined by https://jsonresume.org/schema/
     * @return Object contains the json result and also the status code. Right now the status code is always OK.
     * @throws JsonResumeParseException
     * @throws JsonProcessingException
     */
    @PostMapping("/addResume")
    public ResponseEntity<String> addResume(@RequestBody String jsonResume) throws JsonResumeParseException, JsonProcessingException {
        logger.info("Controller call /addResume");
        Resume result = service.saveOrUpdateResume(jsonResume,0);
        String jsonResult = mapper.writeValueAsString(result);
        logger.debug("Controller result /addResume : " + jsonResult);
        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
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
     * @throws JsonResumeParseException
     * @throws JsonProcessingException
     */
    @PutMapping("/editResume/{userId}")
    public ResponseEntity<String> editResume(@RequestBody String jsonResume, @PathVariable Integer userId) throws JsonResumeParseException, JsonProcessingException {
        logger.info("Controller call /editResume");
        Resume result = service.saveOrUpdateResume(jsonResume,userId);
        String jsonResult = mapper.writeValueAsString(result);
        logger.debug("Controller result /editResume : " + jsonResult);
        return new ResponseEntity<>(jsonResult, HttpStatus.OK);
    }

    /**
     *  Delete all the Resume details for the user with a given ID. The function will return only a message in front end
     *  defined on Constants as the RESUME_SUCCESS_DELETE_MESSAGE
     *
     * @param userId int The id of the user to whom we are deleting the Resume
     * @return Object contains the result message text and also the status code.
     */
    @DeleteMapping("/deleteResume/{userId}")
    public ResponseEntity<String> deleteResume(@PathVariable Integer userId) {
        logger.info("Controller call /deleteResume");
        String result = service.deleteResume(userId);
        logger.debug("Controller result /deleteResume : " + result);
        if (result.equals(Constants.RESUME_SUCCESS_DELETE_MESSAGE)) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}