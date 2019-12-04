package services;

import constants.Constants;
import utils.CommandArguments;
import utils.Data;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

public class ConnectionService
{
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private boolean isCLI = false;
    //private Scanner socketIn;
    //private PrintWriter socketOut;
    private String ipAddress;

    public static Logger logger = Logger.getLogger(ConnectionService.class.getName());

    public ConnectionService(boolean isCLI)
    {
        this(null, null, isCLI);
    }

    public ConnectionService(String ipAddress, boolean isCLI)
    {
        this(ipAddress, null, isCLI);
    }

    public ConnectionService(String ipAddress, Socket aSocket, boolean isCLI)
    {
        this.ipAddress = ipAddress;
        this.isCLI = isCLI;

        if (aSocket == null)
        {
            try
            {
                socket = new Socket(ipAddress, 25);
            } catch (IOException e)
            {
                logger.info("unable to open the socket: " + e.getMessage());
            }
        }
        else
            socket = aSocket;
    }

    public void initChannels()
    {
        try
        {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e)
        {
            logger.info("Unalbe to create the object output stream: " + e.getMessage());
        }

        try
        {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e)
        {
            logger.info("Unable to create the object input stream: " + e.getMessage());
        }

        /*try
        {
            socketIn = new Scanner(socket.getInputStream());
        } catch (IOException e)
        {
            logger.info("Unable to create the scanner input stream: " + e.getMessage());
        }*/

        /*try
        {
            socketOut = new PrintWriter(socket.getOutputStream());
        } catch (IOException e)
        {
            logger.info("Unable to create the print writer output stream: " + e.getMessage());
        }*/

        //socketOut.println(Constants.CLICONNECT);
        //socketOut.flush();

        if (isCLI)
            sendCommand(Constants.CLICONNECT);
        else
            sendCommand(Constants.CLICONNECT);
    }

    public void sendCommmandArguments(CommandArguments commandArguments)
    {
        try
        {
            objectOutputStream.reset();
            objectOutputStream.writeObject(commandArguments);
            objectOutputStream.flush();
        } catch (IOException e)
        {
            logger.info("Unable to write the socket: " + e.getMessage());
        }

    }

    public void sendFile(File aFile)
    {
        FileInputStream fileInputStream = null;

        try
        {
            fileInputStream = new FileInputStream(aFile.getPath());
        } catch (FileNotFoundException e)
        {
            logger.info("File not found: " + e.getMessage());
        }

        byte[] buffer = new byte[0];

        try
        {
            buffer = new byte[fileInputStream.available()];
        } catch (IOException e)
        {
            logger.info("Unable to get the available bytes: " + e.getMessage());
        }


        try
        {
            fileInputStream.read(buffer);
        } catch (IOException e)
        {
            logger.info("Unable to read the buffer");
        }

        Data data = new Data();
        data.setName(aFile.getName());
        data.setFile(buffer);
        data.setFileSize((int)aFile.length());

        try
        {
            objectOutputStream.reset();
            objectOutputStream.writeObject(data);
            objectOutputStream.flush();
            fileInputStream.close();
        } catch (IOException e)
        {
            logger.info("I/O Exception: " + e.getMessage());
        }

    }

    public static boolean checkConnection()
    {
        URL url = null;
        URLConnection connection = null;
        boolean isConnected = false;

        try
        {
            url = new URL("https://www.google.it");
            connection = url.openConnection();
            connection.connect();
            isConnected = true;
        } catch (MalformedURLException e)
        {
            logger.info("Malformed URL: " + e.getMessage());
        } catch (IOException e)
        {
            logger.info("Unable to connect: " + e.getMessage());
            isConnected = false;
        }

        return isConnected;

    }

    public void closeConnection()
    {
        try
        {
            socket.close();
        } catch (IOException e)
        {
            logger.info("unable to close the socket: " + e.getMessage());
        }
    }

    private void writeCommand(String command)
    {
        try
        {
            objectOutputStream.writeUTF(command);
            objectOutputStream.flush();
        } catch (IOException e)
        {
            logger.info("Unable to send the command: " + e.getMessage());
        }
    }

    public void sendCommand(String command)
    {
        switch (command)
        {
            case Constants.SHOW_ADDRESS:
                writeCommand(command);
                break;
            case Constants.SHOW_PUBLIC_ADDRESS:
                writeCommand(command);
                break;
            case Constants.QUIT:
                writeCommand(command);
                break;
            case Constants.POPULATE:
                writeCommand(command);
                break;
            case Constants.UPDATE:
                writeCommand(command);
                break;
            case Constants.ECHO:
                writeCommand(command);
                break;
            case Constants.SEND_FILE:
                writeCommand(command);
                break;
            default:
                logger.info("Wrong command");
        }
    }

    /*** ACCESSORS ***/

    public Socket getSocket()
    {
        return socket;
    }

    public void setSocket(Socket socket)
    {
        this.socket = socket;
    }

    public ObjectInputStream getObjectInputStream()
    {
        return objectInputStream;
    }

    public void setObjectInputStream(ObjectInputStream objectInputStream)
    {
        this.objectInputStream = objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream()
    {
        return objectOutputStream;
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream)
    {
        this.objectOutputStream = objectOutputStream;
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    /*public Scanner getSocketIn()
    {
        return socketIn;
    }*/

   /* public void setSocketIn(Scanner socketIn)
    {
        this.socketIn = socketIn;
    }*/

    /*public PrintWriter getSocketOut()
    {
        return socketOut;
    }*/

    /*public void setSocketOut(PrintWriter socketOut)
    {
        this.socketOut = socketOut;
    }*/
}