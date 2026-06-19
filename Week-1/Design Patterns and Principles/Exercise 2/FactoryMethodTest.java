public class FactoryMethodTest {

    public static void main(String[] args) {

        DocumentFactory wordFactory = new WordDocumentFactory();
        System.out.println("--- Creating a Word document ---");
        wordFactory.prepareDocument();

        DocumentFactory pdfFactory = new PdfDocumentFactory();
        System.out.println("\n--- Creating a PDF document ---");
        pdfFactory.prepareDocument();

        DocumentFactory excelFactory = new ExcelDocumentFactory();
        System.out.println("\n--- Creating an Excel document ---");
        excelFactory.prepareDocument();

        System.out.println("\n--- Creating a document type chosen at runtime ---");
        String requestedType = "pdf";
        DocumentFactory chosenFactory = getFactoryForType(requestedType);
        chosenFactory.prepareDocument();
    }

    private static DocumentFactory getFactoryForType(String type) {
        switch (type.toLowerCase()) {
            case "word":
                return new WordDocumentFactory();
            case "pdf":
                return new PdfDocumentFactory();
            case "excel":
                return new ExcelDocumentFactory();
            default:
                throw new IllegalArgumentException("Unknown document type: " + type);
        }
    }
}