package resume.builder.dto.mapper;

import io.gitgub.eaxdev.jsonresume.validator.model.Volunteer;
import resume.builder.Utils.Constants;

public class VolunteerMapper {
    public static Volunteer toVolunteerDto(resume.builder.entity.Volunteer volunteer){
        Volunteer volunteerDto = new Volunteer();
        volunteerDto.setOrganization(volunteer.getOrganization());
        volunteerDto.setPosition(volunteer.getPosition());
        volunteerDto.setWebsite(volunteer.getWebsite());
        volunteerDto.setSummary(volunteer.getSummary());
        volunteerDto.setHighlights(volunteer.getHighlights().split(Constants.VOLUNTEER_HIGHLIGHTS_DB_DELIMITER));
        volunteerDto.setStartDate(volunteer.getStartDate());
        volunteerDto.setEndDate(volunteer.getEndDate());

        return volunteerDto;
    }
}