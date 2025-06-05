package qns1_b;
/*
There is an input file named "input.txt". Read all the strings from that file.
Now calculate the length of all strings and write them to "output.txt" file.
                ,________________________,
                | Input.txt | Output.txt |
                |-----------|------------|
                | Mango     |5           |
                | Apple     |5           |
                |___________|____________|
 */
import java.io.*;
public class FileStringLength {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {

            String line;
            while ((line = br.readLine()) != null) {
                bw.write(String.valueOf(line.length()));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
