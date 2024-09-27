package br.com.mangarosa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.datastructures.interfaces.impl.QueueNode;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LinkedQueue<String> linkedList = new LinkedQueue<>(String.class);

        linkedList.enqueue("Guilherme");
        linkedList.enqueue("Graziele");
        linkedList.enqueue("Julio Cesar");

        // String[] array = linkedList.toArray();

        System.out.println(LocalDateTime.now());
    }
}