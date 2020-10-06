package resume.builder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resume.builder.utils.Constants;
import resume.builder.entity.Resume;
import resume.builder.entity.Work;
import resume.builder.repository.WorkRepository;

@Service
public class WorkService {

    @Autowired
    WorkRepository repository;

    public Work addWork(io.gitgub.eaxdev.jsonresume.validator.model.Work work, Resume resume){
        Work newWork = new Work();
        newWork.setCompany(work.getCompany());
        newWork.setPosition(work.getPosition());
        newWork.setStartDate(work.getStartDate());
        newWork.setEndDate(work.getEndDate());
        newWork.setWebsite(work.getWebsite());
        newWork.setSummary(work.getSummary());
        newWork.setHighlights(String.join(Constants.WORK_HIGHLIGHTS_DB_DELIMITER,work.getHighlights()));
        newWork.setResumeObj(resume);

        return repository.save(newWork);
    }

    public void delete(Work work){
        repository.delete(work);
    }
}
