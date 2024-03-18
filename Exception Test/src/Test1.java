import java.util.InputMismatchException;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        // 예외처리
        System.out.println("프로그램 시작");

        Scanner sc = new Scanner(System.in);

        try { // 예외가 발생할 수 있는 코드
            int num1 = sc.nextInt();
            int num2 = sc.nextInt();
            System.out.println(num1 / num2);
            System.out.println(111);
//        } catch (Exception e) {
////            System.out.println("예외 발생");
////            // 예외 발생 이유 출력
////            System.out.println(e.getMessage());
////            e.printStackTrace();
//        }
        } catch (ArithmeticException e){ // 예외 발생 시 실행할 코드 (오류를 낸 후 자료형 복붙)
            System.out.println("모든 수는 0을 나눌 수 없습니다.");
        } catch (InputMismatchException e){
            System.out.println("문자는 나눌 수 없습니다.");
        } finally { // 예외 발생 유무 상관없이 무조건 실행되는 코드

        }

        System.out.println("프로그램 종료");

    }
}
