package com.spring.json;

import com.alibaba.fastjson.JSON;
import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.spring.michael.Person;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/8
 */
public class JsonDemo {
    public static void main(String[] args) throws Exception {
        executeWithJava();
        executeWithJack();
        executeWithFastJson();
        executeWithProtoBuf();
        executeWithHessian();
    }

    private static Person init() {
        Person person = new Person();
        person.setName("Spring");
        person.setAge(18);
        person.setSuperAge(36);
        return person;
    }

    private static  void executeWithJava() throws Exception {
        Person person = init();
        FileOutputStream fos = new FileOutputStream(new File("person"));
        ObjectOutputStream oo = new ObjectOutputStream(fos);
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            oo.writeObject(person);
        }
        oo.flush();
        oo.close();
        System.out.println("Java序列化：" + (System.currentTimeMillis() - start) + "ms，总大小->" + fos.toString().getBytes().length);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("person")));
        Person person1 = (Person) ois.readObject();
        ois.close();
        System.out.println(person1);
    }

    private static void executeWithJack() throws IOException {
        Person person = init();

        ObjectMapper mapper = new ObjectMapper();
        byte[] writeBytes = null;
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            writeBytes = mapper.writeValueAsBytes(person);
        }
        System.out.println("Json序列化：" + (System.currentTimeMillis() - start) + "ms，总大小->" + writeBytes.length);

        Person person1 = mapper.readValue(writeBytes, Person.class);
        System.out.println(person1);
    }

    private static void executeWithFastJson() {
        Person person = init();

        String text = null;
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            text = JSON.toJSONString(person);
        }
        System.out.println("FastJson序列化：" + (System.currentTimeMillis() - start) + "ms，总大小->" + text.getBytes().length);

        Person person1 = JSON.parseObject(text, Person.class);
        System.out.println(person1);
    }

    private static void executeWithProtoBuf() throws IOException {
        Person person = init();

        Codec<Person> personCodec = ProtobufProxy.create(Person.class, false);
        Long start = System.currentTimeMillis();
        byte[] bytes = null;
        for (int i = 0; i < 100000; i++) {
            bytes = personCodec.encode(person);
        }
        System.out.println("ProtoBuf序列化：" + (System.currentTimeMillis() - start) + "ms，总大小->" + bytes.length);

        Person person1 = personCodec.decode(bytes);
        System.out.println(person1);
    }

    private static void executeWithHessian() throws IOException {
        Person person = init();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);
        Long start = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            ho.writeObject(person);
            if (i == 0) {
                System.out.println("Hessian序列化总大小：" + os.toByteArray().length);
            }
        }
        System.out.println("Hessian序列化：" + (System.currentTimeMillis() - start) + "ms");
        HessianInput hi = new HessianInput(new ByteArrayInputStream(os.toByteArray()));
        Person person1 = (Person) hi.readObject();
        System.out.println(person1);
    }
}
