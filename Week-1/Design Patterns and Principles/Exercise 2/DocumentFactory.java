public abstract class DocumentFactory {

    public abstract Document createDocument();

    public Document prepareDocument() {
        Document document = createDocument();
        document.open();
        document.save();
        return document;
    }
}