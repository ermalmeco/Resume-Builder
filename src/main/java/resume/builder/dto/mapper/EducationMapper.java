package resume.builder.dto.mapper;

import io.gitgub.eaxdev.jsonresume.validator.model.Education;
import resume.builder.utils.Constants;


public class EducationMapper {
    public static Education toEducationDto(resume.builder.entity.Education education){
        Education educationDto = new Education();
        educationDto.setArea(education.getArea());
        educationDto.setGpa(education.getGpa());
        educationDto.setInstitution(education.getInstitution());
        educationDto.setStudyType(education.getStudyType());
        educationDto.setCourses(education.getCourses().split(Constants.COURSES_DB_DELIMITER));
        educationDto.setStartDate(education.getStartDate());
        educationDto.setEndDate(education.getEndDate());

        return educationDto;
    }
}