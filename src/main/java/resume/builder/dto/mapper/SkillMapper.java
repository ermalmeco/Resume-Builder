package resume.builder.dto.mapper;

import io.gitgub.eaxdev.jsonresume.validator.model.Skill;
import resume.builder.utils.Constants;

public class SkillMapper {
    public static Skill toInterestDto(resume.builder.entity.Skill skill){
        Skill skillDto = new Skill();
        skillDto.setName(skill.getName());
        skillDto.setLevel(skill.getLevel());
        skillDto.setKeywords(skill.getKeywords().split(Constants.SKILL_KEYWORDS_DB_DELIMITER));

        return skillDto;
    }
}