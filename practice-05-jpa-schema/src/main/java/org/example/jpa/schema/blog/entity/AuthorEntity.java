package org.example.jpa.schema.blog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "blog_author")
public class AuthorEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @ToString.Exclude
    @ManyToOne(targetEntity = MemberEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity member;

    @ToString.Exclude
    @OneToMany(targetEntity = PostEntity.class, fetch = FetchType.LAZY, mappedBy = "author", orphanRemoval = true)
    private Set<PostEntity> posts;
}
