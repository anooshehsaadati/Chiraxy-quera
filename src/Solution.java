import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Solution {

    public static void main(String[] args) {
        List<Supplier<?>> functions = Arrays.asList(new Devider(1, 0), new Devider(1, 1));

        Solution solution = new Solution();
        List<Solution.ExceptionProxy> result = solution.transformException(functions);
        for (Solution.ExceptionProxy exceptionProxy : result) {
            System.out.println("msg: " + exceptionProxy.getMsg());
            System.out.println("function: " + exceptionProxy.getFunction());
        }
    }

    public List<ExceptionProxy> transformException(List<Supplier<?>> functions) {
        //TODO
        List<ExceptionProxy> exceptionProxyList = new ArrayList<>();
        for (Supplier<?> f : functions) {
            try {
                f.get();
                exceptionProxyList.add(new ExceptionProxy("OK!", f));
            } catch (Exception e) {
                exceptionProxyList.add(new ExceptionProxy(e.getMessage(), f));
            }
        }
        return exceptionProxyList;
    }

    public static class ExceptionProxy {
        //TODO
        private final String msg;
        private final Supplier<?> function;

        public ExceptionProxy(String msg, Supplier<?> function) {
            this.msg = msg;
            this.function = function;
        }

        public String getMsg() {
            return msg;
        }

        public Supplier<?> getFunction() {
            return function;
        }
    }

    public static class Devider implements Supplier<Integer> {
        int x, y;

        public Devider(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public Integer get() {
            return x / y;
        }

        @Override
        public String toString() {
            return "Devide[" + x + "/" + y + "]";
        }
    }
}
