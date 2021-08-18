/**
 * @author run
 * @since 2021/3/23 22:45
 */
public class HelloWorld {
    public static void main(String []args) {
        int count = 0;
        for(int i = 1; i < 2020; i++){
            count += getCount(i);
        }
        System.out.println(count);
    }

    public static int getCount(int num){
        int length = (num + "").length();
        int divide = (int) Math.pow(10, length - 1);
        int count = 0;
        while(num > 0){
            int d = num / divide;
            if(d == 2){
                count++;
            }
            num -= d * divide;
            divide /= 10;
        }
        return count;
    }
}