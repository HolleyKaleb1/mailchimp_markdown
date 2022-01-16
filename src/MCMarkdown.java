import java.io.BufferedReader;
import java.io.FileReader;


enum Type {
    PARAGRAPH, HEADER , ATTRIBUTE, IGNORED
}

public class MCMarkdown {
    public static void main(String[] args){

        processMarkdownContent();
    }

    public static void processMarkdownContent(){

        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader("/Users/groovykroc/Documents/Development/MCMarkdown/src/Text.txt"));
            String line = reader.readLine();

            while (line != null){
                System.out.println(line);

                line = reader.readLine();
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }
}
//TODOS
// [x] Create txt file
// [x] Read from txt file per line
// [x] create tag class to determine what is needed for html
// [] Determine the type of html component / tag
// [] Headers, paragraphs ,attribute, Ignored,
// [] LINKS CAN BE EMBEDDED IN HEADERS OR PARAGRAPHS