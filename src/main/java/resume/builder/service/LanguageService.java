package resume.builder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resume.builder.entity.Language;
import resume.builder.entity.Resume;
import resume.builder.entity.Skill;
import resume.builder.repository.LanguageRepository;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository repository;

    public Language addLanguage(io.gitgub.eaxdev.jsonresume.validator.model.Language language, Resume result){
        Language newLanguage = new Language();
        newLanguage.setLanguage(language.getLanguage());
        newLanguage.setFluency(language.getFluency());
        newLanguage.setResumeObj(result);

        return repository.save(newLanguage);
    }

    public void delete(Language language){
        System.out.println("Duke fshi languagen "+language.toString());
        repository.delete(language);
    }
}
