package com.alphatica.genotick.population;

public class PopulationDAOFactory {
    public static PopulationDAO getDefaultDAO(String dao) {
        switch (dao) {
            case "RAM": return new PopulationDAORAM();
            case "FileSystem": return new PopulationDAOFileSystem();
            default: throw new DAOException("No such population DAO: " + dao);
        }
    }
}
