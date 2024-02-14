import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Map01 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaaa");
        list.add("aaaa"); //순서가 있기때문에 중복값 가능
        list.get(0); //순서로 값을 뽑는다.


        // key, value 순으로 존재
        // ket : 중복 X
        // value : 중복 O
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "java");
        map.put(2, "python");
        map.put(3, "c++"); // 순서가 존재하지 않기때문에 key는 중복 불가능하고 value만 중복 가능

        map.get(2); // key로 이용해서 값을 뽑는다.
    }

    public Map<String,Object> aaa(){
        Aaa a = new Aaa();
        Bbb b = new Bbb();

        Map<String, Object> map = new HashMap<>();
        map.put("Aaa의 a", a);
        map.put("Bbb의 b", b);

        return map;
    }
}

class Aaa{}
class Bbb{}
class Ccc{}
