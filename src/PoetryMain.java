public class PoetryMain {
    public static void main(String[] args) {

        WritePoetry poem = new WritePoetry();
        System.out.println(poem.WritePoem("green.txt", "sam", 20, true));
        System.out.println(poem.WritePoem("Lester.txt", "lester", 30, true));
        System.out.println(poem.WritePoem("HowMany.txt", "how", 30, false));
        System.out.println(poem.WritePoem("Zebra.txt", "are", 50, true));

        // bonus cases
        System.out.println(poem.WritePoem2("green.txt", "am-sam", 20, true));
        System.out.println(poem.WritePoem2("Lester.txt", "lester-was", 30, true));
        System.out.println(poem.WritePoem2("HowMany.txt", "how-much", 30, false));
        System.out.println(poem.WritePoem2("Zebra.txt", "are-you", 50, true));
    }

}