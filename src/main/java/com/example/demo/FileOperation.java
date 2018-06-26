package com.example.demo;

public enum FileOperation {
  READ("read"),
  WRITE("write"),
  DELETE("delete"),
  READ_ACL("read_acl"),
  WRITE_ACL("write_acl");

  private String  name;

  FileOperation(String name){
    this.name = name;
  }

  @Override
  public String toString(){
    return name;
  }
}