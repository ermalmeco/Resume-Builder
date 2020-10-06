package resume.builder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import resume.builder.entity.Award;

public interface AwardRepository extends JpaRepository<Award,Integer> {
}
