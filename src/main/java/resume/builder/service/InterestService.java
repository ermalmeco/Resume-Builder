package resume.builder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resume.builder.Utils.Constants;
import resume.builder.entity.Interest;
import resume.builder.entity.Resume;
import resume.builder.entity.Skill;
import resume.builder.repository.InterestRepository;

@Service
public class InterestService {
    @Autowired
    InterestRepository repository;

    public Interest addInterest(io.gitgub.eaxdev.jsonresume.validator.model.Interest interest, Resume result){
        Interest newinterest = new Interest();
        newinterest.setName(interest.getName());
        newinterest.setKeywords(String.join(Constants.INTERESTS_KEYWORDS_DB_DELIMITER,interest.getKeywords()));
        newinterest.setResumeObj(result);

        return repository.save(newinterest);
    }

    public void delete(Interest interest){
        repository.delete(interest);
    }

}
