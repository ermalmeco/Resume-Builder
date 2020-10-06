package resume.builder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import resume.builder.entity.Interest;

public interface InterestRepository extends JpaRepository<Interest,Integer> {
}
