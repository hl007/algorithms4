import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Evaluate {
    public static void main(String[] args){
        //一个运算符栈
        Stack<String> ops=new Stack<>();
        //一个操作数栈
        Stack<Double> vals=new Stack<>();
        while (!StdIn.isEmpty()){
            String s=StdIn.readString();
            //如果是左括号，忽略
            if(s.equals("("));
            else if(s.equals("+")) ops.push(s);
            else if(s.equals("-")) ops.push(s);
            else if(s.equals("*")) ops.push(s);
            else if(s.equals("/")) ops.push(s);
            else if(s.equals("sqrt")) ops.push(s);
            //如果是右括号，弹出操作数和运算符计算后，压入操作数栈
            else if(s.equals(")")){
                String op=ops.pop();
                double v=vals.pop();
                if(op.equals("+")) v=vals.pop()+v;
                else if(op.equals("-")) v=vals.pop()-v;
                else if(op.equals("*")) v=vals.pop()*v;
                else if(op.equals("/")) v=vals.pop()/v;
                else if(op.equals("sqrt")) v=Math.sqrt(v);
                vals.push(v);
            }
            else vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());
    }
}
