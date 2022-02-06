package net.hopkins22.demoapi.domain;

public enum SearchType {
    EMPTY("EMPTY"),
    ARTIST_ONLY("ARTIST ONLY"),
    GENRE_ONLY("GENRE ONLY"),
    ALL_CONDITIONS("ALL CONDITIONS");

    private String searchVal;

    SearchType(String s) {
        searchVal = s;
    }

    public String getSearchType() {
        return this.searchVal;
    }
}
