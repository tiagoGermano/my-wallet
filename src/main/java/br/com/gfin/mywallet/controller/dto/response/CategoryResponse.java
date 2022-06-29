package br.com.gfin.mywallet.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CategoryResponse {

    private final Long id;

    private final String description;
}
