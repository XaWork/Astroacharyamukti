package com.example.astroacharyamukti.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataItems {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableDetailList = new HashMap<String, List<String>>();
        List<String> fruits = new ArrayList<String>();
        fruits.add("Step 1- Go to the Home page and click on Menu on the Pro App and Click on ‘My Earnings’\n" +
                " Step 2- Once you click on My Earnings, the page will open and you will be able to find your earnings according to the consultations for a specific date and time you will have to click on the filter applied for the search process.\n");

        List<String> account = new ArrayList<String>();
        account.add("When you click on the ‘Account’ in the Menu, the Account page will open for your guidance.\n" +
                "            Account details are filled in on the website once an Astrologer has done the process of registering I the website/App.\n" +
                "            Only a registered Astrologer is allowed to view the details of the account created by the website / Application.\n" +
                "            Astrologers are not allowed to use the same identity information for registering on the other application/ Website.\n" +
                "            Strict action will be taken if found using the identity information for other sign inns as per the terms and conditions of the Website/an Application.");

        List<String> payments = new ArrayList<String>();
        payments.add("Once you open the page called ‘Payment’ you will automatically get access to see the details related to the payments.\n" +
                "            An Astrologer who is registered to the certain website/Application can access the payment related details provided by the service provider\n" +
                "            All payment-related details and your earnings will be shown and explained in the payment box.\n" +
                "            Taking an extra payment, Asking for unnecessary money and other restrictions are not allowed by an Astrologer according to the terms and conditions of the website/Application.\n");

        List<String> appointment = new ArrayList<String>();
        appointment.add("Click on the option called ‘Book an Appointment’ the page will open for the details related to Booking an appointment.\n" +
                "            An Astrologer who is registered is allowed to access the page ‘Book an Appointment’ booked by the client.\n" +
                "            Astrologers should check the appointment schedules and plan for a session accordingly on regular basis.\n" +
                "            In case the appointment is missed and it led to bad reviews for the website/Application, strict action will be taken according to the terms and conditions of the website/Application.");
        List<String> chat = new ArrayList<String>();
        chat.add("Once you click on the option that says ‘Chat with Astrologers’ it will take you to the page directly\n" +
                "            Chat with Astrologers platform is created to have a chat with the needy client through the website.\n" +
                "            An astrologer should have chat with the client for the duration has been given. Astrologers should not chat with the person on chat with the unmatched details of a client.\n" +
                "            Strict action will be taken in case found having an unnecessary conversation on chat, false language on chat, forming a personal relationship through chat, spreading unwanted information, etc.\n" +
                "            Access is provided to the registered Astrologers with correct information. Actions will be taken if found ruining the above restrictions.");
        List<String> call = new ArrayList<String>();
        call.add(">Click on the ‘Call Astrologers’ page will take you to the Call related information provided by the website or an Application.\n" +
                "            A registered Astrologer is allowed to access the calls from the clients.\n" +
                "            An Astrologer can finish the session within provided time duration by the service provider/website/Application\n" +
                "            An Astrologer is not allowed to have long conversations and out-of-the-box conversations with the client. Relevant information should be provided to the client by Astrologers.\n" +
                "            I found strict action will be taken according to the terms and conditions of the website/Application provided by the service provider.");
        List<String> quality = new ArrayList<String>();
        quality.add("Our Application/Website is the best quality in service providing. The quality of the website should be maintained by the Astrologers who have access to these Applications created by the service provider of the website.\n" +
                "            Any bad effects related to the quality of services will be considered inhalation of the terms and conditions of the website/Application.");

        expandableDetailList.put("Follow the steps given below and Find Answers to your Questions", fruits);
        expandableDetailList.put("Account-Related", account);
        expandableDetailList.put("Payment related", payments);
        expandableDetailList.put("Book an appointment", appointment);
        expandableDetailList.put("Chat Related", chat);
        expandableDetailList.put("Call related", call);
        expandableDetailList.put("Quality", quality);
        return expandableDetailList;
    }
}
