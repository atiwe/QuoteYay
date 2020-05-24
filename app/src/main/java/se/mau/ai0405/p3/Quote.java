package se.mau.ai0405.p3;

/**
 * Entity class for a quote question.
 *
 * @author Antoine Rebelo
 * @author Sara Dalvig
 */
public class Quote {
    private String quote = "";
    private String correctAuthor = "";
    private String fakeAuthor1 = "";
    private String fakeAuthor2 = "";
    private String fakeAuthor3 = "";

    /**
     * Returns the quote.
     *
     * @return The quote
     */
    public String getQuote1() {
        return quote;
    }

    /**
     * Sets the quote.
     *
     * @param quote1 The quote
     */
    public void setQuote1(String quote1) {
        this.quote = quote1;
    }

    /**
     * Returns the correct author of the quote.
     *
     * @return The correct author
     */
    public String getCorrectAuthor() {
        return correctAuthor;
    }

    /**
     * Sets the correct author of the quote.
     *
     * @param correctAuthor The correct author
     */
    public void setCorrectAuthor(String correctAuthor) {
        this.correctAuthor = correctAuthor;
    }

    /**
     * Returns the first wrong author of the quote.
     *
     * @return The first wrong author
     */
    public String getFakeAuthor1() {
        return fakeAuthor1;
    }

    /**
     * Sets the first wrong author of the quote.
     *
     * @param fakeAuthor1 The first wrong author
     */
    public void setFakeAuthor1(String fakeAuthor1) {
        this.fakeAuthor1 = fakeAuthor1;
    }

    /**
     * Returns the second wrong author of the quote.
     *
     * @return The second wrong author
     */
    public String getFakeAuthor2() {
        return fakeAuthor2;
    }

    /**
     * Sets the second wrong author of the quote.
     *
     * @param fakeAuthor2 The second wrong author
     */
    public void setFakeAuthor2(String fakeAuthor2) {
        this.fakeAuthor2 = fakeAuthor2;
    }

    /**
     * Returns the third wrong author of the quote.
     *
     * @return The third wrong author
     */
    public String getFakeAuthor3() {
        return fakeAuthor3;
    }

    /**
     * Sets the third wrong author of the quote.
     *
     * @param fakeAuthor3 The third wrong author
     */
    public void setFakeAuthor3(String fakeAuthor3) {
        this.fakeAuthor3 = fakeAuthor3;
    }
}
