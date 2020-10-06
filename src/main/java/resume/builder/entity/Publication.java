package resume.builder.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Publication {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String publisher;
    private String releaseDate;
    private String website;
    private String summary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume")
    private Resume resumeObj;

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", website='" + website + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
