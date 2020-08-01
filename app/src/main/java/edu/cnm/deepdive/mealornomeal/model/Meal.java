package edu.cnm.deepdive.mealornomeal.model;

import com.google.gson.annotations.Expose;

public class Meal {

  @Expose
  private Long id;
  @Expose
  private String name;
  @Expose
  private String instruction;
  @Expose
  private Integer prepTime;
  @Expose
  private String requirements;
//  private Long creatorId;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }

  public Integer getPrepTime() {
    return prepTime;
  }

  public void setPrepTime(Integer prepTime) {
    this.prepTime = prepTime;
  }

  public String getRequirements() {
    return requirements;
  }

  public void setRequirements(String requirements) {
    this.requirements = requirements;
  }
}
