package resume.builder.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Resume {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String label;
    private String picture;
    private String email;
    private String phone;
    private String website;
    private String summary;
    private String address;
    private String postalCode;
    private String city;
    private String countryCode;
    private String region;

    //TODO:Replace with the OneToOne relation with user table
    private int userId;

    @OneToMany(mappedBy="resumeObj", fetch=FetchType.LAZY)
    private List<Profiles> profiles;

    @OneToMany(mappedBy="resumeObj", fetch=FetchType.LAZY)
    private List<Work> works;

    @OneToMany(mappedBy="resumeObj", fetch=FetchType.LAZY)
    private List<Volunteer> volunteers;

    @OneToMany(mappedBy="resumeObj", fetch=FetchType.LAZY)
    private List<Education> educations;

    @OneToMany(mappedBy="resumeObj", fetch=FetchType.LAZY)
    private List<Award> awards;

    @OneToMany(mappedBy="resumeObj", fetch=FetchType.LAZY)
    private List<Publication> publications;

    @OneToMany(mappedBy="resumeObj", fetch=FetchType.LAZY)
    private List<Skill> skills;

    @OneToMany(mappedBy="resumeObj", fetch=FetchType.LAZY)
    private List<Language> languages;

    @OneToMany(mappedBy="resumeObj", fetch=FetchType.LAZY)
    private List<Interest> interests;

    @OneToMany(mappedBy="resumeObj", fetch=FetchType.LAZY)
    private List<Reference> references;

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", picture='" + picture + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", summary='" + summary + '\'' +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", region='" + region + '\'' +
                ", userId=" + userId +
                ", profiles=" + profiles +
                ", works=" + works +
                ", volunteers=" + volunteers +
                ", educations=" + educations +
                ", awards=" + awards +
                ", publications=" + publications +
                ", skills=" + skills +
                ", languages=" + languages +
                ", interests=" + interests +
                ", references=" + references +
                '}';
    }
}