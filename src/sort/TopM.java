package sort;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Transaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class TopM {

    public static void main(String[] args) throws FileNotFoundException {
        int M=Integer.parseInt(args[0]);
        MinPQ<Transaction> pq=new MinPQ<Transaction>(M+1);
        Scanner s=new Scanner(new File("F:\\algorithms4\\algs4-data\\algs4-data\\tinyBatch.txt"));
        while(s.hasNextLine()){
            pq.insert(new Transaction(s.nextLine()));
            if(pq.size()>M){
                pq.delMin();
            }
        }
        Stack<Transaction> stack=new Stack<Transaction>();
        while(!pq.isEmpty()) stack.push(pq.delMin());
        for(Transaction t:stack) System.out.println(t);
    }
}
