package home.training.jdk8.lambda.robocall;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author MikeW
 */
@Builder
public class Person {
    @Getter
    private String givenName;
    @Getter
    private String surName;
    @Getter
    private int age;
    @Getter
    private Gender gender;
    @Getter
    private String email;
    @Getter
    private String phone;
    @Getter
    private String address;


    public static List<Person> createShortList() {
        List<Person> people = new ArrayList<>();

        people.add(
                Person.builder()
                        .givenName("Bob")
                        .surName("Baker")
                        .age(21)
                        .gender(Gender.MALE)
                        .email("bob.baker@example.com")
                        .phone("201-121-4678")
                        .address("44 4th St, Smallville, KS 12333")
                        .build()
        );

        people.add(
                Person.builder()
                        .givenName("Jane")
                        .surName("Doe")
                        .age(25)
                        .gender(Gender.FEMALE)
                        .email("jane.doe@example.com")
                        .phone("202-123-4678")
                        .address("33 3rd St, Smallville, KS 12333")
                        .build()
        );

        people.add(
                Person.builder()
                        .givenName("John")
                        .surName("Doe")
                        .age(25)
                        .gender(Gender.MALE)
                        .email("john.doe@example.com")
                        .phone("202-123-4678")
                        .address("33 3rd St, Smallville, KS 12333")
                        .build()
        );

        people.add(
                Person.builder()
                        .givenName("James")
                        .surName("Johnson")
                        .age(45)
                        .gender(Gender.MALE)
                        .email("james.johnson@example.com")
                        .phone("333-456-1233")
                        .address("201 2nd St, New York, NY 12111")
                        .build()
        );

        people.add(
                Person.builder()
                        .givenName("Joe")
                        .surName("Bailey")
                        .age(67)
                        .gender(Gender.MALE)
                        .email("joebob.bailey@example.com")
                        .phone("112-111-1111")
                        .address("111 1st St, Town, CA 11111")
                        .build()
        );

        people.add(
                Person.builder()
                        .givenName("Phil")
                        .surName("Smith")
                        .age(55)
                        .gender(Gender.MALE)
                        .email("phil.smith@examp;e.com")
                        .phone("222-33-1234")
                        .address("22 2nd St, New Park, CO 222333")
                        .build()
        );

        people.add(
                Person.builder()
                        .givenName("Betty")
                        .surName("Jones")
                        .age(85)
                        .gender(Gender.FEMALE)
                        .email("betty.jones@example.com")
                        .phone("211-33-1234")
                        .address("22 4th St, New Park, CO 222333")
                        .build()
        );


        return people;
    }

    public String printCustom(Function<Person, String> f) {
        return f.apply(this);
    }
    
    public void printWesternName(){
        System.out.println(
                String.format("\nName: %s %s\nAge: %s  Gender: %s\nEMail: %s\nPhone: %s\nAddress: %s",
                        this.getGivenName(), this.getSurName(), this.getAge(), this.getGender(),this.getEmail(),this.getPhone(),this.getAddress()));
    }
    
    public void printEasternName(){
        System.out.println(
                String.format("\nName: %s %s\nAge: %s  Gender: %s\nEMail: %s\nPhone: %s\nAddress: %s",
                        this.getSurName(), this.getGivenName(), this.getAge(), this.getGender(),this.getEmail(),this.getPhone(),this.getAddress()));
    }

}
