package by.bsuir.psp.clientServer.client;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

// работы в сети

public class Client {

    public static void main(String[] arg) {

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd 'и время' hh:mm:ss");


        try {

            System.out.println("server connecting....");

            Socket clientSocket = new Socket("127.0.0.1", 2525);//установление //соединения между локальной машиной и указанным портом узла сети

            System.out.println("connection established....");

            BufferedReader stdin =

                    new BufferedReader(new InputStreamReader(System.in));//создание

//буферизированного символьного потока ввода

            ObjectOutputStream coos =

                    new ObjectOutputStream(clientSocket.getOutputStream());//создание

//потока вывода

            ObjectInputStream cois =

                    new ObjectInputStream(clientSocket.getInputStream());//создание

//потока ввода

            System.out.println("Enter any string to send to server \n\t('quite' − programme terminate)");

            String clientMessage = stdin.readLine();

            System.out.println("your entered: " + clientMessage + " " + formatForDateNow.format(dateNow));
            while (!clientMessage.equals("quite")) {//выполнение цикла, пока строка //не будет равна «quite»

                coos.writeObject(clientMessage);//потоку вывода присваивается //значение строковой переменной (передается серверу)

                System.out.println("server message: " + cois.readObject() + " " + formatForDateNow.format(dateNow));//выводится на //экран со-держимое потока ввода (переданное сервером)

                // System.out.println("---------------------------");

                clientMessage = stdin.readLine();//ввод текста с клавиатуры


                System.out.println("your message: " + clientMessage + " " + formatForDateNow.format(dateNow));//вывод в

//консоль строки и значения строковой переменной

            }

            coos.close();//закрытие потока вывода

            cois.close();//закрытие потока ввода

            clientSocket.close();//закрытие сокета

        } catch (Exception e) {

            e.printStackTrace();//выполнение метода исключения е

        }

    }

}