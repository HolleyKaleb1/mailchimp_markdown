public class HTMLConverter {

    public String checkForTags (String line){
        if (line.charAt(0) == '#'){
            return "HEADER";
        } else if (isLetter(line.charAt(0))){
            return "PARAGRAPH";
        } else if (line.charAt(0) == '['){
            return "ATTRIBUTE";
        } else if (line.equals("Blank Line")){
            return "IGNORED";
        }
      return "ORIGINAL";
    }

    public String processHeader(String currentLine){
        int headerCount = currentLine.indexOf(" ");
        String innerHtml = currentLine.substring(headerCount+1);
        String htmlComponent = "<h" + headerCount  + ">" + innerHtml + "</h" + headerCount  + ">";
        return htmlComponent;
    }

    public String processParagraph(String currentLine){
        return "<p>" + currentLine + "</p>";
    }
    public String processLink(String currentLine){
        Link link = new Link(currentLine);
        return "<a href=\"" + link.getUrl() + "\">" + link.getLinkText() + "</a>";
    }
    private static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z');
    }
}
