package com.gameshub.domain.book.googleBook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBookDto {

    private String id;
    private String selfLink;
    private GoogleBookMainInfoDto volumeInfo;
    private GoogleBookSaleInfo saleInfo;
}
