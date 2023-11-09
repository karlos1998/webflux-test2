package it.letscode.webfluxtest2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class User {

    User(String name) {
        setName(name);
    }

    private String id;
    private String name;
}
