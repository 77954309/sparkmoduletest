import org.junit.Test;

/**
 * @Author: limeng
 * @Date: 2019/7/24 10:54
 */
public class DemoTest {
    public static void main(String[] args) {
        int i=1;
        int j=1;
        System.out.println(i&j);
        System.out.println();

    }
    @Test
    public void test2(){
        boolean status=true;
        System.out.println("asdas");
        if(status){
            return;
        }
        System.out.println("asdasdasd");
    }
}