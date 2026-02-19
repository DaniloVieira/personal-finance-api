package br.com.personalfinace.personalfinanceapi.business.tag;

import br.com.personalfinace.personalfinanceapi.business.tag.dto.TagRequest;
import br.com.personalfinace.personalfinanceapi.business.tag.dto.TagResponse;
import br.com.personalfinace.personalfinanceapi.business.user.AuthenticatedUserProvider;
import br.com.personalfinace.personalfinanceapi.business.user.User;
import br.com.personalfinace.personalfinanceapi.common.dto.Response;
import br.com.personalfinace.personalfinanceapi.common.exception.BusinessException;
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
                Optional.ofNullable(tag.getParent()).map(Tag::getId).orElse(null),
                tag.getPrimaryHexColor(),
                tag.getSecondaryHexColor(),
                null
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

    public TagResponse save(TagRequest tagRequest) throws BusinessException {
        Tag tag = toEntity(tagRequest);
        if(Objects.isNull(tag.getId())) {
            tag.setUser(authUser.getCurrentUser());
        }
        tagRepository.save(tag);
        return toResponse(tag);
    }

    public TagResponse findById(Long id) {
        Tag tag =  tagRepository.findById(id).orElse(null);
        if (Objects.isNull(tag)) {
            Exception e = new Exception(String.format( "Tag not found, id: %d", id));
            e.printStackTrace(); // TODO change for logging
            return null;
        }
        return toResponse(tag);
    }

    public List<TagResponse> findAll() throws BusinessException {
        User user = authUser.getCurrentUser();
        List<Tag> tags = tagRepository.findByUserId(user.getId());
        return tags.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Set<TagResponse> findAllByIds(List<Long> ids) {
        Set<Tag> tags = getAllByIds(ids);
        return tags.stream().map(this::toResponse).collect(Collectors.toSet());
    }

    public Set<Tag> getAllByIds(List<Long> ids) {
        return new HashSet<>(tagRepository.findAllById(ids));
    }

    public Response delete(Long id) {
        Tag tag = tagRepository.findById(id).orElse(null);
        if (Objects.isNull(tag)) {
            return new Response(String.format("Tag id %d not found", id) );
        }
        tagRepository.delete(tag);
        return new Response(String.format("Tag %s deleted", tag.getName()));
    }

}
