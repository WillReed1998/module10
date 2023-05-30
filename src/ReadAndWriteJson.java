import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteJson {
    public static void main(String[] args) {
        String inputFilePath = "src/file2.txt";
        String outputFilePath = "src/user.json";

        List<User> users = readUsersFromFile(inputFilePath);
        writeUsersToJsonFile(users, outputFilePath);
    }

    private static List<User> readUsersFromFile(String filePath) {
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(" ");
                String name = columns[0];
                int age = Integer.parseInt(columns[1]);

                User user = new User(name, age);
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    private static void writeUsersToJsonFile(List<User> users, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}