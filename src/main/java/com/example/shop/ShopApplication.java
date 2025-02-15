package com.example.shop;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);

        Dotenv dotenv = Dotenv.load();
        System.out.println("✅ DB_URL: " + dotenv.get("DB_URL"));
        System.out.println("✅ DB_USERNAME: " + dotenv.get("DB_USERNAME"));
        System.out.println("✅ DB_PASSWORD: " + dotenv.get("DB_PASSWORD"));

        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

        var object = new Info();
        object.setName("김하진");
        object.setAge(27);
        System.out.println(object.getName());
        System.out.println(object.getAge());
        object.addAge(27);
        System.out.println(object.getAge());

        var test = new Test();
        System.out.println(test.name);
        
    }
}

@Getter
@Setter
class Info {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if (age > 0 && age < 100) {
            this.age = age;
        }
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void addAge(int age) {
        this.age = age + 1;
    }

}

class Test {
    String name = "kim";
    void hello(){
        System.out.println("안녕");
    }
}
