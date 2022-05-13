package com.gameshub.domain.game;

import com.gameshub.domain.user.User;
import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@NamedEntityGraph(
        name = "graph.GameOpinion.user",
        attributeNodes = @NamedAttributeNode("user")
)
@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "GAME_OPINIONS")
public class GameOpinion {

    @Id
    @EqualsAndHashCode.Include
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @EqualsAndHashCode.Exclude
    @Column(name = "GAME_NAME")
    private String gameName;

    @NotNull
    @EqualsAndHashCode.Exclude
    @Column(name = "USER_LOGIN")
    private String userLogin;

    @NotNull
    @EqualsAndHashCode.Exclude
    @Column(name = "OPINION")
    private String opinion;

    @NotNull
    @EqualsAndHashCode.Exclude
    @Column(name = "PUBLICATION_DATE")
    private final LocalDateTime publicationDate = LocalDateTime.now();


    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    }, fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_ID")
    private Game game;


    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    }, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;



    @Override
    public String toString() {
        return "\nGameOpinion{" +
                "\nid=" + id +
                "\ngameName='" + gameName + '\'' +
                "\nuserLogin='" + userLogin + '\'' +
                "\nopinion='" + opinion + '\'' +
                "\npublicationDate=" + publicationDate +
                "\ngame=" + game +
                "\nuser=" + user +
                '}';
    }
}
