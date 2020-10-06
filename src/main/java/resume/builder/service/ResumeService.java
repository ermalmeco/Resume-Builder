package resume.builder.service;

import io.gitgub.eaxdev.jsonresume.validator.JsonResume;
import io.gitgub.eaxdev.jsonresume.validator.exeption.JsonResumeParseException;
import io.gitgub.eaxdev.jsonresume.validator.model.BasicInfo;
import io.gitgub.eaxdev.jsonresume.validator.model.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resume.builder.utils.Constants;
import resume.builder.dto.mapper.*;
import resume.builder.entity.*;
import resume.builder.repository.ResumeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeService {

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    AwardService awardService;

    @Autowired
    EducationService educationService;

    @Autowired
    InterestService interestService;

    @Autowired
    LanguageService languageService;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    PublicationService publicationService;

    @Autowired
    ReferenceService referenceService;

    @Autowired
    SkillService skillService;

    @Autowired
    VolunteerService volunteerService;

    @Autowired
    WorkService workService;

    /**
     *  This function retrieves the resume based on the user id and then creates the Resume structure to match the model
     *  this application is using. The model is defined by https://jsonresume.org/schema/
     *
     * @param userId  int The user id for whom the function is getting the Resume
     * @return Object Resume.class type
     */
    public Resume getResumeByUserId(int userId) {
        resume.builder.entity.Resume userResume = resumeRepository.findResumeByUserId(userId);
        if (userResume == null){
            return null;
        }
        return buildResumeStructure(userResume);
    }

    /**
     *  This is the function that is called to add a new Resume
     *
     * @param jsonData String json based on the schema defined by https://jsonresume.org/schema/
     * @param userIdParameter int The id of the user to whom the function is saving the details
     * @return Object Resume.class type
     * @throws Exception
     */
    public Resume createNewResume(String jsonData,Integer userIdParameter) throws Exception {
        return saveOrUpdateResume(jsonData,userIdParameter,true);
    }

    /**
     *  This is the function that is called to edit the Resume
     *
     * @param jsonData String json based on the schema defined by https://jsonresume.org/schema/
     * @param userIdParameter int The id of the user to whom the function is saving the details
     * @return Object Resume.class type
     * @throws Exception
     */
    public Resume editResume(String jsonData,Integer userIdParameter) throws Exception {
        return saveOrUpdateResume(jsonData,userIdParameter,false);
    }

    /**
     *  This function saves all the details for a resume into the database.
     *  It firstly validates the json passed on the parameters to match the json schema defined by https://jsonresume.org/schema/.
     *  Then if it is an update it retrieves the existing Resume or if not it creates a new one and map all the necessary
     *  details to save them latter on the database.
     *
     * @param jsonData String json based on the schema defined by https://jsonresume.org/schema/
     * @param userIdParameter int The id of the user to whom the function is saving the details
     * @return Object Resume.class type
     * @throws Exception
     */
    public Resume saveOrUpdateResume(String jsonData,Integer userIdParameter,boolean isNew) throws Exception {
        Resume deserializedResume;

        final JsonResume jsonResume = new JsonResume(jsonData);
        if (jsonResume.isValid()){
            deserializedResume = jsonResume.deserialize();

            resume.builder.entity.Resume newResume = resumeRepository.findResumeByUserId(userIdParameter);
            if (!isNew) {
                newResume = clearExistingData(newResume);
            } else {
                if (newResume != null){
                    throw new Exception("User already has a Resume, You can edit that one or delete it before creating another one!");
                }
                newResume = new resume.builder.entity.Resume();
            }

            newResume.setName(deserializedResume.getBasicInfo().getName());
            newResume.setLabel(deserializedResume.getBasicInfo().getLabel());
            newResume.setPicture(deserializedResume.getBasicInfo().getPicture());
            newResume.setEmail(deserializedResume.getBasicInfo().getEmail());
            newResume.setPhone(deserializedResume.getBasicInfo().getPhone());
            newResume.setWebsite(deserializedResume.getBasicInfo().getWebsite());
            newResume.setSummary(deserializedResume.getBasicInfo().getSummary());
            newResume.setAddress(deserializedResume.getBasicInfo().getLocation().getAddress());
            newResume.setPostalCode(deserializedResume.getBasicInfo().getLocation().getPostalCode());
            newResume.setCity(deserializedResume.getBasicInfo().getLocation().getCity());
            newResume.setCountryCode(deserializedResume.getBasicInfo().getLocation().getCountryCode());
            newResume.setRegion(deserializedResume.getBasicInfo().getLocation().getRegion());
            newResume.setUserId(userIdParameter);
            resume.builder.entity.Resume result = resumeRepository.save(newResume);

            List<Work> workList = new ArrayList<>();
            for (io.gitgub.eaxdev.jsonresume.validator.model.Work work:deserializedResume.getWorks()) {
                workList.add(workService.addWork(work,result));
            }

            List<Volunteer> volunteerList = new ArrayList<>();
            for (io.gitgub.eaxdev.jsonresume.validator.model.Volunteer volunteer:deserializedResume.getVolunteers()) {
                volunteerList.add(volunteerService.addVolunteer(volunteer,result));
            }

            List<Education> educationList = new ArrayList<>();
            for (io.gitgub.eaxdev.jsonresume.validator.model.Education education:deserializedResume.getEducations()) {
                educationList.add(educationService.addEducation(education,result));
            }

            List<Award> awardList = new ArrayList<>();
            for (io.gitgub.eaxdev.jsonresume.validator.model.Award award:deserializedResume.getAwards()) {
                awardList.add(awardService.addAwards(award,result));
            }

            List<Publication> publicationList = new ArrayList<>();
            for (io.gitgub.eaxdev.jsonresume.validator.model.Publication publication:deserializedResume.getPublications()) {
                publicationList.add(publicationService.addPublication(publication,result));
            }

            List<Skill> skillList = new ArrayList<>();
            for (io.gitgub.eaxdev.jsonresume.validator.model.Skill skill:deserializedResume.getSkills()) {
                skillList.add(skillService.addSkill(skill,result));
            }

            List<Language> languageList = new ArrayList<>();
            for (io.gitgub.eaxdev.jsonresume.validator.model.Language language:deserializedResume.getLanguages()) {
                languageList.add(languageService.addLanguage(language,result));
            }

            List<Interest> interestList = new ArrayList<>();
            for (io.gitgub.eaxdev.jsonresume.validator.model.Interest interest:deserializedResume.getInterests()) {
                interestList.add(interestService.addInterest(interest,result));
            }

            List<Reference> referenceList = new ArrayList<>();
            for (io.gitgub.eaxdev.jsonresume.validator.model.Reference reference:deserializedResume.getReferences()) {
                referenceList.add(referenceService.addReference(reference,result));
            }

            List<Profiles> profileList = new ArrayList<>();
            for (io.gitgub.eaxdev.jsonresume.validator.model.Profiles profile:deserializedResume.getBasicInfo().getProfiles()) {
                profileList.add(profilesService.addProfiles(profile,result));
            }
            result.setWorks(workList);
            result.setVolunteers(volunteerList);
            result.setEducations(educationList);
            result.setAwards(awardList);
            result.setPublications(publicationList);
            result.setSkills(skillList);
            result.setLanguages(languageList);
            result.setInterests(interestList);
            result.setReferences(referenceList);
            result.setProfiles(profileList);
            result = resumeRepository.save(result);

            System.out.println("Done");
            return buildResumeStructure(result);
        }
        return null;
    }

    /**
     *  Delete a resume and all the resume details, for a user given on the parameters
     *  The function will return only a message in front end defined on Constants as the RESUME_SUCCESS_DELETE_MESSAGE
     *
     * @param userIdParameter int The id of the user to whom we are deleting the Resume
     * @return String contains the result message text
     */
    public String deleteResume(Integer userIdParameter){
        resume.builder.entity.Resume resume = resumeRepository.findResumeByUserId(userIdParameter);
        resume = clearExistingData(resume);
        try {
            resumeRepository.delete(resume);
            return Constants.RESUME_SUCCESS_DELETE_MESSAGE;
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    /**
     *  Deletes all the data for a given Resume on the database.
     *
     * @param resume Object type entity Resume.class
     * @return Object type entity Resume.class. It is the same resume but now with all the relation objects deleted.
     */
    public resume.builder.entity.Resume clearExistingData(resume.builder.entity.Resume resume){
        //delete Works
        List<Work> exWork = resume.getWorks();
        if (exWork != null){
            for (Work work:exWork) {
                workService.delete(work);
            }
            resume.getWorks().removeAll(exWork);
            resume.getWorks().addAll(exWork);
        }

        //delete Volunteers
        List<Volunteer> exVolunteers = resume.getVolunteers();
        if (exVolunteers != null){
            for (Volunteer volunteers:exVolunteers) {
                volunteerService.delete(volunteers);
            }
            resume.getVolunteers().removeAll(exVolunteers);
            resume.getVolunteers().addAll(exVolunteers);
        }

        //delete Educations
        List<Education> exEducations = resume.getEducations();
        if (exEducations != null){
            for (Education education:exEducations) {
                educationService.delete(education);
            }
            resume.getEducations().removeAll(exEducations);
            resume.getEducations().addAll(exEducations);
        }

        //delete Awards
        List<Award> exAwards = resume.getAwards();
        if (exAwards != null){
            for (Award award:exAwards) {
                awardService.delete(award);
            }
            resume.getAwards().removeAll(exAwards);
            resume.getAwards().addAll(exAwards);
        }

        //delete Publications
        List<Publication> exPublications = resume.getPublications();
        if (exPublications != null){
            for (Publication publication:exPublications) {
                publicationService.delete(publication);
            }
            resume.getPublications().removeAll(exPublications);
            resume.getPublications().addAll(exPublications);
        }

        //delete Skills
        List<Skill> exSkills = resume.getSkills();
        if (exSkills != null){
            for (Skill skill:exSkills) {
                skillService.delete(skill);
            }
            resume.getSkills().removeAll(exSkills);
            resume.getSkills().addAll(exSkills);
        }

        //delete Languages
        List<Language> exLanguages = resume.getLanguages();
        if (exLanguages != null){
            for (Language language:exLanguages) {
                languageService.delete(language);
            }
            resume.getLanguages().removeAll(exLanguages);
            resume.getLanguages().addAll(exLanguages);
        }

        //delete Interests
        List<Interest> exInterests = resume.getInterests();
        if (exInterests != null){
            for (Interest interest:exInterests) {
                interestService.delete(interest);
            }
            resume.getInterests().removeAll(exInterests);
            resume.getInterests().addAll(exInterests);
        }

        //delete References
        List<Reference> exReferences = resume.getReferences();
        if (exReferences != null){
            for (Reference reference:exReferences) {
                referenceService.delete(reference);
            }
            resume.getReferences().removeAll(exReferences);
            resume.getReferences().addAll(exReferences);
        }

        //delete Profiles
        List<Profiles> exProfiles = resume.getProfiles();
        if (exProfiles != null){
            for (Profiles profile:exProfiles) {
                profilesService.delete(profile);
            }
            resume.getProfiles().removeAll(exProfiles);
            resume.getProfiles().addAll(exProfiles);
        }

        return resume;
    }

    /**
     *  This function get a resume of type entity Resume.class and then creates the Resume.class object as it is defined
     *  by https://jsonresume.org/schema/.
     *
     * @param userResume Object type entity Resume.class
     * @return Object type Resume.class that matches the model defined by https://jsonresume.org/schema/.
     */
    public Resume buildResumeStructure(resume.builder.entity.Resume userResume){
        Resume resume = new Resume();

        //set Awards to CV
        List<io.gitgub.eaxdev.jsonresume.validator.model.Award> awardList = new ArrayList<>();
        for (Award award: userResume.getAwards()) {
            awardList.add(AwardMapper.toAwardDto(award));
        }

        //set Basic Info to CV
        BasicInfo basicInfo = BasicInfoMapper.toBasicInfoDto(userResume);
        //set Profiles to Basic Info
        List<io.gitgub.eaxdev.jsonresume.validator.model.Profiles> profileList = new ArrayList<>();
        for (Profiles profile: userResume.getProfiles()) {
            profileList.add(ProfileMapper.toProfileDto(profile));
        }

        //set Education to CV
        List<io.gitgub.eaxdev.jsonresume.validator.model.Education> educationList = new ArrayList<>();
        for (Education education: userResume.getEducations()) {
            educationList.add(EducationMapper.toEducationDto(education));
        }

        //set Interests to CV
        List<io.gitgub.eaxdev.jsonresume.validator.model.Interest> interestList = new ArrayList<>();
        for (Interest interest: userResume.getInterests()) {
            interestList.add(InterestMapper.toInterestDto(interest));
        }

        //set Languages to CV
        List<io.gitgub.eaxdev.jsonresume.validator.model.Language> languageList = new ArrayList<>();
        for (Language language: userResume.getLanguages()) {
            languageList.add(LanguageMapper.toLanguageDto(language));
        }

        //set Publications to CV
        List<io.gitgub.eaxdev.jsonresume.validator.model.Publication> publicationList = new ArrayList<>();
        for (Publication publication: userResume.getPublications()) {
            publicationList.add(PublicationMapper.toPublicationDto(publication));
        }

        //set References to CV
        List<io.gitgub.eaxdev.jsonresume.validator.model.Reference> referenceList = new ArrayList<>();
        for (Reference reference: userResume.getReferences()) {
            referenceList.add(ReferenceMapper.toReferenceDto(reference));
        }

        //set Skill to CV
        List<io.gitgub.eaxdev.jsonresume.validator.model.Skill> skillList = new ArrayList<>();
        for (Skill skill: userResume.getSkills()) {
            skillList.add(SkillMapper.toInterestDto(skill));
        }

        //set Volunteer to CV
        List<io.gitgub.eaxdev.jsonresume.validator.model.Volunteer> volunteerList = new ArrayList<>();
        for (Volunteer volunteer: userResume.getVolunteers()) {
            volunteerList.add(VolunteerMapper.toVolunteerDto(volunteer));
        }

        //set Work to CV
        List<io.gitgub.eaxdev.jsonresume.validator.model.Work> workList = new ArrayList<>();
        for (Work work: userResume.getWorks()) {
            workList.add(WorkMapper.toWorkDto(work));
        }

        basicInfo.setProfiles(profileList);
        resume.setBasicInfo(basicInfo);
        resume.setAwards(awardList);
        resume.setEducations(educationList);
        resume.setLanguages(languageList);
        resume.setInterests(interestList);
        resume.setPublications(publicationList);
        resume.setReferences(referenceList);
        resume.setSkills(skillList);
        resume.setVolunteers(volunteerList);
        resume.setWorks(workList);

        return resume;
    }
}