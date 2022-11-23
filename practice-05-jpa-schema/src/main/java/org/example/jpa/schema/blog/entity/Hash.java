package org.example.jpa.schema.blog.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "blog_tag")
public class Hash {
    @Id
    private Long id;
}
