package net.hopkins22.demoapi.domain;


import org.apache.commons.lang3.ArrayUtils;

public class RequestFilterDto {

    private Long[] artist;
    private Long[] genre;

    public RequestFilterDto() {}

    public RequestFilterDto(Long[] artist, Long[] genre) {
        this.artist = artist;
        this.genre = genre;
    }

    public Long[] getArtist() {
        return artist;
    }

    public void setArtist(Long[] artist) {
        this.artist = artist;
    }

    public Long[] getGenre() {
        return genre;
    }

    public void setGenre(Long[] genre) {
        this.genre = genre;
    }

    public static boolean isEmpty(RequestFilterDto other) {
        return ArrayUtils.isEmpty(other.artist)
                && ArrayUtils.isEmpty(other.genre);
    }

    public static boolean isArtistOnlyValues(RequestFilterDto other) {
        return !ArrayUtils.isEmpty(other.getArtist())
                && ArrayUtils.isEmpty(other.getGenre());
    }

    public static boolean isGenreOnlyValues(RequestFilterDto other) {
        return !ArrayUtils.isEmpty(other.getGenre())
                && ArrayUtils.isEmpty(other.getArtist());
    }

    @Override
    public String toString() {
        return "RequestFilter{" +
                "artist=" + artist +
                ", genre=" + genre +
                '}';
    }
}
