package by.bsuir.psp.clientServer.server;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Server {
    public static void main(String[] arg) {

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd 'и время' hh:mm:ss");

        //объявление объекта класса ServerSocket
        ServerSocket serverSocket = null;
        Socket clientAccepted = null;//объявление объекта класса Socket
        ObjectInputStream sois = null;//объявление байтового потока ввода
        ObjectOutputStream soos = null;//объявление байтового потока вывода

        try {

            System.out.println("server starting....");
            serverSocket = new ServerSocket(2525);//создание сокета сервера для //заданного порта
            clientAccepted = serverSocket.accept();//выполнение метода, который //обеспечивает реальное подключение сервера к клиенту
            System.out.println("connection established....");

            sois = new ObjectInputStream(clientAccepted.getInputStream());
            soos = new ObjectOutputStream(clientAccepted.getOutputStream());


            String clientMessage = (String) sois.readObject();//объявление //строки и присваивание ей данных потока ввода, представленных //в виде строки (передано клиентом)


            while (!clientMessage.equals("quite"))//выполнение цикла: пока //строка не будет равна «quite»


            {

                System.out.println("client message: '" + clientMessage + "'" + " " + formatForDateNow.format(dateNow));

                // clientMessageRecieved = clientMessageRecieved.toUpperCase();//приведение символов строки к верхнему регистру
                BufferedReader stdin =
                        new BufferedReader(new InputStreamReader(System.in));


                String serverMessage = stdin.readLine();
                System.out.println("server: " + serverMessage + " " + formatForDateNow.format(dateNow));
                soos.writeObject(serverMessage);//потоку вывода

                clientMessage = (String) sois.readObject();

            }
        } catch (Exception e) {

        } finally {

            try {

                sois.close();//закрытие потока ввода

                soos.close();//закрытие потока вывода

                clientAccepted.close();//закрытие сокета, выделенного для клиента

                serverSocket.close();//закрытие сокета сервера

            } catch (Exception e) {

                e.printStackTrace();//вызывается метод исключения е

            }

        }

    }

}