package br.com.personalfinace.personalfinanceapi.business.tag;

import br.com.personalfinace.personalfinanceapi.business.tag.dto.TagRequest;
import br.com.personalfinace.personalfinanceapi.business.tag.dto.TagResponse;
import br.com.personalfinace.personalfinanceapi.business.user.AuthenticatedUserProvider;
import br.com.personalfinace.personalfinanceapi.business.user.User;
import br.com.personalfinace.personalfinanceapi.common.dto.Response;
import br.com.personalfinace.personalfinanceapi.common.exception.BusinessException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final AuthenticatedUserProvider authUser;

    private TagResponse toResponse(Tag tag) {

        return new TagResponse(
                tag.getId(),
                tag.getName(),
                tag.getIcon(),
                Optional.ofNullable(tag.getParent()).map(Tag::getId).orElse(null),
                tag.getPrimaryHexColor(),
                tag.getSecondaryHexColor()
        );
    }

    private Tag toEntity(TagRequest tagRequest) {
        Tag tag = new Tag();
        tag.setId(tagRequest.id());
        tag.setName(tagRequest.name());
        tag.setPrimaryHexColor(tagRequest.primaryHexColor());
        tag.setSecondaryHexColor(tagRequest.secondaryHexColor());
        if (tagRequest.parentId() != null) {
            tag.setParent(tagRepository.findById(tagRequest.parentId()).orElse(null));
        } else {
            tag.setParent(null);
        }
        return tag;
    }

    public TagResponse save(TagRequest request) throws BusinessException {
        Tag tag;

        if (request.id() != null) {
            tag = tagRepository.findById(request.id())
                    .orElseThrow(() -> new BusinessException("Tag not found"));
        } else {
            tag = new Tag();
            tag.setUser(authUser.getCurrentUser());
        }

        tag.setName(request.name());
        tag.setIcon(request.icon());
        tag.setPrimaryHexColor(request.primaryHexColor());
        tag.setSecondaryHexColor(request.secondaryHexColor());

        return toResponse(tagRepository.save(tag));
    }

    public TagResponse findById(Long id) throws BusinessException {
        return toResponse(tagRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Tag not found")));
    }

    public List<TagResponse> findAll() throws BusinessException {
        User user = authUser.getCurrentUser();
        List<Tag> tags = tagRepository.findByUserIdAndParentIsNull(user.getId());
        return tags.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Set<TagResponse> findAllByIds(List<Long> ids) {
        Set<Tag> tags = getAllByIds(ids);
        return tags.stream().map(this::toResponse).collect(Collectors.toSet());
    }

    public Set<Tag> getAllByIds(List<Long> ids) {
        return new HashSet<>(tagRepository.findAllById(ids));
    }

    @Transactional
    public Response delete(Long id) throws BusinessException {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Tag not found"));
        tagRepository.removeTagFromAllTransactions(id);
        tagRepository.delete(tag);
        return new Response(String.format("Tag %s deleted", tag.getName()));
    }

    public Set<Tag> findAndValidateTags(List<Long> tagIds) throws BusinessException {
        Set<Tag> tags = new HashSet<>(tagRepository.findAllById(tagIds));
        if (tags.size() != tagIds.size()) {
            throw new BusinessException("One or more tags not found");
        }
        return tags;
    }

}
