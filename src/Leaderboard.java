package src;

import java.io.*;
import java.util.ArrayList;

public class Leaderboard {
    public Leaderboard(String[] newScore) {
        try {
            // read the leaderboard file and save all the data to an ArrayList
            BufferedReader reader = new BufferedReader(new FileReader("leaderboard.txt"));
            ArrayList<String[]> scores = new ArrayList<>();
            while (reader.ready()) {
                scores.add(reader.readLine().split(","));
            }
            System.out.println("reading");
            reader.close();

            // add the new score passed (in the form "name,score") to the array list
            scores.add(newScore);

            // sort the array list by numerical score
            scores.sort((a, b) -> Integer.parseInt(b[1]) - Integer.parseInt(a[1]));

            // update the file with the scores that now include the new one in the correct spot
            BufferedWriter writer = new BufferedWriter(new FileWriter("leaderboard.txt"));
            for (String[] strings : scores) {
                writer.write(strings[0] + "," + strings[1] + "\n");
            }
            writer.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
