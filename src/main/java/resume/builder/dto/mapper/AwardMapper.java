package resume.builder.dto.mapper;

import io.gitgub.eaxdev.jsonresume.validator.model.Award;

public class AwardMapper {
    public static Award toAwardDto(resume.builder.entity.Award award){
        Award awardDto = new Award();
        awardDto.setTitle(award.getTitle());
        awardDto.setAwarder(award.getAwarder());
        awardDto.setDate(award.getDate());
        awardDto.setSummary(award.getSummary());

        return awardDto;
    }
}
