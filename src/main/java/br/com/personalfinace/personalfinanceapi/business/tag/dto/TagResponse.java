package br.com.personalfinace.personalfinanceapi.business.tag.dto;

public record TagResponse(

        Long id,
        String name,
        String icon,
        Long parentId,
        String primaryHexColor,
        String secondaryHexColor
) {
}
