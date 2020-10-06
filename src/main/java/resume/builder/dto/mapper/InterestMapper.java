package resume.builder.dto.mapper;

import io.gitgub.eaxdev.jsonresume.validator.model.Interest;
import resume.builder.utils.Constants;

public class InterestMapper {
    public static Interest toInterestDto(resume.builder.entity.Interest interest){
        Interest interestDto = new Interest();
        interestDto.setName(interest.getName());
        interestDto.setKeywords(interest.getKeywords().split(Constants.INTERESTS_KEYWORDS_DB_DELIMITER));

        return interestDto;
    }
}