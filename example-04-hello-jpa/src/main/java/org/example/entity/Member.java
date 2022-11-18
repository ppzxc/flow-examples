package org.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {

  @Id
  private Long id;
  private String name;

  @ManyToOne(targetEntity = Team.class)
  @JoinColumn(name = "team_id")
  private Team team;
}
