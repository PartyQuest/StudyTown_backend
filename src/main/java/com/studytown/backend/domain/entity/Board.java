package com.studytown.backend.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 해당 클래스는 JPA를 활용한 데이터클래스, 게시글 데이터 저장용도
 *
 * @author Song Min Gyu
 * @version 1.0, 엔티티 최초작성
 *
 * */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "tb_board")
public class Board extends DateAudit{
    /* Database Reference ID*/
    @Id
    @GeneratedValue
    @Column
    private Long id;

    /* Board title, content*/
    @NotNull private String title;
    @NotNull private String content;

    /* This attribute is writer column. Using join userEntity*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;

    /* comment column, self reference join*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "board", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Board> boards = new ArrayList<>();

}
