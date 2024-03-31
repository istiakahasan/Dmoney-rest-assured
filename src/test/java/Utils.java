public class Utils {
    public static int generateRandom(int min,int max){
       double randomId= Math.random()*(max-min)+min;
        return (int) randomId;
    }
    public static void main(String[]args){
        int rand= generateRandom(1000,9999);
        System.out.println(rand);
    }

    public static void setEnvVar(String token, String token1) {
    }
}
