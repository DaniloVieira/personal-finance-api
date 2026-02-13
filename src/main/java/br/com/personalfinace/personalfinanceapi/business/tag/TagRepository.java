package br.com.personalfinace.personalfinanceapi.business.tag;

import br.com.personalfinace.personalfinanceapi.business.tag.customs.TagRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long>, TagRepositoryCustom {
}
