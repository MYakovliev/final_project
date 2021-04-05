package com.epam.web.dao.impl;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.LotDao;
import com.epam.web.entity.Lot;
import com.epam.web.pool.ConnectionPool;
import com.epam.web.pool.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LotDaoImpl implements LotDao {
    private static final Logger logger = LogManager.getLogger();
    private static final LotDao instance = new LotDaoImpl();
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String ANY_AMOUNT_SQL_CHARACTER = "%";
    private static final String ADD_LOT_PICTURE = "INSERT INTO lot_images(lot_id, image) VALUES(?,?)";
    private static final String ADD_NEW_LOT_STATEMENT =
            "INSERT INTO lots(name, description, start_time, end_time, bid, seller) VALUES (?,?,?,?,?,?)";
    private static final String FIND_LOT_BY_ID_STATEMENT =
            "SELECT idlots, name, description, bid, end_time, seller FROM lots WHERE idlots=?";
    private static final String FIND_LOT_PICTURES_BY_ID_STATEMENT =
            "SELECT image FROM lot_images WHERE lot_id=?";
    private static final String FIND_LOT_BY_NAME_STATEMENT =
            "SELECT idlots, name, description, bid, end_time, seller FROM lots WHERE name LIKE ? LIMIT ?, ?";
    private static final String FIND_WON_LOT_BY_BUYER_ID_STATEMENT =
            "SELECT DISTINCT idlots, name, description, lots.bid, end_time, seller " +
                    "FROM lots INNER JOIN bid_history ON bid_history.id_lot = lots.idlots " +
                    "WHERE id_buyer=? AND status=(SELECT idstatus FROM status WHERE status.status='WON') LIMIT ?, ?";
    private static final String FIND_LOT_BY_SELLER_ID_STATEMENT =
            "SELECT idlots, name, description, bid, end_time, seller FROM lots WHERE seller=? LIMIT ?, ?";
    private static final String FIND_ALL_LOT_STATEMENT =
            "SELECT idlots, name, description, bid, end_time, seller FROM lots LIMIT ?,?";
    private static final String FIND_ACTIVE_LOT_STATEMENT =
            "SELECT idlots, name, description, bid, end_time, seller FROM lots WHERE end_time < NOW() LIMIT ?,?";

    private LotDaoImpl() {
    }

    public static LotDao getInstance() {
        return instance;
    }


    @Override
    public Optional<Lot> createNewLot(String name, String description, BigDecimal startBid, Timestamp startTime, Timestamp finishTime, long sellerId, List<String> images) throws DaoException {
        Optional<Lot> lot = Optional.empty();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(ADD_NEW_LOT_STATEMENT, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, name);
            statement.setString(2, description);
            statement.setTimestamp(3, startTime);
            statement.setTimestamp(4, finishTime);
            statement.setBigDecimal(5, startBid);
            statement.setLong(6, sellerId);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            long id = 0;
            if (generatedKeys.next()) {
                id = generatedKeys.getLong(1);
            }
            statement.close();
            for (String image : images) {
                statement = connection.prepareStatement(ADD_LOT_PICTURE);
                statement.setLong(1, id);
                statement.setString(2, image);
                statement.executeUpdate();
                statement.close();
            }

            lot = Optional.of(new Lot(id, name, description, finishTime, startBid, sellerId, images));
            connection.commit();
        } catch (SQLException | ConnectionPoolException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    logger.error(ex);
                    throw new DaoException(ex);
                }
            }
            logger.error(e);
            throw new DaoException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DaoException(e);
                }
            }
        }
        return lot;
    }

    @Override
    public Optional<Lot> findLotById(long id) throws DaoException {
        Optional<Lot> lot = Optional.empty();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_LOT_BY_ID_STATEMENT)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                List<String> images = findLotImages(connection, id);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return lot;
    }

    @Override
    public List<Lot> findAll(int start, int finish) throws DaoException {
        List<Lot> lots = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_LOT_STATEMENT)) {
            statement.setInt(1, start);
            statement.setInt(2, finish);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                List<String> images = findLotImages(connection, id);
                Lot lot = createLot(resultSet, images);
                lots.add(lot);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return lots;
    }

    @Override
    public List<Lot> findLotByName(String name, int start, int finish) throws DaoException {
        List<Lot> lots = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_LOT_BY_NAME_STATEMENT)) {
            statement.setString(1, (ANY_AMOUNT_SQL_CHARACTER + name + ANY_AMOUNT_SQL_CHARACTER));
            statement.setInt(2, start);
            statement.setInt(3, finish);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                List<String> images = findLotImages(connection, id);
                Lot lot = createLot(resultSet, images);
                lots.add(lot);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return lots;
    }

    @Override
    public List<Lot> findWonLotByBuyerId(long buyerId, int start, int finish) throws DaoException {
        List<Lot> lots = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_WON_LOT_BY_BUYER_ID_STATEMENT)) {
            statement.setLong(1, buyerId);
            statement.setInt(2, start);
            statement.setInt(3, finish);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                List<String> images = findLotImages(connection, id);
                Lot lot = createLot(resultSet, images);
                lots.add(lot);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return lots;
    }

    @Override
    public List<Lot> findLotBySellerId(long sellerId, int start, int finish) throws DaoException {
        List<Lot> lots = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_LOT_BY_SELLER_ID_STATEMENT)) {
            statement.setLong(1, sellerId);
            statement.setInt(2, start);
            statement.setInt(3, finish);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                List<String> images = findLotImages(connection, id);
                Lot lot = createLot(resultSet, images);
                lots.add(lot);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return lots;
    }

    @Override
    public List<Lot> findActive(int start, int finish) throws DaoException {
        List<Lot> lots = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ACTIVE_LOT_STATEMENT)) {
            statement.setInt(1, start);
            statement.setInt(2, finish);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                List<String> images = findLotImages(connection, id);
                Lot lot = createLot(resultSet, images);
                lots.add(lot);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return lots;
    }

    private List<String> findLotImages(Connection connection, long id) throws SQLException {
        List<String> images = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_LOT_PICTURES_BY_ID_STATEMENT)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String imagePath = resultSet.getString(1);
                images.add(imagePath);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new SQLException(e);
        }
        return images;
    }

    private Lot createLot(ResultSet resultSet, List<String> images) throws SQLException {
        long id = resultSet.getLong(1);
        String name = resultSet.getString(2);
        String description = resultSet.getString(3);
        BigDecimal bid = resultSet.getBigDecimal(4);
        Timestamp time = resultSet.getTimestamp(5);
        long seller = resultSet.getLong(6);
        Lot lot = new Lot(id, name, description, time, bid, seller, images);
        return lot;
    }
}
