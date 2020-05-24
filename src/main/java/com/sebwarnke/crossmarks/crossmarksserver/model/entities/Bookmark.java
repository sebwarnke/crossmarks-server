package com.sebwarnke.crossmarks.crossmarksserver.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Builder
@Table(
  name = "CM_BOOKMARKS"
)

public class Bookmark {

  /* Primary Key */
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  /* Human Readable Name of Bookmark */
  @JoinColumn(nullable = false)
  private String name;

  /* Bookmark's URL */
  @JoinColumn(nullable = false)
  private String url;

  /* DateTime of Bookmark's Initial Creation */
  @JoinColumn(nullable = false)
  private LocalDateTime createdAt;

  /* DateTime of when Bookmark was visited last */
  private LocalDateTime lastVisitedAt;

  /* Number of Visits */
  private Integer visitCount;

  /* Optional Description */
  private String description;
}
