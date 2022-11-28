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
@Entity(name = "blog_member")
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 64)
    private String username;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "email", nullable = false, length = 64)
    private String email;

    @ToString.Exclude
    @OneToMany(targetEntity = AuthorEntity.class, fetch = FetchType.LAZY, mappedBy = "member", orphanRemoval = true)
    private Set<AuthorEntity> authors;
}
