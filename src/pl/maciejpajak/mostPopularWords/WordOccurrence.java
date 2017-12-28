package pl.maciejpajak.mostPopularWords;


class WordOccurrence  {
    public String word;
    public int occurrences;
    
    public WordOccurrence(String word, int count) {
        this.word = word;
        this.occurrences = count;
    }
    
    @Override
    public String toString() {
        return (this.word + " " + this.occurrences);
    }
}
