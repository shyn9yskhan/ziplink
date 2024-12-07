package kz.ziplink.profile_content_aggregator_service.model;

public class TextWithLinkBlock extends Block {

    private String text;
    private String link;

    public TextWithLinkBlock() {}

    public TextWithLinkBlock(String text, String link) {
        this.text = text;
        this.link = link;
    }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public String getLink() { return link; }

    public void setLink(String link) { this.link = link; }
}