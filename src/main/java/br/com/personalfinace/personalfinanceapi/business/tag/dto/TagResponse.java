package br.com.personalfinace.personalfinanceapi.business.tag.dto;

import br.com.personalfinace.personalfinanceapi.common.dto.Response;

public record TagResponse(

        Long id,
        String name,
        Long parentId,
        String primaryHexColor,
        String secondaryHexColor,
        Response message

) {
}
