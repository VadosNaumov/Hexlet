package src.JSON;

import src.JSON.model.Link;

public class LinkSerializer {

    public static String toJson(final Link link) {

        String fU;
        if (link.getFullUrl() != null) {
            fU = "\"" + link.getFullUrl() + "\"";
        } else fU = null;

        String code;
        if (link.getCode() != null) {
            code = "\"" + link.getCode() + "\"";
        } else code = null;

        String exD;
        if (link.getExpiryDate() != null) {
            exD = "{\"month\":" + link.getExpiryDate().getMonth() + ","
                    + "\"year\":" + link.getExpiryDate().getYear() + "}";
        } else exD = null;

        StringBuilder sb = new StringBuilder();
        sb = sb.append("{\"fullUrl\":" + fU + ","
                + "\"code\":" + code + ","
                + "\"expiryDate\":" + exD
                + "}");
        return sb.toString();

    }
}

