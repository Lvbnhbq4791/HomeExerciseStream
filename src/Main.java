import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long listOfJuveniles = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Кол-во несовершенолетних : " + listOfJuveniles + " человек");

        List<String> listOfConscripts = persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .filter(person -> (person.getSex()).equals(Sex.MAN))
                .map(Person::getFamily).toList();
        System.out.println("Список призывников по фамилии:");
        StringBuilder cBtext = new StringBuilder();
        int ch = 1;
        for (String text : listOfConscripts) {
            if (ch % 15 != 0) {
                cBtext.append(text).append(".");
            } else {
                cBtext.append("\n");
            }
            ch++;
        }
        System.out.println(cBtext);

        List<Person> listOfHealthy = persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 65)
                .filter(person -> (person.getEducation()).equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily)).toList();
        System.out.println("Список работоспособных с высшим образованием:");
        StringBuilder fBtext = new StringBuilder();
        for (Person text : listOfHealthy) {
            fBtext.append(text).append("\n");
        }
        System.out.println(fBtext);
    }
}
