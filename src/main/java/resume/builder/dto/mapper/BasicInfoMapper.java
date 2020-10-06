package resume.builder.dto.mapper;

import io.gitgub.eaxdev.jsonresume.validator.model.BasicInfo;
import io.gitgub.eaxdev.jsonresume.validator.model.Location;

public class BasicInfoMapper {
    public static BasicInfo toBasicInfoDto(resume.builder.entity.Resume userResume){
        BasicInfo basicInfoDto = new BasicInfo();
        Location location = new Location();

        location.setAddress(userResume.getAddress());
        location.setCity(userResume.getCity());
        location.setCountryCode(userResume.getCountryCode());
        location.setPostalCode(userResume.getPostalCode());
        location.setRegion(userResume.getRegion());

        basicInfoDto.setLocation(location);
        basicInfoDto.setLabel(userResume.getLabel());
        basicInfoDto.setEmail(userResume.getEmail());
        basicInfoDto.setName(userResume.getName());
        basicInfoDto.setPhone(userResume.getPhone());
        basicInfoDto.setPicture(userResume.getPicture());
        basicInfoDto.setSummary(userResume.getSummary());
        basicInfoDto.setWebsite(userResume.getWebsite());


        return basicInfoDto;
    }
}
