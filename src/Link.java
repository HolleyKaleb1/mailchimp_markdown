public class Link {
    private String linkText;

    private String url;

    public Link (String linkCridentials){
        int linkStart = linkCridentials.indexOf("(") + 1;
        int linkEnd = linkCridentials.indexOf(")");
        int linkTextStart =  linkCridentials.indexOf("[") + 1;
        int linkTextEnd = linkCridentials.indexOf("]");
        this.url = linkCridentials.substring(linkStart,linkEnd);
        this.linkText = linkCridentials.substring(linkTextStart,linkTextEnd);
    }

    public String getUrl() {
        return url;
    }

    public String getLinkText() {
        return linkText;
    }
}
