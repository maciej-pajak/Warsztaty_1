package task5.MostPopularWords;


class WordOccurrence implements Comparable<WordOccurrence> {
    public String word;
    public int occurrences;
    
    public WordOccurrence(String word, int count) {
        this.word = word;
        this.occurrences = count;
    }
    
    @Override
    public boolean equals(Object o) {
        if ( o instanceof WordOccurrence ) {
            return this.word.equals(((WordOccurrence) o).word);
        } else {
            return false;
        }
        
    }
    
    public boolean equals(WordOccurrence comp) {
        if ( comp.word.equals(this.word) ) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public int compareTo(WordOccurrence arg0) {
        return this.occurrences - arg0 .occurrences;
    }
    
    @Override
    public String toString() {
        return (this.word + " " + this.occurrences);
    }
}
