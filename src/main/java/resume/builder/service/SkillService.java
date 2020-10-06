package resume.builder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resume.builder.Utils.Constants;
import resume.builder.entity.Resume;
import resume.builder.entity.Skill;
import resume.builder.entity.Volunteer;
import resume.builder.repository.SkillRepository;

@Service
public class SkillService {
    @Autowired
    SkillRepository repository;

    public Skill addSkill(io.gitgub.eaxdev.jsonresume.validator.model.Skill skill, Resume result){
        Skill newSkill = new Skill();
        newSkill.setName(skill.getName());
        newSkill.setLevel(skill.getLevel());
        newSkill.setKeywords(String.join(Constants.SKILL_KEYWORDS_DB_DELIMITER,skill.getName()));
        newSkill.setResumeObj(result);

        return repository.save(newSkill);
    }

    public void delete(Skill skill){
        repository.delete(skill);
    }

}
