package resume.builder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resume.builder.Utils.Constants;
import resume.builder.entity.Resume;
import resume.builder.entity.Volunteer;
import resume.builder.entity.Work;
import resume.builder.repository.VolunteerRepository;

@Service
public class VolunteerService {
    @Autowired
    VolunteerRepository repository;

    public Volunteer addVolunteer(io.gitgub.eaxdev.jsonresume.validator.model.Volunteer volunteer, Resume result){
        Volunteer newVolunteer = new Volunteer();
        newVolunteer.setOrganization(volunteer.getOrganization());
        newVolunteer.setPosition(volunteer.getPosition());
        newVolunteer.setStartDate(volunteer.getStartDate());
        newVolunteer.setEndDate(volunteer.getEndDate());
        newVolunteer.setSummary(volunteer.getSummary());
        newVolunteer.setWebsite(volunteer.getWebsite());
        newVolunteer.setHighlights(String.join(Constants.VOLUNTEER_HIGHLIGHTS_DB_DELIMITER,volunteer.getHighlights()));
        newVolunteer.setResumeObj(result);

        return repository.save(newVolunteer);
    }

    public void delete(Volunteer volunteer){
        repository.delete(volunteer);
    }

}
