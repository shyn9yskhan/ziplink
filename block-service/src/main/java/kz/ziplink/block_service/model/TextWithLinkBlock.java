package kz.ziplink.block_service.model;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@TypeAlias("textWithLinkBlock")
public class TextWithLinkBlock extends Block {
    @Field("text")
    private String text;

    @Field("link")
    private String link;

    public TextWithLinkBlock(String text, String link) {
        this.text = text;
        this.link = link;
    }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public String getLink() { return link; }

    public void setLink(String link) { this.link = link; }
}