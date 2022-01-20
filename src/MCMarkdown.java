import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class MCMarkdown {
    public static void main(String[] args){

        processMarkdownContent();
    }

    public static void processMarkdownContent(){

        BufferedReader reader;

        try{
            ArrayList<String> markdownData = new ArrayList<>();
            // The file name should be changed to wherever the file is on your local computer.
            reader = new BufferedReader(new FileReader("/Users/groovykroc/Documents/Development/MCMarkdown/src/Text.txt"));
            HTMLConverter htmlConverter = new HTMLConverter();
            ArrayList<String> convertedHTML = new ArrayList<String>();

            String line = reader.readLine();
            markdownData.add(line);
            while (line != null){
                line = reader.readLine();
                markdownData.add(line);
            }

            for (int i = 0; i < markdownData.size() - 1;i++) {
                String currentLine = markdownData.get(i);
                String type = htmlConverter.checkForTags(currentLine);
                switch (type){
                    case "HEADER":
                        convertedHTML.add(htmlConverter.processHeader(currentLine));
                        break;
                    case "PARAGRAPH":
                        convertedHTML.add(htmlConverter.processParagraph(currentLine));
                        break;
                    case "ATTRIBUTE":
                        convertedHTML.add(htmlConverter.processLink( currentLine));
                        break;
                    case "ORIGINAL":
                        convertedHTML.add(currentLine);
                        break;
                    default:
                        convertedHTML.add("This string is not supported by markdown");
                }
            }

            for (int i = 0; i < convertedHTML.size();i++){
                System.out.println(convertedHTML.get(i));
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
// [x] Determine the type of html component / tags
// [x] Handle Identifying headers
// [x] Headers, paragraphs ,attribute, Ignored,
// [x] LINKS CAN BE EMBEDDED IN HEADERS OR PARAGRAPHS