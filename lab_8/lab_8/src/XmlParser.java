import org.w3c.dom.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.*;
import javax.xml.stream.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {
    public static void main(String[] args) {
        try {
            // Получение XML-документа по ссылке
            URL url = new URL("https://www.nbrb.by/Services/XmlExRates.aspx");

            // Использование DOM парсера
            List<Currency> currenciesDOM = parseWithDOM(url);
            System.out.println("DOM парсер:");
            for (Currency currency : currenciesDOM) {
                System.out.println(currency);
            }
            System.out.println();

            // Использование SAX парсера
            List<Currency> currenciesSAX = parseWithSAX(url);
            System.out.println("SAX парсер:");
            for (Currency currency : currenciesSAX) {
                System.out.println(currency);
            }
            System.out.println();

            // Использование StAX парсера
            List<Currency> currenciesStAX = parseWithStAX(url);
            System.out.println("StAX парсер:");
            for (Currency currency : currenciesStAX) {
                System.out.println(currency);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Currency> parseWithDOM(URL url) throws ParserConfigurationException, IOException, SAXException {
        List<Currency> currencies = new ArrayList<>();
        InputStream stream = url.openStream();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(stream);

        NodeList currencyNodes = document.getElementsByTagName("Currency");
        for (int i = 0; i < currencyNodes.getLength(); i++) {
            Element currencyElement = (Element) currencyNodes.item(i);

            Currency currency = new Currency();
            currency.setId(currencyElement.getAttribute("Id"));
            currency.setNumCode(getElementValue(currencyElement, "NumCode"));
            currency.setCharCode(getElementValue(currencyElement, "CharCode"));
            currency.setScale(Integer.parseInt(getElementValue(currencyElement, "Scale")));
            currency.setName(getElementValue(currencyElement, "Name"));
            currency.setRate(Double.parseDouble(getElementValue(currencyElement, "Rate")));

            currencies.add(currency);
        }
        stream.close();
        return currencies;
    }

    private static String getElementValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element) node;
                Node textNode = childElement.getFirstChild();
                if (textNode != null) {
                    return textNode.getNodeValue();
                }
            }
        }
        return "";
    }

    private static List<Currency> parseWithSAX(URL url) throws ParserConfigurationException, SAXException, IOException {
        List<Currency> currencies = new ArrayList<>();
        InputStream stream = url.openStream();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        DefaultHandler handler = new DefaultHandler() {
            private Currency currentCurrency;
            private StringBuilder currentText;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                if (qName.equals("Currency")) {
                    currentCurrency = new Currency();
                    currentCurrency.setId(attributes.getValue("Id"));
                }
                currentText = new StringBuilder();
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                currentText.append(ch, start, length);
            }

            @Override
            public void endElement(String uri, String localName, String qName) {
                switch (qName) {
                    case "NumCode" -> currentCurrency.setNumCode(currentText.toString());
                    case "CharCode" -> currentCurrency.setCharCode(currentText.toString());
                    case "Scale" -> currentCurrency.setScale(Integer.parseInt(currentText.toString()));
                    case "Name" -> currentCurrency.setName(currentText.toString());
                    case "Rate" -> currentCurrency.setRate(Double.parseDouble(currentText.toString()));
                    case "Currency" -> {
                        currencies.add(currentCurrency);
                        currentCurrency = null;
                    }
                }
            }
        };

        saxParser.parse(stream, handler);
        stream.close();
        return currencies;
    }
    private static List<Currency> parseWithStAX(URL url) throws XMLStreamException {
        List<Currency> currencies = new ArrayList<>();
        try (InputStream stream = url.openStream()) {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(stream);

            Currency currentCurrency = null;
            String currentElement = "";
            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if (reader.getLocalName().equals("Currency")) {
                            currentCurrency = new Currency();
                            currentCurrency.setId(reader.getAttributeValue(null, "Id"));
                        }
                        currentElement = reader.getLocalName();
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        String text = reader.getText().trim();

                        // Обработка содержимого элемента
                        if (!text.isEmpty()) {
                            switch (currentElement) {
                                case "NumCode" -> currentCurrency.setNumCode(text);
                                case "CharCode" -> currentCurrency.setCharCode(text);
                                case "Scale" -> currentCurrency.setScale(Integer.parseInt(text));
                                case "Name" -> currentCurrency.setName(text);
                                case "Rate" -> currentCurrency.setRate(Double.parseDouble(text));
                            }
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        // Обработка завершающего элемента
                        if (reader.getLocalName().equals("Currency")) {
                            currencies.add(currentCurrency);
                            currentCurrency = null;
                        }
                        break;
                }
            }
        } catch (IOException e) {
            // Обработка исключения
            e.printStackTrace();
        }
        return currencies;
    }
}

