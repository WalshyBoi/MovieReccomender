package models;

public class Genre {
private String genreName;
private int genreId;
	public Genre(String genreName, int genreId){
		this.genreName = genreName;
		this.genreId = genreId;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	@Override
	public String toString() {
		return "Genre [genreName=" + genreName + ", genreId=" + genreId + "]";
	}
}
