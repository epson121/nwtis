/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.lurajcevi.zadaca_1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;

/**
 * 
 * @author Luka Rajcevic
 */
public class Zadaca_1 {
    
    /**
    * @author Luka Rajcevic
    * @param String array args, console parameters
    */
    public static void main(String[] args){
            
            Pattern p;
            Matcher m;
            boolean status;
            Konfiguracija config;
            String regexServer = "^-server -port ([8-9]\\d{3}) -konf ([^\\s]+\\.(?i)txt|xml)( +-load)? -s ([^\\s]+\\.[^\\s]+) *$";
            String regexUser = "^-user -ts (\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}) -port ([8-9]\\d{3}) -u ([a-zA-Z0-9_]+) -konf ([^\\s]+\\.(?i)txt|xml) *$";
            String regexShow = "^-show -s ([^\\s]+\\.[^\\s]+) *$";
            String regexAdmin = "^-admin -ts (\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}) -port ([8-9]\\d{3}) -u ([a-zA-Z0-9_]+) -p ([a-zA-Z0-9_]+) -konf ([^\\s]+\\.(?i)txt|xml) (-t (\\d\\d.\\d\\d.\\d\\d\\d\\d \\d\\d:\\d\\d:\\d\\d)|PAUSE|START|STOP|CLEAN) *$";
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < args.length; i++){
                    sb.append(args[i]).append(" ");
            }
            String strCommand = sb.toString().trim();
            
            switch (args[0]){
                case "-server":
                    p = Pattern.compile(regexServer);
                    m = p.matcher(strCommand);
                    status = m.matches();
                    if (status){
                        int port = Integer.parseInt(m.group(1));
                        String configFileName = m.group(2);
                        boolean load = m.group(3) != null;
                        String serializeFileName = m.group(4);
                        TimeServer sv = new TimeServer(   port
                                                              , configFileName
                                                              , load
                                                              , serializeFileName);
                        sv.startTimeServer();
                    }
                    break;
                case "-admin":
                    p = Pattern.compile(regexAdmin);
                    m = p.matcher(strCommand);
                    status = m.matches();
                    if (status){
                        int port = Integer.parseInt(m.group(2));
                        String configFileName = m.group(5);
                        String serverIP = m.group(1);
                        String user = m.group(3);
                        String password = m.group(4);
                        String adminCommand;
                        if (m.group(6).startsWith("-t")){
                            adminCommand = "SETTIME";
                        }
                        else{
                            adminCommand = m.group(6);
                        }
                        String time = m.group(7);
                        TimeAdministrator av = new TimeAdministrator (    port
                                                                        , configFileName
                                                                        , serverIP
                                                                        , user
                                                                        , password
                                                                        , adminCommand
                                                                        , time
                                                                        );
                        av.startAdministratorVremena();
                    }
                    break;
                case "-user":
                    p = Pattern.compile(regexUser);
                    m = p.matcher(strCommand);
                    status = m.matches();
                    System.out.println(status);
                    if (status){
                        int port = Integer.parseInt(m.group(2));
                        String configFileName = m.group(4);
                        String serverIP = m.group(1);
                        String user = m.group(3);
                        
                        TimeClient kv = new TimeClient(   port
                                                        , configFileName
                                                        , serverIP
                                                        , user);
                        kv.startTimeClient();
                    }
                    break;
                case "-show":
                    p = Pattern.compile(regexShow);
                    m = p.matcher(strCommand);
                    status = m.matches();
                    if (status){
                        RecordSerialization.deserializeFromFile(m.group(1));
                        for (Record rec : RecordSerialization.record){
                            System.out.println("User: " + rec.getUser() + "\nTime: " + rec.getTime() + 
                                       "\nCommand: " + rec.getCommand() + "\n------------------------------\n");
                        }
                    }
                    break;
                default:
                    System.out.println("Wrong input!");
            }
         
    }

}
