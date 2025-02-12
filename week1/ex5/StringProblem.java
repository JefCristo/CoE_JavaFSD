package week1.ex5;

class StringProblem {
    public String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public int countOccurrences(String text, String sub) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(sub, index)) != -1) {
            count++;
            index++;
        }
        return count;
    }

    public String splitAndCapitalize(String str) {
        String[] words = str.split(" ");
        StringBuilder capitalized = new StringBuilder();
        for (String word : words) {
            capitalized.append(word.substring(0, 1).toUpperCase()).append(word.substring(1)).append(" ");
        }
        return capitalized.toString().trim();
    }

    public static void main(String[] args) {
        StringProblem processor = new StringProblem();

        String text = "hello world hello again";
        System.out.println("Reversed String: " + processor.reverseString(text));
        System.out.println("Occurrences of 'hello': " + processor.countOccurrences(text, "hello"));
        System.out.println("Capitalized String: " + processor.splitAndCapitalize(text));
    }
}
