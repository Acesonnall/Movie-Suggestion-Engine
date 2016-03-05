package project1;

import java.io.FileNotFoundException;

/**
 * @author Omar Taylor
 *         <p>
 *         This class implements the project.
 */
public class Implementation {

    public static void main(String[] args) throws FileNotFoundException {
        SuggestionEngine s = new SuggestionEngine();
        s.getTopX(20000);
    }
}
