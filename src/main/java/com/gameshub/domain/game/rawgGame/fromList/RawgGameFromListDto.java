package com.gameshub.domain.game.rawgGame.fromList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RawgGameFromListDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "slug")
    private String slug;

    @JsonProperty(value = "released")
    private LocalDate released;

    @JsonProperty(value = "tba")
    private boolean tba;

    @JsonProperty(value = "background_image")
    private String backgroundImage;

    @JsonProperty(value = "metacritic")
    private double metacritic;

    @JsonProperty(value = "platforms")
    private List<RawgGamePlatforms> platforms;

    @Override
    public String toString() {
        return "\n\nRawgGameFromListDto{" +
                "\nid=" + id +
                "\nname='" + name + '\'' +
                "\nreleased=" + released +
                "\ntba=" + tba +
                "\nbackgroundImage='" + backgroundImage + '\'' +
                "\nmetacritic=" + metacritic +
                "\n\nplatforms=" + platforms +
                '}';
    }
}
