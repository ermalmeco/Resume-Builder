package resume.builder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resume.builder.Utils.Constants;
import resume.builder.entity.Education;
import resume.builder.entity.Resume;
import resume.builder.entity.Volunteer;
import resume.builder.repository.EducationRepository;

@Service
public class EducationService {

    @Autowired
    EducationRepository repository;

    public Education addEducation(io.gitgub.eaxdev.jsonresume.validator.model.Education education, Resume result){
        Education newEducation = new Education();
        newEducation.setInstitution(education.getInstitution());
        newEducation.setArea(education.getArea());
        newEducation.setStudyType(education.getStudyType());
        newEducation.setGpa(education.getGpa());
        newEducation.setCourses(String.join(Constants.COURSES_DB_DELIMITER,education.getCourses()));
        newEducation.setResumeObj(result);

        return repository.save(newEducation);
    }

    public void delete(Education education){
        repository.delete(education);
    }
}
