import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class WritePoetry {
    /**
     * Writes a poem based on a file
     * Has restrictions on what word to start with and when to stop
     * May or may not print the associated hash table
     *
     * @param filename   the file we look at before we write our poem
     * @param startWord  first word of our poem
     * @param poemLength number of words in the poem
     * @param willPrint  whether we print the hash table
     * @return the poem (and possibly the hash table as a string)
     */
    public String WritePoem(String filename, String startWord, int poemLength, boolean willPrint) {
        HashTable<WordFreqInfo, String> info = buildHashTable(filename);

        String nextWord;
        String oldWord = pickNextWord(info.find(startWord));
        StringBuilder poem = new StringBuilder(startWord + " ");

        for (int i = 0; i < poemLength; i++) {
            nextWord = pickNextWord(info.find(oldWord));
            poem.append(nextWord).append(" ");
            if (Objects.equals(nextWord, ".")) {
                poem.append("\n");
            }
            if (i == poemLength - 1) {
                poem.append(".");
            }
            oldWord = nextWord;
        }

        if (willPrint) {
            return info + "\n" + poem + "\n\n";
        } else {
            return poem + "\n\n";
        }
    }

    public String WritePoem2(String filename, String startWord, int poemLength, boolean willPrint) {
        HashTable<WordFreqInfo, String[]> info2 = buildHashTable2(filename);

        String nextWord;
        String oldWord = pickNextWord(info2.find(startWord));
        StringBuilder poem = new StringBuilder(startWord + " ");

        for (int i = 0; i < poemLength; i++) {
            nextWord = pickNextWord(info2.find(oldWord));
            poem.append(nextWord).append(" ");
            if (Objects.equals(nextWord, ".")) {
                poem.append("\n");
            }
            if (i == poemLength - 1) {
                poem.append(".");
            }
            oldWord = nextWord;
        }

        if (willPrint) {
            return info2 + "\n" + poem + "\n\n";
        } else {
            return poem + "\n\n";
        }
    }

    /**
     * Chooses the next word based on the WordFreqInfo
     * Words found more often will be chosen more often when picked randomly
     *
     * @param lastWord previous word that holds all of its following words' data
     * @return randomly chosen next word
     */
    private String pickNextWord(WordFreqInfo lastWord) {
        int randomPick = (int) (Math.random() * lastWord.occurCt);

        // checks which of the options will
        for (WordFreqInfo.Freq f : lastWord.followList) {
            if (randomPick < f.followCt) {
                return f.follow;
            } else {
                randomPick -= f.followCt;
            }
        }
        // shouldn't get here
        return null;
    }

    /**
     * Chooses the next word based on the WordFreqInfo
     * Words found more often will be chosen more often when picked randomly
     *
     * @param lastWord previous word that holds all of its following words' data
     * @return randomly chosen next word
     */
    private String pickNextWord2(WordFreqInfo lastWord) {
        int randomPick = (int) (Math.random() * lastWord.occurCt);

        // checks which of the options will
        for (WordFreqInfo.Freq f : lastWord.followList) {
            if (randomPick < f.followCt) {
                return f.follow;
            } else {
                randomPick -= f.followCt;
            }
        }
        // shouldn't get here
        return null;
    }

    /**
     * Take a file and parse through it
     * Build a hash table of every word in the file and every word that follows it with frequencies
     *
     * @param filename text file that we read into a hash table
     * @return the finished hash table or exit if the file doesn't exist
     */
    private HashTable buildHashTable(String filename) {
        try {
            HashTable<WordFreqInfo, String> info = new HashTable<>();  // the hash table we'll return later
            String key = "";
            String follower;
            Scanner reader = new Scanner(new File(filename));

            // set first word, if possible
            if (reader.hasNext()) {
                key = reader.next().toLowerCase();
                WordFreqInfo currentWord = new WordFreqInfo(key, 0);
                info.insert(currentWord, key);
            }
            // add other words to the hash table, if they exist, while updating followings
            while (reader.hasNext()) {
                follower = reader.next().toLowerCase();
                info.find(key).updateFollows(follower);

                // set for new iteration
                key = follower;
                WordFreqInfo shiftWord = new WordFreqInfo(key, 0);

                // only add new words to the table
                if (!info.contains(key)) {
                    info.insert(shiftWord, key);
                }
            }
            // System.out.println(info.toString());
            return info;

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        // will never get here
        return null;
    }

    private HashTable buildHashTable2(String filename) {
        try {
            HashTable<WordFreqInfo, String> info = new HashTable<>();  // the hash table we'll return later
            String key1 = "";
            String key2 = "";
            String follower;
            Scanner reader = new Scanner(new File(filename));

            // set first word, if possible
            if (reader.hasNext()) {
                key1 = reader.next().toLowerCase();
                if (reader.hasNext()) {
                    key2 = reader.next().toLowerCase();
                    WordFreqInfo currentWord = new WordFreqInfo(key1 + "-" + key2, 0);
                    info.insert(currentWord, key1 + "-" + key2);
                } else {
                    WordFreqInfo currentWord = new WordFreqInfo(key1, 0);
                    info.insert(currentWord, key1);
                }
            }

            // add other words to the hash table, if they exist, while updating followings
            while (reader.hasNext()) {
                follower = reader.next().toLowerCase();
                info.find(key1 + "-" + key2).updateFollows(follower);

                // set for new iteration
                key1 = key2;
                key2 = follower;
                WordFreqInfo shiftWord = new WordFreqInfo(key1 + "-" + key2, 0);

                // only add new words to the table
                if (!info.contains(key1 + "-" + key2)) {
                    info.insert(shiftWord, key1 + "-" + key2);
                }
            }
            // System.out.println(info.toString());
            return info;

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        // will never get here
        return null;
    }
}
