package resume.builder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import resume.builder.entity.Profiles;

public interface ProfilesRepository extends JpaRepository<Profiles,Integer> {
}
