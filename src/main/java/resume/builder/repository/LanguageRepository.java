package resume.builder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import resume.builder.entity.Language;

public interface LanguageRepository extends JpaRepository<Language,Integer> {
}
