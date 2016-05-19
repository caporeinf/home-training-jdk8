package home.training.jdk8.lambda.robocall;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by renrodriguez on 5/9/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RoboCallTest {
    @Autowired
    RoboContact roboContact;

    @Autowired
    SearchCriteria searchCriteria;

    @Test
    public void printWithLambdaImprove() {
        List<Person> people = Person.createShortList();

        // Predicates
        Predicate<Person> allDrivers = p -> p.getAge() >= 16;
        Predicate<Person> allDraftees = p -> p.getAge() >= 18 && p.getAge() <= 25 && p.getGender() == Gender.MALE;
        Predicate<Person> allPilots = p -> p.getAge() >= 23 && p.getAge() <= 65;

        System.out.println("\n==== Test 04 ====");
        System.out.println("\n=== Calling all Drivers ===");
        roboContact.phoneContacts(people, allDrivers);

        System.out.println("\n=== Emailing all Draftees ===");
        roboContact.emailContacts(people, allDraftees);

        System.out.println("\n=== Mail all Pilots ===");
        roboContact.mailContacts(people, allPilots);

        // Mix and match becomes easy
        System.out.println("\n=== Mail all Draftees ===");
        roboContact.mailContacts(people, allDraftees);

        System.out.println("\n=== Call all Pilots ===");
        roboContact.phoneContacts(people, allPilots);
    }

    @Test
    public void printNameWithFunctionInterface() {
        List<Person> people = Person.createShortList();

        // Print Custom First Name and e-mail
        System.out.println("===Custom List===");

        for (Person person : people) {
            System.out.println(
                    person.printCustom(p -> "Name: " + p.getGivenName() + " EMail: " + p.getEmail())
            );
        }

        // Define Western and Eastern Lambdas
        Function<Person, String> westernStyle = p ->
                String.format("\nName: %s %s\nAge: %s  Gender: %s\nEMail: %s\nPhone: %s\nAddress: %s",
                        p.getGivenName(), p.getSurName(), p.getAge(), p.getGender(), p.getEmail(), p.getPhone(), p.getAddress());

        Function<Person, String> easternStyle = p ->
                String.format("\nName: %s %s\nAge: %s  Gender: %s\nEMail: %s\nPhone: %s\nAddress: %s",
                        p.getSurName(), p.getGivenName(), p.getAge(), p.getGender(), p.getEmail(), p.getPhone(), p.getAddress());

        // Print Western List
        System.out.println("\n===Western List===");
        for (Person person : people) {
            System.out.println(
                    person.printCustom(westernStyle)
            );
        }

        // Print Eastern List
        System.out.println("\n===Eastern List===");
        for (Person person : people) {
            System.out.println(
                    person.printCustom(easternStyle)
            );
        }

    }

    @Test
    public void customPredicateInterfaceShouldWorkWell() {
        Person renny = Person.builder().givenName("Renny").build();
        CustomPredicateInterface<Person> p = person -> "Renny".equals(person.getGivenName());
        Assert.assertTrue(p.test(renny));
    }

    @Test
    public void customFunctionInterfaceShouldWorkWell() {
        Person renny = Person.builder().givenName("Renny").build();
        CustomFunctionInterface<Person, String> personStringCustomFunctionInterface = person1 -> String.format("Given name: %s", person1.getGivenName());
        Assert.assertThat("Given name: Renny", Is.is(personStringCustomFunctionInterface.exchange(renny)));
    }

    @Test
    public void waysForEachElementOfAnyCollection() {
        List<Person> people = Person.createShortList();
        System.out.println("\n=== Western Phone List ===");
        people.forEach(person -> person.printWesternName());

        System.out.println("\n=== Eastern Phone List ===");
        people.forEach(Person::printEasternName);

        System.out.println("\n=== Custom Phone List ===");
        people.forEach(person -> System.out.println(person.printCustom(person1 -> String.format("Name: %s EMail: %s",
                person1.getGivenName(), person1.getEmail()))));
    }

    @Test
    public void filterForCollectionsWorkWell() {
        List<Person> people = Person.createShortList();
        System.out.println("\n=== Western Pilot Phone List ===");
        people.stream().filter(searchCriteria.getCriteria(SearchCriteriaTypes.ALLPILOTS)).forEach(Person::printWesternName);

        System.out.println("\n=== Eastern Draftee Phone List ===");
        people.stream().filter(searchCriteria.getCriteria(SearchCriteriaTypes.ALLDRAFTEES)).forEach(Person::printEasternName);
    }

    @Test
    public void collectionFilteredShouldBeNotNull() {
        List<Person> people = Person.createShortList();
        // Make a new list after filtering.
        List<Person> pilotList = people
                .stream()
                .filter(searchCriteria.getCriteria(SearchCriteriaTypes.ALLPILOTS))
                .collect(Collectors.toList());
        Assert.assertNotNull(pilotList);
    }

    @Test
    public void mapMethodOfFilterShouldWorkWell() {

        List<Person> people = Person.createShortList();

        // Calc average age of pilots old style
        System.out.println("== Calc Old Style ==");
        int sum = 0;
        int count = 0;

        for (Person p : people) {
            if (p.getAge() >= 23 && p.getAge() <= 65) {
                sum = sum + p.getAge();
                count++;
            }
        }

        long average = sum / count;
        System.out.println("Total Ages: " + sum);
        System.out.println("Average Age: " + average);


        // Get sum of ages
        System.out.println("\n== Calc New Style ==");
        long totalAge = people
                .stream()
                .filter(searchCriteria.getCriteria(SearchCriteriaTypes.ALLPILOTS))
                .mapToInt(p -> p.getAge())
                .sum();

        // Get average of ages
        OptionalDouble averageAge = people
                .parallelStream()
                .filter(searchCriteria.getCriteria(SearchCriteriaTypes.ALLPILOTS))
                .mapToDouble(p -> p.getAge())
                .average();

        System.out.println("Total Ages: " + totalAge);
        System.out.println("Average Age: " + averageAge.getAsDouble());
    }

}
