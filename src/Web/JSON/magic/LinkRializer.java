package src.JSON.magic;

import com.google.gson.Gson;
import src.JSON.model.Link;

public class LinkRializer {

    private static final Gson GSON = new Gson();

    public static Link deserialize(final String json) {
        return GSON.fromJson(json, Link.class);
    }
}
