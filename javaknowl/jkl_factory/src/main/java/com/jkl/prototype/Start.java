package com.jkl.prototype;

public class Start {

    public static void main(String[] args) {
        ProtoA protoA = new ProtoA();
        protoA.setId(100L);
        protoA.setName("cainiao");
        protoA.setValue("大佬");
        Client client = new Client(protoA);
        ProtoA aa = (ProtoA) client.startClone();
        System.out.println(aa);

    }

}
