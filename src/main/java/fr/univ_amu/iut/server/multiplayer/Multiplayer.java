package fr.univ_amu.iut.server.multiplayer;

import fr.univ_amu.iut.database.dao.DAOConfigSessionsJDBC;
import fr.univ_amu.iut.server.ClientCommunication;

import java.io.*;
import java.sql.SQLException;
import java.util.UUID;

public class Multiplayer {
    private ClientCommunication clientCommunication;

    public Multiplayer(ClientCommunication clientCommunication) {
        this.clientCommunication = clientCommunication;
    }

    /**
     * Create the multiplayer session
     * @throws IOException
     * @throws SQLException
     */
    public void createSession(String code) throws IOException, SQLException {
        ServerMultiplayer serverMultiplayer = new ServerMultiplayer(code, clientCommunication);
        serverMultiplayer.run();
    }

    /**
     * Supports the creation of a multiplayer session
     * @throws IOException
     */
    public void createMultiplayerSession() throws IOException, SQLException {
        String code = UUID.randomUUID().toString().substring(0,8);
        clientCommunication.sendMessageToClient("CODE_FLAG");
        clientCommunication.sendMessageToClient(code);
        createSession(code);
    }

    /**
     * Join a multiplayer session
     * @throws IOException
     */
    public void joinMultiplayerSession() throws IOException, SQLException {
        clientCommunication.sendMessageToClient("JOIN_SESSION");
        DAOConfigSessionsJDBC configSessionsJDBC = new DAOConfigSessionsJDBC();
        String message = clientCommunication.receiveMessageFromClient();
        if(configSessionsJDBC.isIn(message)) {  // Get the input code and ask if the code is in the database
            clientCommunication.sendMessageToClient("CODE_EXISTS_FLAG");
            clientCommunication.sendMessageToClient(Integer.toString(configSessionsJDBC.findPort(message)));    // Give the port
        } else {
            clientCommunication.sendMessageToClient("CODE_NOT_EXISTS_FLAG");
        }
    }
}