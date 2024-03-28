package dev.bogdanbalalau.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {

    private String name;
    private String capital;
    private String officialLanguage;
    private Integer population;
}
