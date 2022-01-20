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
        if (currentLine.contains("[")){
            String ret =  preProcessForChildrenLinks(innerHtml);
            return"<h" + headerCount  + ">" + ret + "</h" + headerCount  + ">";
        }
        String htmlComponent = "<h" + headerCount  + ">" + innerHtml + "</h" + headerCount  + ">";
        return htmlComponent;
    }

    public String processParagraph(String currentLine){
        if (currentLine.equals("Blank line")){
            return currentLine;
        }
        if (currentLine.contains("[")){
           String ret =  preProcessForChildrenLinks(currentLine);
           return"<p>" + ret + "</p>";
        }
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
    public String preProcessForChildrenLinks(String  currentLine){
        String ret = "";
        int startOfLinkTextIndex = -1;
        int endOfLinkTextIndex = -1;
        int startOfLinkIndex = -1;
        int endOfLinkIndex = -1;

        boolean currLink = false;
        for(int i =0; i < currentLine.length();i++){
                if (currentLine.charAt(i) == '['){
                    startOfLinkTextIndex = i;
                }
                if (currentLine.charAt(i) == ']'){
                    endOfLinkTextIndex = i;
                }
                if (currentLine.charAt(i) == '('){
                    startOfLinkIndex = i;
                }
                if (currentLine.charAt(i) == ')'){
                    endOfLinkIndex = i;
                }
            if (startOfLinkTextIndex != -1 && endOfLinkTextIndex != -1 && startOfLinkIndex != -1 && endOfLinkIndex != -1){
               String processedLink = processLink(currentLine.substring(startOfLinkTextIndex,endOfLinkIndex+1));
               ret = ret + processedLink;
               startOfLinkTextIndex = -1;
               endOfLinkTextIndex = -1;
               startOfLinkIndex = -1;
               endOfLinkIndex = -1;
            }else if (startOfLinkTextIndex == -1 && endOfLinkTextIndex == -1 && startOfLinkIndex == -1 && endOfLinkIndex == -1){
                ret = ret + currentLine.charAt(i);
            }
        }
        return ret;
    }
}
