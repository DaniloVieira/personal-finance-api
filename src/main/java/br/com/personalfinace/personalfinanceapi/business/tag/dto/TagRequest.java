package br.com.personalfinace.personalfinanceapi.business.tag.dto;

public record TagRequest(

        Long id,
        String name,
        Long parentId,
        String primaryHexColor,
        String secondaryHexColor

) {
}
