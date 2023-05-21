import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.dat"));
            ObjectInputStream  ois = new ObjectInputStream(new FileInputStream("person.dat")))
        {
            ArrayList<Person> people = new ArrayList<Person>();
            people.add(new Person("Sergey",35,"male"));
            people.add(new Person("Ivan",17,"male"));
            people.add(new Person("Marina",22,"female"));

            //Запись объекта
            for (Person person:people){
                oos.writeObject(person);
            }
            System.out.println("Written successfully!\n");

            //Чтение объекта
            for (Person person: people) {
                Person person_read = (Person)ois.readObject();
                if(person_read.getGender() == null)
                {
                    System.out.printf("Name: %s \n Age: %d \n", person_read.getName(), person_read.getAge());
                }else
                {
                    System.out.printf("Name: %s \n Age: %d \n Gender: %s \n", person_read.getName(), person_read.getAge(), person_read.getGender());
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
