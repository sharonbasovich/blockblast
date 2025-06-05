package src;
import java.io.*;
import java.util.ArrayList;

public class Leaderboard {
    public Leaderboard(String[] newScore) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("running");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("leaderboard.txt"));
            ArrayList<String[]> scores = new ArrayList<>();
            while (reader.ready()) {
                scores.add(reader.readLine().split(","));
            }
            System.out.println("reading");
            reader.close();

            scores.add(newScore);
            scores.sort((a, b) -> Integer.parseInt(b[1]) - Integer.parseInt(a[1]));

            
            BufferedWriter writer = new BufferedWriter(new FileWriter("leaderboard.txt"));
            for (String[] strings : scores) {
                // for (String string : strings) {
                //     // System.out.println(string);
                // }
                writer.write(strings[0] + "," + strings[1]+ "\n");
            }

            System.out.println("writing");

            writer.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
