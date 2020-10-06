package resume.builder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resume.builder.entity.Award;
import resume.builder.entity.Resume;
import resume.builder.repository.AwardRepository;

@Service
public class AwardService {

    @Autowired
    AwardRepository repository;

    public Award addAwards(io.gitgub.eaxdev.jsonresume.validator.model.Award award, Resume result){
        Award newAward = new Award();
        newAward.setTitle(award.getTitle());
        newAward.setDate(award.getDate());
        newAward.setAwarder(award.getAwarder());
        newAward.setSummary(award.getSummary());
        newAward.setResumeObj(result);

        return repository.save(newAward);
    }

    public void delete(Award award){
        repository.delete(award);
    }
}
