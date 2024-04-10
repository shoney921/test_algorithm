import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class javaTest {
    public static int solution_0409_2327(int n, int a, int b) {
        int answer = 0;

        List<Integer> base = new ArrayList<>();

        while (a != b) {
            answer++;
            for (int i = 0; i < n; i++) {
                base.add((i + 2) / 2);
            }
            n = n / 2;
            a = base.get(a - 1);
            b = base.get(b - 1);
            solution_0409_2327_toStringMethod(n, a, b);
            if (a == b) {
                return answer;
            }
            base.clear();
        }
        return answer;
    }

    private static void solution_0409_2327_toStringMethod(int n, int a, int b) {
        System.out.println("+++++++++++++++++");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("n = " + n);
        System.out.println("+++++++++++++++++");
    }

    public static int solution_0410_1351(int[] nums) {
        // todo Set 자료구조 전환 방법 (Arrays -> Set)
        Set<Integer> collect = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        System.out.println("collect = " + collect);

        // todo List 안의 값을 map으로 바꾸는 방법, 람다식으로 정렬하는 방법
        List<Integer> list = collect.stream().map(x -> {
            if (x == 2) {
                return x;
            }
            return 0;
        }).sorted((x, y) -> y - x).toList();
        System.out.println("list = " + list);


        int count = (int) Arrays.stream(nums).boxed().collect(Collectors.toSet()).stream().count();
        return Math.min(count, nums.length / 2);
    }

    public static String solution_0410_1700(String[] participant, String[] completion) {
        List<String> partList = Arrays.stream(participant).sorted().collect(Collectors.toList());
        List<String> compList = Arrays.stream(completion).sorted().collect(Collectors.toList());

        List<String> test = new ArrayList<>();

        for (int i = 0; i < compList.size(); i++) {
            if (!partList.get(i).equals(compList.get(i))) {
                return partList.get(i);
            }
        }

        return partList.get(partList.size() - 1);
    }

    public static boolean solution_0410_1705(String[] phone_book) {
        List<String> list = Arrays.stream(phone_book).sorted(Comparator.comparing(String::length)).collect(Collectors.toList());

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                String lf = list.get(i);
                String rt = list.get(j);
                if (rt.startsWith(lf)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int solution_0410_1956(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> hashMap = new HashMap<>();

        Arrays.stream(clothes)
                .forEach(x -> hashMap.put(x[1], hashMap.getOrDefault(x[1], 1) + 1));

        for (String s : hashMap.keySet()) {
            System.out.println("s = " + s);
            System.out.println("hashMap = " + hashMap.get(s));
            answer *= hashMap.get(s);
        }

        return answer - 1;
    }


    public static class Album {
        int id;
        String genres;
        int play;

        public Album(int id, String genres, int play) {
            this.id = id;
            this.genres = genres;
            this.play = play;
        }

        public int getId() {
            return id;
        }

        public String getGenres() {
            return genres;
        }

        public int getPlay() {
            return play;
        }
    }

    public static int[] solution_0410_2009(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        List<Album> albums = new ArrayList<>();
        HashMap<String, Integer> genresSum = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            albums.add(new Album(i, genres[i], plays[i]));
            genresSum.put(genres[i], genresSum.getOrDefault(genres[i], 0) + plays[i]);
        }

        List<String> genresRank = genresSum.keySet().stream()
                .sorted((x, y) -> genresSum.get(y) - genresSum.get(x)).collect(Collectors.toList());

        List<Album> albumList = albums.stream()
                .sorted(Comparator.comparing(Album::getGenres).thenComparing(Album::getPlay).reversed()).collect(Collectors.toList());

        for (String s : genresRank) {
            int count = 0;
            for (Album album : albumList) {
                if (album.getGenres().equals(s) && count < 2) {
                    answer.add(album.getId());
                    count += 1;
                }
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop", "kk"};
        int[] plays = {500, 600, 150, 800, 2500, 3000};
        int[] ints = solution_0410_2009(genres, plays);
        for (int anInt : ints) {
            System.out.println("anInt = " + anInt);
        }
//        String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
//        int i = solution_0410_1956(clothes);
//        System.out.println("i = " + i);


//        String[] phone_book = {"119", "97674223", "1195524421", "1124"};
//        String[] phone_book3 = {"12", "123", "1235", "567", "88"};
//        String[] phone_book1 = {"123","456","789"};
//        boolean b = solution_0410_1705(phone_book);
//        System.out.println("b = " + b);


//        String[] part = {"marina", "josipa", "nikola", "vinko", "filipa"};
//        String[] comp = {"josipa", "filipa", "marina", "nikola"};
//        String solution = solution_0410_1700(part, comp);
//        System.out.println("solution = " + solution);
//        List<Student> students = List.of(new Student(187, "shoney")
//                , new Student(167, "jenny")
//                , new Student(178, "apple")
//                , new Student(166, "test3")
//                , new Student(166, "test2")
//                , new Student(166, "test1")
//        );
//
//
//        students.stream().sorted(Comparator.comparing(Student::getHeight).reversed()
//                        .thenComparing(Student::getName))
//                .forEach(x -> System.out.println("이름 " + x.getName() + "  키 "+ x.getHeight()));

//
//        int[] nums = {1, 3, 4, 5, 5, 5, 2, 3};
//        int i = solution_0410_1351(nums);
//        System.out.println("i = " + i);


//        int solution = solution_0409_2327(8, 4, 7);
    }
}
