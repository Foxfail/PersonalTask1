import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

public class TestMath extends TestCase {

    private final ArrayList<String> linesFromFile = new ArrayList<>();

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        File data_file = new File("src/test/resources/data.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(data_file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                linesFromFile.add(line);
            }
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        linesFromFile.clear();
    }

    public void testMath() {

    }
}
