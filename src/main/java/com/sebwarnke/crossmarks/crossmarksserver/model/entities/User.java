package com.sebwarnke.crossmarks.crossmarksserver.model.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Builder
@Data
@Table(
  name = "CM_USER"
)
public class User {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @JoinColumn(nullable = false)
  private String name;

  @JoinColumn(nullable = false)
  private String password;
}
