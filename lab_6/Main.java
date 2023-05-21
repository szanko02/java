import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(new FileReader("src/read_file.txt"));){
            List<SiteUser> siteUsers = new ArrayList<>();
            while(scanner.hasNext()){
                siteUsers.add(new SiteUser(scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.nextInt()));
            }

            Collections.sort(siteUsers);
            for (SiteUser user : siteUsers) {
                System.out.println(user);
            }
            FileWriter writer = new FileWriter("src/new_file.txt");
            for (SiteUser user : siteUsers) {
                writer.write(user.toString());
                writer.write("\n");
            }
            writer.flush();
        } catch(FileNotFoundException fileNotFoundException) {
            System.out.println("File not found");
        } catch (IOException ioException) {
            System.out.println("ioException");
        }


    }
}