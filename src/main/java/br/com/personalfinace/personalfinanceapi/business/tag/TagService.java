package br.com.personalfinace.personalfinanceapi.business.tag;

import br.com.personalfinace.personalfinanceapi.business.tag.dto.TagRequest;
import br.com.personalfinace.personalfinanceapi.business.tag.dto.TagResponse;
import br.com.personalfinace.personalfinanceapi.common.dto.Response;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

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

    public TagResponse save(TagRequest tagRequest) {
        Tag tag = toEntity(tagRequest);
        tagRepository.save(tag);
        return toResponse(tag);
    }

    public TagResponse findById(Long id) {
//        return toResponse(Objects.requireNonNull(tagRepository.findById(id).orElse(null)));
        Tag tag =  tagRepository.findById(id).orElse(null);
        if (Objects.isNull(tag)) {
            Exception e = new Exception(String.format( "Tag not found, id: %d", id));
            e.printStackTrace(); // TODO change for logging
            return null;
        }
        return toResponse(tag);
    }

    public List<TagResponse> findAll() {
        List<Tag> tags = tagRepository.findAll();
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
            return null;
        }
        return new Response("Tag deleted");
    }

}
