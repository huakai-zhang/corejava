package corejava.chapter3.section6;

public class CodePointCount {
    public static void main(String[] args) {
        String greeting = "𝕆 is the set of octonions";
        /*int cpCount = greeting.codePointCount(0, greeting.length());
        System.out.println(greeting.length());
        System.out.println(cpCount);

        char first = greeting.charAt(1);
        char last = greeting.charAt(4);
        System.out.println(first);
        System.out.println(last);

        int index = greeting.offsetByCodePoints(0, 2);
        int cp = greeting.codePointAt(index);
        System.out.println(index);
        System.out.println(cp);*/

        for (int i = 0; i < greeting.length();) {
            int cp = greeting.codePointAt(i);
            System.out.println(cp);
            if (Character.isSupplementaryCodePoint(cp)) {
                i += 2;
            } else {
                i++;
            }
        }

        int[] codePoints = greeting.codePoints().toArray();
        for (int i = 0;i < codePoints.length;i++) {
            System.out.println(codePoints[i]);
        }

        String str = new String(codePoints, 0, 1);
        System.out.println(str);
    }
}
