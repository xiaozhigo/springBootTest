package com.example.springboottest.test;

import java.util.List;

public class BuilderTest {

    private int id;

    private String name;

    private List<String> list;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getList() {
        return list;
    }

    public BuilderTest() {
    }

    private BuilderTest(UserBuilder userBuilder) {
        this.id = userBuilder.id;
        this.name = userBuilder.name;
        this.list = userBuilder.list;
    }

    public static class UserBuilder{

        private int id;

        private String name;

        private List<String> list;

        public UserBuilder() {
        }

        public UserBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder setName(String name){
            this.name = name;
            return this;
        }

        public UserBuilder setList(List<String> list){
            this.list = list;
            return this;
        }

        public BuilderTest bulider(){
            BuilderTest test = new BuilderTest(this);
            return test;
        }
    }
}
