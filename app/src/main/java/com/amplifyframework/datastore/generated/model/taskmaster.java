package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the taskmaster type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "taskmasters")
@Index(name = "taskmasterItem", fields = {"teamID"})
public final class taskmaster implements Model {
  public static final QueryField ID = field("taskmaster", "id");
  public static final QueryField TITLE = field("taskmaster", "title");
  public static final QueryField BODY = field("taskmaster", "body");
  public static final QueryField STATE = field("taskmaster", "state");
  public static final QueryField TEAMS = field("taskmaster", "teamID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="String", isRequired = true) String body;
  private final @ModelField(targetType="String", isRequired = true) String state;
  private final @ModelField(targetType="team", isRequired = true) @BelongsTo(targetName = "teamID", type = team.class) team teams;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getTitle() {
      return title;
  }
  
  public String getBody() {
      return body;
  }
  
  public String getState() {
      return state;
  }
  
  public team getTeams() {
      return teams;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private taskmaster(String id, String title, String body, String state, team teams) {
    this.id = id;
    this.title = title;
    this.body = body;
    this.state = state;
    this.teams = teams;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      taskmaster taskmaster = (taskmaster) obj;
      return ObjectsCompat.equals(getId(), taskmaster.getId()) &&
              ObjectsCompat.equals(getTitle(), taskmaster.getTitle()) &&
              ObjectsCompat.equals(getBody(), taskmaster.getBody()) &&
              ObjectsCompat.equals(getState(), taskmaster.getState()) &&
              ObjectsCompat.equals(getTeams(), taskmaster.getTeams()) &&
              ObjectsCompat.equals(getCreatedAt(), taskmaster.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), taskmaster.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTitle())
      .append(getBody())
      .append(getState())
      .append(getTeams())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("taskmaster {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("body=" + String.valueOf(getBody()) + ", ")
      .append("state=" + String.valueOf(getState()) + ", ")
      .append("teams=" + String.valueOf(getTeams()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TitleStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static taskmaster justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new taskmaster(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      title,
      body,
      state,
      teams);
  }
  public interface TitleStep {
    BodyStep title(String title);
  }
  

  public interface BodyStep {
    StateStep body(String body);
  }
  

  public interface StateStep {
    TeamsStep state(String state);
  }
  

  public interface TeamsStep {
    BuildStep teams(team teams);
  }
  

  public interface BuildStep {
    taskmaster build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements TitleStep, BodyStep, StateStep, TeamsStep, BuildStep {
    private String id;
    private String title;
    private String body;
    private String state;
    private team teams;
    @Override
     public taskmaster build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new taskmaster(
          id,
          title,
          body,
          state,
          teams);
    }
    
    @Override
     public BodyStep title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }
    
    @Override
     public StateStep body(String body) {
        Objects.requireNonNull(body);
        this.body = body;
        return this;
    }
    
    @Override
     public TeamsStep state(String state) {
        Objects.requireNonNull(state);
        this.state = state;
        return this;
    }
    
    @Override
     public BuildStep teams(team teams) {
        Objects.requireNonNull(teams);
        this.teams = teams;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String title, String body, String state, team teams) {
      super.id(id);
      super.title(title)
        .body(body)
        .state(state)
        .teams(teams);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder body(String body) {
      return (CopyOfBuilder) super.body(body);
    }
    
    @Override
     public CopyOfBuilder state(String state) {
      return (CopyOfBuilder) super.state(state);
    }
    
    @Override
     public CopyOfBuilder teams(team teams) {
      return (CopyOfBuilder) super.teams(teams);
    }
  }
  
}
