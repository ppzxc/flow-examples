package org.example.jpa.schema.blog.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "blog_tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ToString.Exclude
    @ManyToOne(targetEntity = Post.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ToString.Exclude
    @ManyToOne(targetEntity = HashTag.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private HashTag hashTag;
}
