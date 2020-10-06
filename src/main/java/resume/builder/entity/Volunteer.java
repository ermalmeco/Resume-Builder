package resume.builder.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Volunteer {

    @Id
    @GeneratedValue
    private Integer id;

    private String organization;
    private String position;
    private LocalDate startDate;
    private LocalDate endDate;
    private String website;
    private String summary;
    private String highlights;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume")
    private Resume resumeObj;

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", organization='" + organization + '\'' +
                ", position='" + position + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", website='" + website + '\'' +
                ", summary='" + summary + '\'' +
                ", highlights='" + highlights + '\'' +
                '}';
    }
}