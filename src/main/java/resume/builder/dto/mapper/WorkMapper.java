package resume.builder.dto.mapper;

import io.gitgub.eaxdev.jsonresume.validator.model.Work;
import resume.builder.utils.Constants;

public class WorkMapper extends Work {
    public static Work toWorkDto(resume.builder.entity.Work work){
        Work workDto = new Work();
        workDto.setCompany(work.getCompany());
        workDto.setPosition(work.getPosition());
        workDto.setWebsite(work.getWebsite());
        workDto.setHighlights(work.getHighlights().split(Constants.WORK_HIGHLIGHTS_DB_DELIMITER));
        workDto.setStartDate(work.getStartDate());
        workDto.setEndDate(work.getEndDate());

        return workDto;
    }
}