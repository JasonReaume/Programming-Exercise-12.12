import java.util.*;
import java.io.*;

/**
 * 
 * @author Jason Reaume
 * @version 4/26/2022
 *
 *          Exercise12_12 reads a Java source file and reformats the source code to use end-of-line
 *          brace style.
 */
public class Exercise12_12 {

  /**
   * checkCommandLine checks the command line parameter usage
   * 
   * @param args: arguments passed to the command line
   */
  public static void checkCommandline(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: java Exercise12_12 filename");
      System.exit(1);
    }
  }

  /**
   * checkSourceFile checks if a sourceFile exists and returns its reference
   * 
   * @param args: arguments passed to the command line
   * @return sourceFile
   */
  public static File checkSourceFile(String[] args) {
    File sourceFile = new File(args[0]);
    if (!sourceFile.exists()) {
      System.out.println("Source file " + args[0] + " not exist");
      System.exit(2);
    }
    return sourceFile;
  }

  /**
   * parseFile reads input from a Java source file while there is still content in the source code
   * 
   * @param input: input received from a Java source file
   * @param buffer: a StringBuilder that contains the Java source file contents
   */
  public static void parseFile(Scanner input, StringBuilder buffer) {
    while (input.hasNext()) {
      String s = input.nextLine();
      String s1 = s.trim();
      reformatBraces(buffer, s, s1);
    }
  }

  /**
   * reformatBraces changes the formatting of braces in Java source code
   * 
   * @param buffer: a StringBuilder that contains the Java source file contents
   * @param s: a String that contains a line of Java source code
   * @param s1: a trimmed line of Java source code
   */
  public static void reformatBraces(StringBuilder buffer, String s, String s1) {
    if (s1.charAt(0) == '{') {
      buffer.append(" {");
      if (s1.length() > 1) {
        buffer.append("\r\n" + s.replace('{', ' '));
      }
    } else {
      buffer.append("\r\n" + s);
    }
  }

  /**
   * writeFile writes the contents of the StringBuilder buffer to the Java source file
   * 
   * @param sourceFile: contains the Java source ode
   * @param buffer: a StringBuilder that contains the Java source file contents
   * @throws FileNotFoundException: catches exception if file does not exist or is not found
   */
  public static void writeFile(File sourceFile, StringBuilder buffer) throws FileNotFoundException {
    // Write buffer into the file
    PrintWriter output = new PrintWriter(sourceFile);
    output.print(buffer.toString());
    output.close();
  }

  /** Main method */
  public static void main(String[] args) throws Exception {
    checkCommandline(args);

    File sourceFile = checkSourceFile(args);
    StringBuilder buffer = new StringBuilder();
    Scanner input = new Scanner(sourceFile);

    parseFile(input, buffer);

    input.close();
    writeFile(sourceFile, buffer);
  }
}
