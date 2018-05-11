import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EnoughIsEnough {

    public static int[] deleteNth(int[] elements, int maxOccurrences) {
        int[] a = {};
        int index = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < elements.length; i++) {
            if (map.containsKey(elements[i])) {
                if(map.get(elements[i]) < maxOccurrences){
                    map.replace(elements[i], map.get(elements[i]) + 1);
                    a = Arrays.copyOf(a, a.length + 1);
                    a[index++] = elements[i];
                }
            } else {
                map.put(elements[i], 1);
                a= Arrays.copyOf(a, a.length+1);
                a[index++] = elements[i];
            }
        }
        return a;
    }

    public static void main(String[] args){
        int[] a=deleteNth(new int[]{1},1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }
}