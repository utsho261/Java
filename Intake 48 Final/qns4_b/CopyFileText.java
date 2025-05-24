package qns4_b;
/*
Write a Java program that copies the contents of one text file to another. Use
BufferedReader and Bufferedwriter.
 */
import java.io.*;

public class CopyFileText {
    public static void main(String[] args) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader("source.txt"));
                BufferedWriter writer = new BufferedWriter(new FileWriter("destination.txt"))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
