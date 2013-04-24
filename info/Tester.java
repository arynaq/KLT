import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Tester {
    private Object field1;
    private Object field2;
    private Object field3;

    private Map<Integer, Tester> testers;

    public Tester(Map<Integer, Tester> testers) {
        this.testers = testers;
    }

    public void someMethod() {
    }

    public void removeThySelf(int key) {
        System.out.println("Before: " + testers);
        System.out.println(this + "removed");
        testers.remove(key);
        System.out.println("After: " + testers);
    }

    public static void main(String[] args) {
        Map<Integer, Tester> testers = new ConcurrentHashMap<Integer, Tester>();
        testers.put(1, new Tester(testers));
        testers.put(2, new Tester(testers));
        testers.put(3, new Tester(testers));

        for (Integer key : testers.keySet())
            testers.get(key).removeThySelf(key);
    }
}
