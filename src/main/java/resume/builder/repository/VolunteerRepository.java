package resume.builder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import resume.builder.entity.Volunteer;

public interface VolunteerRepository extends JpaRepository<Volunteer,Integer> {
}
