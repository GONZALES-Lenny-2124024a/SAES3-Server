package fr.univ_amu.iut.database.dao;

import fr.univ_amu.iut.Main;
import fr.univ_amu.iut.database.table.Qcm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOQcmJDBC implements DAOQcm {
    private PreparedStatement getACertainNumberOfQCMStatement;
    private static final Connection CONNECTION = Main.database.getConnection();

    public DAOQcmJDBC() throws SQLException {
        getACertainNumberOfQCMStatement = CONNECTION.prepareStatement("SELECT DISTINCT DESCRIPTION, QUESTION, TRUE_ANSWER, ANSWER_1, ANSWER_2, ANSWER_3 FROM HISTORY H, QCM Q WHERE H.ID = Q.ID and H.ID IN (select ID from HISTORY H WHERE H.MODULE = ? ORDER BY RANDOM() LIMIT ?) LIMIT ?;");
    }

    /**
     * Return a certain number of qcm on a certain module
     * @param numberOfTuples number of tuples that we want to get
     * @param module The questions are attached to a specific module
     * @return a list of questions and answers
     * @throws SQLException if the request fails
     */
    @Override
    public List<Qcm> getACertainNumberOfQCM(int numberOfTuples, String module) throws SQLException {
        getACertainNumberOfQCMStatement.setString(1, module);
        getACertainNumberOfQCMStatement.setInt(2, numberOfTuples);
        getACertainNumberOfQCMStatement.setInt(3, numberOfTuples);
        ResultSet result = getACertainNumberOfQCMStatement.executeQuery();
        List<Qcm> qcmList = new ArrayList<>();

        while(result.next()) {
            Qcm qcm = new Qcm();
            qcm.setDescription(result.getString(1));
            qcm.setQuestion(result.getString(2));
            qcm.setTrueAnswer(result.getInt(3));
            qcm.setAnswer1(result.getString(4));
            qcm.setAnswer2(result.getString(5));
            qcm.setAnswer3(result.getString(6));
            qcmList.add(qcm);
        }
        return qcmList;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }

    @Override
    public Object insert(Object obj) {
        return null;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }
}
