package src.JSON.model;

public class Link {

    private final String fullUrl;
    private final String code;
    private final ExpiryDate expiryDate;

    public Link(final String fullUrl, final String code, final ExpiryDate expiryDate) {
        this.fullUrl = fullUrl;
        this.code = code;
        this.expiryDate = expiryDate;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public String getCode() {
        return code;
    }

    public ExpiryDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Link link = (Link) o;

        if(fullUrl != null){
            if(!(this.fullUrl.equals(link.getFullUrl()))) return false;
        } else if(!(link.getFullUrl() == null)) return false;

        if(code != null){
            if(!(this.code.equals(link.getCode()))) return false;
        } else if(!(link.getCode() == null)) return false;

        if(expiryDate != null){
            return this.expiryDate.equals(link.getExpiryDate());
        } else return link.getExpiryDate() == null;
    }

    @Override
    public String toString() {
        return "Link:\n" +
                "fullUrl = " + fullUrl +
                ", code = " + code +
                ", expiryDate = " + expiryDate;
    }

    public static class ExpiryDate {

        private final int month;
        private final int year;

        public ExpiryDate(int month, int year) {
            this.month = month;
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public int getYear() {
            return year;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final ExpiryDate shortDate = (ExpiryDate) o;

            return month == shortDate.getMonth() && year == shortDate.getYear();
        }

        @Override
        public String toString() {
            return "{" +
                    "month = " + month +
                    ", year = " + year +
                    '}';
        }
    }
}
