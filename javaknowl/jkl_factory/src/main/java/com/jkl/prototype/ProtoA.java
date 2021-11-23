package com.jkl.prototype;

public class ProtoA implements Proto {

    private Long id;
    private String name;
    private String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ProtoA{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public Proto clone() {
        ProtoA protoA = new ProtoA();
        protoA.setId(this.id);
        protoA.setName(this.name);
        protoA.setValue(this.value);
        return protoA;
    }
}
