package models.dto;

public class Student {
  private Long id;
  private String name;
  private Long age;
  private Long group_id;
  private Long user_id;

  public Student(Long id, String name, Long age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

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

  public Long getAge() {
    return age;
  }

  public void setAge(Long age) {
    this.age = age;
  }

  public Long getGroup_id() {
    return group_id;
  }

  public void setGroup_id(Long group_id) {
    this.group_id = group_id;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }
}
