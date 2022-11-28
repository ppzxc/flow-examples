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
@Entity(name = "blog_hash_tag")
public class HashTagEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @ToString.Exclude
    @OneToMany(targetEntity = TagEntity.class, fetch = FetchType.LAZY, mappedBy = "hashTag", orphanRemoval = true)
    private Set<TagEntity> tags;
}
