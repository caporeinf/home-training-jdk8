package home.training.jdk8.lambda.robocall;

import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.function.Predicate;

/**
 * Created by renrodriguez on 5/10/2016.
 */
@Component
public class SearchCriteria {
    private final EnumMap<SearchCriteriaTypes,Predicate<Person>> searchMap = new EnumMap<>(SearchCriteriaTypes.class);

    public SearchCriteria() {
        super();
        initSearchMap();

    }

    private void initSearchMap() {
        Predicate<Person> allDrivers = person -> person.getAge()>=16;
        Predicate<Person> allDraftees = person -> person.getAge()>=18 && person.getAge()<=25 && person.getGender() == Gender.MALE;
        Predicate<Person> allPilots = person -> person.getAge()>=23 && person.getAge()<=65;

        searchMap.put(SearchCriteriaTypes.ALLDRIVERS,allDrivers);
        searchMap.put(SearchCriteriaTypes.ALLDRAFTEES,allDraftees);
        searchMap.put(SearchCriteriaTypes.ALLPILOTS,allPilots);
    }

    public Predicate<Person> getCriteria(SearchCriteriaTypes searchCriteriaType){
        Predicate<Person> target;
        target = searchMap.get(searchCriteriaType);
        if(target == null){
            System.out.println("Search criteria not found");
            System.exit(1);
        }
        return target;
    }
}
