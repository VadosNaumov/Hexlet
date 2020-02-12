package src.JSON;

import src.JSON.magic.LinkRializer;
import src.JSON.model.Link;

public class Main {

    public static void main(String[] args) {
        testSerialization(new Link(null, null, null));
        testSerialization(new Link("someFullUrl", "asfdaf1", new Link.ExpiryDate(1, 1)));
        testSerialization(new Link("someFullUrl", "asfdaf2", new Link.ExpiryDate(10, 2)));
        testSerialization(new Link("someFullUrl", null, new Link.ExpiryDate(10, 3)));
        testSerialization(new Link(null, "asfdaf4", new Link.ExpiryDate(10, 4)));
        testSerialization(new Link("someFullUrl", "asfdaf5", null));
        System.out.println("All tests is OK!");

    }

    private static void testSerialization(final Link link) {
        final String json = LinkSerializer.toJson(link);
        //System.out.println("1: " + json);
        final Link afterSerialization;
        try {
            afterSerialization = LinkRializer.deserialize(json);
        } catch (Exception e){
            throw new RuntimeException(
                    String.format("Original link:\n %s\nWas serialized to:\n %s", link.toString(), json));
        }

        if (!link.equals(afterSerialization)) {
            throw new RuntimeException(
                    String.format("Original link:\n %s\nWas serialized to:\n %s", link.toString(), json));
        }
    }
}

