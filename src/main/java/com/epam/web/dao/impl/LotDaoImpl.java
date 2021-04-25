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
    private static final LotDaoImpl instance = new LotDaoImpl();
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String ANY_AMOUNT_SQL_CHARACTER = "%";
    private static final String ADD_LOT_PICTURE = "INSERT INTO lot_images(lot_id, image) VALUES(?,?)";
    private static final String ADD_NEW_LOT_STATEMENT =
            "INSERT INTO lots(name, description, start_time, end_time, bid, seller) VALUES (?,?,?,?,?,?)";
    private static final String FIND_LOT_BY_ID_STATEMENT =
            "SELECT idlots, name, description, bid, start_time, end_time, seller FROM lots WHERE idlots=?";
    private static final String FIND_LOT_PICTURES_BY_ID_STATEMENT =
            "SELECT image FROM lot_images WHERE lot_id=?";
    private static final String FIND_LOT_BY_NAME_STATEMENT =
            "SELECT idlots, name, description, bid, start_time, end_time, seller FROM lots WHERE name LIKE ? LIMIT ?, ?";
    private static final String FIND_LOT_BY_BUYER_ID_STATEMENT =
            "SELECT DISTINCT idlots, name, description, lots.bid, start_time, end_time, seller " +
                    "FROM lots INNER JOIN bid_history ON bid_history.id_lot = lots.idlots " +
                    "WHERE id_buyer=? LIMIT ?, ?";
    private static final String FIND_LOT_BY_SELLER_ID_STATEMENT =
            "SELECT idlots, name, description, bid, start_time, end_time, seller FROM lots WHERE seller=? LIMIT ?, ?";
    private static final String FIND_ALL_LOT_STATEMENT =
            "SELECT idlots, name, description, bid, start_time, end_time, seller FROM lots LIMIT ?,?";
    private static final String FIND_ACTIVE_LOT_STATEMENT =
            "SELECT idlots, name, description, bid, start_time, end_time, seller FROM lots" +
                    " WHERE end_time > NOW() AND NOW() > start_time LIMIT ?,?";
    private static final String COMPARE_BID_STATEMENT =
            "SELECT MAX(bid), id_buyer FROM bid_history WHERE id_lot=?";
    private static final String CHECK_BUYER_STATEMENT =
            "SELECT id_buyer FROM bid_history WHERE id_lot=? " +
                    "AND idbid_history=(SELECT MAX(idbid_history) FROM bid_history WHERE id_lot=?)";
    private static final String IS_LOT_SUBMITTED_STATEMENT =
            "SELECT status=(SELECT idstatus FROM status WHERE status.status = 'WON') FROM bid_history " +
                    "WHERE idbid_history=(SELECT MAX(idbid_history) FROM bid_history WHERE id_lot=?)";


    private LotDaoImpl() {
    }

    public static LotDaoImpl getInstance() {
        return instance;
    }


    @Override
    public Optional<Lot> createNewLot(String name, String description, BigDecimal startBid, Timestamp startTime, Timestamp finishTime, long sellerId, List<String> images) throws DaoException {
        Optional<Lot> lot;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(ADD_NEW_LOT_STATEMENT, Statement.RETURN_GENERATED_KEYS);

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
            statement = connection.prepareStatement(ADD_LOT_PICTURE);
            for (String image : images) {
                statement.setLong(1, id);
                statement.setString(2, image);
                statement.executeUpdate();
            }
            lot = Optional.of(new Lot(id, name, description, startTime, finishTime, startBid, sellerId, images));
            connection.commit();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    logger.error(ex);
                    throw new DaoException(ex);
                }
            }
            throw new DaoException(e);
        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwable) {
                    logger.error(throwable);
                }
            }
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e);
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
                lot = Optional.of(createLot(connection, resultSet, images));

            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return lot;
    }

    @Override
    public List<Lot> findAll(int start, int amount) throws DaoException {
        List<Lot> lots = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_LOT_STATEMENT)) {
            statement.setInt(1, start);
            statement.setInt(2, amount);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                List<String> images = findLotImages(connection, id);
                Lot lot = createLot(connection, resultSet, images);
                lots.add(lot);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return lots;
    }

    @Override
    public List<Lot> findLotByName(String name, int start, int amount) throws DaoException {
        List<Lot> lots = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_LOT_BY_NAME_STATEMENT)) {
            statement.setString(1, (ANY_AMOUNT_SQL_CHARACTER + name + ANY_AMOUNT_SQL_CHARACTER));
            statement.setInt(2, start);
            statement.setInt(3, amount);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                List<String> images = findLotImages(connection, id);
                Lot lot = createLot(connection, resultSet, images);
                lots.add(lot);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return lots;
    }

    @Override
    public List<Lot> findLotByBuyerId(long buyerId, int start, int amount) throws DaoException {
        List<Lot> lots = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_LOT_BY_BUYER_ID_STATEMENT)) {
            statement.setLong(1, buyerId);
            statement.setInt(2, start);
            statement.setInt(3, amount);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                List<String> images = findLotImages(connection, id);
                Lot lot = createLot(connection, resultSet, images);
                lots.add(lot);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return lots;
    }

    @Override
    public List<Lot> findLotBySellerId(long sellerId, int start, int amount) throws DaoException {
        List<Lot> lots = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_LOT_BY_SELLER_ID_STATEMENT)) {
            statement.setLong(1, sellerId);
            statement.setInt(2, start);
            statement.setInt(3, amount);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                List<String> images = findLotImages(connection, id);
                Lot lot = createLot(connection, resultSet, images);
                lots.add(lot);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return lots;
    }

    @Override
    public List<Lot> findActive(int start, int amount) throws DaoException {
        List<Lot> lots = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ACTIVE_LOT_STATEMENT)) {
            statement.setInt(1, start);
            statement.setInt(2, amount);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                List<String> images = findLotImages(connection, id);
                Lot lot = createLot(connection, resultSet, images);
                lots.add(lot);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return lots;
    }

    @Override
    public boolean isLotSubmitted(long lotId) throws DaoException {
        boolean result = false;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(IS_LOT_SUBMITTED_STATEMENT)) {
            statement.setLong(1, lotId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getBoolean(1);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return result;
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
        }
        return images;
    }

    private BigDecimal compareBid(Connection connection, BigDecimal currentBid, long id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(COMPARE_BID_STATEMENT)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                BigDecimal bid = resultSet.getBigDecimal(1);
                if (bid != null) {
                    if (bid.compareTo(currentBid) > 0) {
                        currentBid = bid;
                    }
                }
            }
        }
        return currentBid;
    }

    private long checkBuyer(Connection connection, long lotId) throws SQLException{
        long buyerId = 0;
        try (PreparedStatement statement = connection.prepareStatement(CHECK_BUYER_STATEMENT)) {
            statement.setLong(1, lotId);
            statement.setLong(2, lotId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                buyerId = resultSet.getLong(1);
            }
        }
        return buyerId;
    }

    private Lot createLot(Connection connection, ResultSet resultSet, List<String> images) throws SQLException {
        long id = resultSet.getLong(1);
        String name = resultSet.getString(2);
        String description = resultSet.getString(3);
        BigDecimal bid = resultSet.getBigDecimal(4);
        bid = compareBid(connection, bid, id);
        Timestamp startTime = resultSet.getTimestamp(5);
        Timestamp finishTime = resultSet.getTimestamp(6);
        long seller = resultSet.getLong(7);
        Lot lot = new Lot(id, name, description, startTime, finishTime, bid, seller, images);
        long buyerId = checkBuyer(connection, id);
        lot.setBuyerId(buyerId);
        return lot;
    }
}
