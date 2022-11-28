package org.example.jpa.schema.blog.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "blog_reply")
public class ReplyEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "message", nullable = false, length = 128)
    private String message;

    @Column(name = "writer", nullable = false, length = 128)
    private String writer;

    @ToString.Exclude
    @ManyToOne(targetEntity = CommentEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private CommentEntity comment;
}
