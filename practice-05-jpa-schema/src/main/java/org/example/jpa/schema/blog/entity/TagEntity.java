package org.example.jpa.schema.blog.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "blog_tag")
public class TagEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ToString.Exclude
    @ManyToOne(targetEntity = PostEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @ToString.Exclude
    @ManyToOne(targetEntity = HashTagEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private HashTagEntity hashTag;
}
