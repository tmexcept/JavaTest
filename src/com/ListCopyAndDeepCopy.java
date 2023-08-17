package com;

public class ListCopyAndDeepCopy {
    public static void main(String[] args){
        String s = "#define REVERB_OFF 0\n" +
                "#define REVERB_GENERIC 1\n" +
                "#define REVERB_PADDEDCELL 2\n" +
                "#define REVERB_ROOM 3\n" +
                "#define REVERB_BATHROOM 4\n" +
                "#define REVERB_LIVINGROOM 5\n" +
                "#define REVERB_STONEROOM 6\n" +
                "#define REVERB_AUDITORIUM 7\n" +
                "#define REVERB_CONCERTHALL 8\n" +
                "#define REVERB_CAVE 9\n" +
                "#define REVERB_ARENA 10\n" +
                "#define REVERB_HANGAR 11\n" +
                "#define REVERB_CARPETTEDHALLWAY 12\n" +
                "#define REVERB_HALLWAY 13\n" +
                "#define REVERB_STONECORRIDOR 14\n" +
                "#define REVERB_ALLEY 15\n" +
                "#define REVERB_FOREST 16\n" +
                "#define REVERB_CITY 17\n" +
                "#define REVERB_MOUNTAINS 18\n" +
                "#define REVERB_QUARRY 19\n" +
                "#define REVERB_PLAIN 20\n" +
                "#define REVERB_PARKINGLOT 21\n" +
                "#define REVERB_SEWERPIPE 22\n" +
                "#define REVERB_UNDERWATER 23";

        String[] strings = s.split("\n");
        String[] ss;
        for (String string : strings) {
            ss = string.split(" ");
            String k =ss[1].replace("REVERB", "FMOD_PRESET");
            System.out.println("case "+ss[1]+":");
            System.out.println("    pro = "+ k+";");
            System.out.println("    break;");
        }




//        List A = new ArrayList<>();
//        for(int i=0;i<5;i++){
//            Entity entity = new Entity();
//            entity.name = "i "+i;
//            A.add(entity);
//        }
//        for(int i=0;i<5;i++){
//            System.out.println(A.get(i).toString());
//        }
//        System.out.println("A");
//        System.out.println("----------------");
//        List C = new ArrayList<>();
//        for(int i=0;i<5;i++){
//            C.add(A.get(i));
//        }
//
//        for(int i=0;i<5;i++){
//            System.out.println(C.get(i).toString());
//        }
//        System.out.println("C--Add");
//        System.out.println("----------------");
//        List B = new ArrayList<>();
//        B.addAll(A);
//
//        for(int i=0;i<5;i++){
//            System.out.println(B.get(i).toString());
//        }
//        System.out.println("B--AddAll");
//        System.out.println("----------------");
//        System.out.println("A.clear()");
//        A.clear();
//        System.out.println("B.size()="+B.size());
//        System.out.println("C.size()="+C.size());
    }

    static class Entity{
        public String name;
    }
}
