package br.com.personalfinace.personalfinanceapi.business.tag;

import br.com.personalfinace.personalfinanceapi.business.tag.customs.TagRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long>, TagRepositoryCustom {

    List<Tag> findByUserIdAndParentIsNull(Long id);
    List<Tag> findByParentId(Long parentId);
    List<Tag> findByUserId(Long userId);

}
