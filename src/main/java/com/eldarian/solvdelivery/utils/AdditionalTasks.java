package com.eldarian.solvdelivery.utils;

import java.util.*;

public class AdditionalTasks {
    public static int[][] transpose(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
        return matrix;
    }

    public static void removeArrayElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(5, 4, 3, 2, 1));
        for (Integer num:
             list) {
            System.out.println(num);
        }
        System.out.println();

        list.remove(3);
        for (Integer num:
                list) {
            System.out.println(num);
        }
    }

    public static void setAndQueues() {
        System.out.println("=====Set======");
        Set<String> phones = new HashSet<>();
        Collections.addAll(phones, "+37511111111 +37522222222 +37533333333 +37533333333".split(" "));
        System.out.println(phones.contains("+37522222222"));
        System.out.println(phones.contains("+37544444444"));
        System.out.println(phones);

        System.out.println("=====Queue=====");
        Queue<Integer> sampleQueue = new LinkedList<>(); //LinkedList implements List, Queue, Dequeue.
        sampleQueue.add(1);
        sampleQueue.add(2);
        sampleQueue.add(3);
        sampleQueue.offer(4); //preferable for capacity-restricted queues
        System.out.println("queue first element is " + sampleQueue.peek());
        System.out.println("queue taken first element " + sampleQueue.poll());
        System.out.println("now queue first element is " + sampleQueue.peek());

    }
}
