package by.gsu.pms.practice2.inh.task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.function.Function;

public class Runner {
    public static void main(String[] args) throws Exception {
        String fileName = args.length > 0 ? args[0].strip() : null;
        if (fileName == null) {
            return;
        }
        ArrayList<Purchase> purchases = readPurchases(fileName);
        Purchase first = null;
        Purchase max = null;
        boolean areAllPurchasesEqual = true;
        for (Purchase purchase : purchases) {
            System.out.println(purchase);
            if (first == null) {
                first = purchase;
            } else if (areAllPurchasesEqual) {
                areAllPurchasesEqual = purchase.equals(first);
            }
            if (max == null || max.getCost() < purchase.getCost()) {
                max = purchase;
            }
        }
        if (max != null) {
            System.out.println("Max: " + max);
        }
    }
    
    private static ArrayList<Purchase> readPurchases(String fileName) throws
        Exception
    {
        ArrayList<Purchase> purchases = new ArrayList<>();
        try (BufferedReader reader = openFileStream(fileName)) {
            while (reader.ready()) {
                String line = reader.readLine();
                Purchase purchase = parseFromLine(line);
                if (purchase != null) {
                    purchases.add(purchase);
                }
            }
        }
        return purchases;
    }
    
    private static BufferedReader openFileStream(String fileName) throws
        Exception
    {
        return new BufferedReader(new FileReader(fileName));
    }
    
    private static Purchase parseFromLine(String line) {
        int tagStart = findTagStart(line);
        if (tagStart == -1) {
            return null;
        }
        int tagEnd = findTagEnd(line, tagStart);
        if (tagEnd == -1) {
            return null;
        }
        String tag = line.substring(tagStart, tagEnd);
        Function<String[], Purchase> parser = determineParser(tag);
        if (parser == null) {
            return null;
        }
        String[] rawArguments = line.substring(tagEnd).strip().split("\\s");
        ArrayList<String> cleanArguments = new ArrayList<>(rawArguments.length);
        for (String rawArgument : rawArguments) {
            if (!rawArgument.isBlank()) {
                cleanArguments.add(rawArgument.strip());
            }
        }
        return parser.apply(cleanArguments.toArray(l -> new String[l]));
    }
    
    private static Function<String[], Purchase> determineParser(String tag) {
        return switch (tag) {
            case Purchase.FILE_TAG -> Purchase::parse;
            case DiscountedPurchase.FILE_TAG -> DiscountedPurchase::parse;
            case ThresholdDiscountedPurchase.FILE_TAG ->
                ThresholdDiscountedPurchase::parse;
            default -> null;
        };
    }
    
    private static int findTagEnd(String line, int tagStart) {
        int i = tagStart;
        while (i < line.length() && !Character.isWhitespace(line.charAt(i))) {
            i++;
        }
        return i == line.length() ? -1 : i;
    }
    
    private static int findTagStart(String line) {
        int i = 0;
        while (i < line.length() && Character.isWhitespace(line.charAt(i))) {
            i++;
        }
        return i == line.length() ? -1 : i;
    }
}
