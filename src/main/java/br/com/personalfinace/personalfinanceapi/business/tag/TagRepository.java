package br.com.personalfinace.personalfinanceapi.business.tag;

import br.com.personalfinace.personalfinanceapi.business.tag.customs.TagRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long>, TagRepositoryCustom {

    List<Tag> findByUserIdAndParentIsNull(Long id);
    List<Tag> findByParentId(Long parentId);
    List<Tag> findByUserId(Long userId);

    @Modifying
    @Query(value = "DELETE FROM pfi_transaction_tag WHERE fk_tag = :tagId", nativeQuery = true)
    void removeTagFromAllTransactions(@Param("tagId") Long tagId);

}
