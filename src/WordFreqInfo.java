import java.util.*;

public class WordFreqInfo {
    /**
     * Test the toString() and updateFollows() methods of WordFreqInfo
     * Should initialize the key in original creation of object
     * Any update after that will add on a "following" word
     */
    public static void main(String[] args){
        WordFreqInfo info1 = new WordFreqInfo("blue", 0);
        System.out.println(info1.toString());
        info1.updateFollows("pink");
        System.out.println(info1.toString());
        info1.updateFollows("pink");
        System.out.println(info1.toString());
        info1.updateFollows("green");
        System.out.println(info1.toString());

        WordFreqInfo info2 = new WordFreqInfo("purple", 5);
        System.out.println(info2.toString());
        info2.updateFollows("is");
        System.out.println(info2);
    }

    public String word;
    public int occurCt;
    ArrayList<Freq> followList;

    public WordFreqInfo(String word, int count) {
        this.word = word;
        this.occurCt = count;
        this.followList = new ArrayList<Freq>();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Word :" + word + ":");
        sb.append(" (" + occurCt + ") : ");
        for (Freq f : followList)
            sb.append(f.toString());

        return sb.toString();
    }

    /**
     * Marks when you've found another trailing word and adds to the frequency
     * @param follow the trailing word
     */
    public void updateFollows(String follow) {
       //System.out.println("updateFollows " + word + " " + follow);
        occurCt++;
        for (Freq f : followList) {
            if (follow.compareTo(f.follow) == 0) {
                f.followCt++;
                return;
            }
        }
        followList.add(new Freq(follow, 1));
    }

    public static class Freq {
        String follow;
        int followCt;

        public Freq(String follow, int ct) {
            this.follow = follow;
            this.followCt = ct;
        }

        public String toString() {
            return follow + " [" + followCt + "] ";
        }

        public boolean equals(Freq f2) {
            return this.follow.equals(f2.follow);
        }
    }
}

