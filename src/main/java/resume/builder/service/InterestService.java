package resume.builder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resume.builder.utils.Constants;
import resume.builder.entity.Interest;
import resume.builder.entity.Resume;
import resume.builder.repository.InterestRepository;

@Service
public class InterestService {
    @Autowired
    InterestRepository repository;

    public Interest addInterest(io.gitgub.eaxdev.jsonresume.validator.model.Interest interest, Resume result){
        Interest newInterest = new Interest();
        newInterest.setName(interest.getName());
        newInterest.setKeywords(String.join(Constants.INTERESTS_KEYWORDS_DB_DELIMITER,interest.getKeywords()));
        newInterest.setResumeObj(result);

        return repository.save(newInterest);
    }

    public void delete(Interest interest){
        repository.delete(interest);
    }

}
