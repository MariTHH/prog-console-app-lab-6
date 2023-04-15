package collection;

import java.util.*;

/**
 * A class that creates a unique id for each character
 */
public class GenerationId {

    private static final Set<Integer> arg = new HashSet<>();
    private static final Random random = new Random();

    /**
     * generates a unique id greater than zero
     *
     * @return id (int)
     */
    public static int generateID() {
        int id = random.nextInt(Integer.MAX_VALUE);
        while (arg.contains(id)) {
            id = random.nextInt(Integer.MAX_VALUE);
        }
        arg.add(id);
        return id;
    }
}

interface z {
    void go1();
}

class F {
    public static class F1 {
        public static <T> T getT(Object object) {
            return (T) object;
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 3, 4, 5);
        for (Object element : list) {
            System.out.println(F1.<Integer>getT(element));
        }
        List<String> list11 = Arrays.asList("aa","help");
        for (Object element : list11) {
            System.out.println(F1.<String>getT(element));
        }
    }
}