public class Test{

        public static void buggyMethod5(int i)
        {
            int x;

            x = i/0;
            System.out.println(x);
        }

        public static void main(String []args){
            buggyMethod5(4);

        }
}
