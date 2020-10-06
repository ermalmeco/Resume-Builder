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
public class Education {
    @Id
    @GeneratedValue
    private Integer id;
    private String institution;
    private String area;
    private String studyType;
    private String gpa;
    private String courses;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume")
    private Resume resumeObj;

    @Override
    public String toString() {
        return "Education{" +
                "id=" + id +
                ", institution='" + institution + '\'' +
                ", area='" + area + '\'' +
                ", studyType='" + studyType + '\'' +
                ", gpa='" + gpa + '\'' +
                ", courses='" + courses + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}