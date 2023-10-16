import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File fichero = new File("esdla.json");
        JsonWriter writer = new JsonWriter(new FileWriter(fichero));

    }
}