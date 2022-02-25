package com.techprimers.test.testcontrollerexample;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/hello")
public class HelloResource {

    private HelloService helloService;

    private final String usersTextFilePath = "C:\\DEV\\test-controller-example-master-Copy\\users.txt";

    private final int currentYear = 2022;

    /*
    public HelloResource(HelloService helloService) {
        this.helloService = helloService;
    }

     */


    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User post(@RequestBody User user) throws Exception {

        addUserToTextFile(user);

        return user;
    }


    @PostMapping(value="/updateUser", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user) throws Exception {

        if(searchForUserInTheUsersTextFile(user)) {

            updateTheUserFieldsInTheTextFile(user);

        }


        return user;
    }


    private void addUserToTextFile(User user) throws Exception {

        FileWriter fileWriter = new FileWriter(usersTextFilePath, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        String userDateOfBirth = user.getUserDateOfBirth();
        String[] userDateOfBirthSplit = user.getUserDateOfBirth().split("/");

        int userYearOfBirth = Integer.parseInt(userDateOfBirthSplit[0]);
        int userAge = currentYear - userYearOfBirth;

        printWriter.println("Name: " + user.getTitle());
        printWriter.println("id: " + user.getUserId());
        printWriter.println("date of birth: " + user.getUserDateOfBirth());
        printWriter.println("job: " + user.getUserJob());
        printWriter.println("address: " + user.getUserAddress());

        user.setCanOpenBankAccount("true");

        if(userAge >= 15) {
            user.setCanTradeStocks("true");
        } else  {
            user.setCanTradeStocks("false");
        }


        if(user.getUserAddress().contains("15 Queen St E,Brampton,ON")) {
            printWriter.println("promo message: " + "15% lower transaction fees for stocks");
        } else if(user.getUserAddress().contains("25 King St W,Toronto,ON")) {
            printWriter.println("promo message: " + "$1000 cash sign up bonus");
        } else {
            printWriter.println("promo message: " + user.getPromotionalMessage());
        }

        printWriter.println();

        printWriter.close();
        fileWriter.close();

    }

    private boolean searchForUserInTheUsersTextFile(User user) throws Exception {

        System.out.println("List1: " + Arrays.toString(addTextFileLinesToList().toArray()));

        for(int i = 0; i < addTextFileLinesToList().size(); i++) {
            if(addTextFileLinesToList().get(i).equals("Name: " + user.getTitle())) return true;
        }

        return false;
    }


    private ArrayList<String> addTextFileLinesToList() throws Exception {

        ArrayList<String> listOfLines = new ArrayList<>();
        BufferedReader bufReader = new BufferedReader(new FileReader(usersTextFilePath));

        String line = bufReader.readLine();

        while (line != null) {
            listOfLines.add(line);
            line = bufReader.readLine();
        }

        bufReader.close();

        return listOfLines;

    }

    private void updateTheUserFieldsInTheTextFile(User user) throws Exception {

        ArrayList<String> list = addTextFileLinesToList();

        updateTextFileArrayList(list, user);

        System.out.println("New List: " + Arrays.toString(list.toArray()));

        FileWriter fileWriter = new FileWriter(usersTextFilePath);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for(int i = 0; i < list.size(); i++) {

            printWriter.println(list.get(i));

        }

        printWriter.close();
        fileWriter.close();

    }


    private void updateTextFileArrayList(ArrayList<String> list, User user) {

        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).equals("Name: " + user.getTitle())) {

                int j = i+1;
                while(j < list.size() && !(list.get(j).contains("Name: "))) {

                    String userDateOfBirth = user.getUserDateOfBirth();
                    String[] userDateOfBirthSplit = user.getUserDateOfBirth().split("/");

                    int userYearOfBirth = Integer.parseInt(userDateOfBirthSplit[2]);
                    int userAge = currentYear - userYearOfBirth;

                    if(list.get(j).contains("id")) list.set(j, "id: " + user.getUserId());
                    if(list.get(j).contains("date of birth")) list.set(j, "date of birth: " + user.getUserDateOfBirth());
                    if(list.get(j).contains("job")) list.set(j, "job: " + user.getUserJob());
                    if(list.get(j).contains("address")) list.set(j, "address: " + user.getUserAddress());

                    if(userAge >= 15) {
                        user.setCanTradeStocks("true");
                    } else  {
                        user.setCanTradeStocks("false");
                    }



                    if(list.get(j).contains("promo message")) {

                        if(user.getUserAddress().contains("15 Queen St E,Brampton,ON")) {
                            list.set(j, "promo message: " + "15% lower transaction fees for stocks");
                        } else if(user.getUserAddress().contains("25 King St W,Toronto,ON")) {
                            list.set(j, "promo message: " + "$1000 cash sign up bonus");
                        } else {
                            list.set(j, "promo message: " + user.getPromotionalMessage());
                        }


                    }

                    j++;

                }
            }
        }

    }



}
