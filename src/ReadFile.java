import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class ReadFile {

    private InputStream inputStream;

    public ReadFile(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ReadFile() throws FileNotFoundException {
            inputStream = new FileInputStream("src/fileToRead.txt");
    }

    public List<String> readFromInputStream()
            throws IOException {
        List<String> lines = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
