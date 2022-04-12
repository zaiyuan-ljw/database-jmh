/**
 * Copyright 2021 Shulie Technology, Co.Ltd
 * Email: shulie@shulie.io
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pamirs.attach.plugin.common.datasource.normal;

import com.pamirs.pradar.pressurement.datasource.SqlParser;

import java.sql.*;

/**
 * <p>{@code } instances should NOT be constructed in
 * standard programming. </p>
 *
 * <p>This constructor is public to permit tools that require a JavaBean
 * instance to operate.</p>
 */
public class NormalStatment implements Statement {
    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    Statement statement;

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    Connection connection;


    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    private String dbConnectionKey;

    String dbType;

    private final String midType;

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    public NormalStatment(Statement statement, Connection connection, String dbConnectionKey, String dbType,
        String midType) {
        this.statement = statement;
        this.connection = connection;
        this.dbConnectionKey = dbConnectionKey;
        this.dbType = dbType;
        this.midType = midType;
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public boolean isWrapperFor(Class<?> arg0) throws SQLException {
        return statement.isWrapperFor(arg0);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public <T> T unwrap(Class<T> arg0) throws SQLException {
        return statement.unwrap(arg0);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public void addBatch(String arg0) throws SQLException {
        arg0 = SqlParser.replaceTable(arg0, this.dbConnectionKey, this.dbType, this.midType);
        statement.addBatch(arg0);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public void cancel() throws SQLException {
        statement.cancel();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public void clearBatch() throws SQLException {
        statement.clearBatch();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public void clearWarnings() throws SQLException {
        statement.clearWarnings();

    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public void close() throws SQLException {
        statement.close();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    public void closeOnCompletion() throws SQLException {
        try {
            statement.closeOnCompletion();
        } catch (AbstractMethodError e) {
        } catch (NoSuchMethodError e) {
        }
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public boolean execute(String arg0) throws SQLException {
        arg0 = SqlParser.replaceTable(arg0, this.dbConnectionKey, this.dbType, this.midType);
        return statement.execute(arg0);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public boolean execute(String arg0, int arg1) throws SQLException {
        arg0 = SqlParser.replaceTable(arg0, this.dbConnectionKey, this.dbType, this.midType);
        return statement.execute(arg0, arg1);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public boolean execute(String arg0, int[] arg1) throws SQLException {
        arg0 = SqlParser.replaceTable(arg0, this.dbConnectionKey, this.dbType, this.midType);
        return statement.execute(arg0, arg1);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public boolean execute(String arg0, String[] arg1) throws SQLException {
        arg0 = SqlParser.replaceTable(arg0, this.dbConnectionKey, this.dbType, this.midType);
        return statement.execute(arg0, arg1);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public int[] executeBatch() throws SQLException {
        return statement.executeBatch();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public ResultSet executeQuery(String arg0) throws SQLException {
        arg0 = SqlParser.replaceTable(arg0, this.dbConnectionKey, this.dbType, this.midType);
        return statement.executeQuery(arg0);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public int executeUpdate(String arg0) throws SQLException {
        arg0 = SqlParser.replaceTable(arg0, this.dbConnectionKey, this.dbType, this.midType);
        return statement.executeUpdate(arg0);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public int executeUpdate(String arg0, int arg1) throws SQLException {
        arg0 = SqlParser.replaceTable(arg0, this.dbConnectionKey, this.dbType, this.midType);
        return statement.executeUpdate(arg0, arg1);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public int executeUpdate(String arg0, int[] arg1) throws SQLException {
        arg0 = SqlParser.replaceTable(arg0, this.dbConnectionKey, this.dbType, this.midType);
        return statement.executeUpdate(arg0, arg1);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public int executeUpdate(String arg0, String[] arg1) throws SQLException {
        arg0 = SqlParser.replaceTable(arg0, this.dbConnectionKey, this.dbType, this.midType);
        return statement.executeUpdate(arg0, arg1);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public Connection getConnection() throws SQLException {
        return statement.getConnection();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public int getFetchDirection() throws SQLException {
        return statement.getFetchDirection();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public int getFetchSize() throws SQLException {
        return statement.getFetchSize();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return statement.getGeneratedKeys();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public int getMaxFieldSize() throws SQLException {
        return statement.getMaxFieldSize();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public int getMaxRows() throws SQLException {
        return statement.getMaxRows();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public boolean getMoreResults() throws SQLException {
        return statement.getMoreResults();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public boolean getMoreResults(int arg0) throws SQLException {
        return statement.getMoreResults(arg0);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public int getQueryTimeout() throws SQLException {
        return statement.getQueryTimeout();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public ResultSet getResultSet() throws SQLException {
        return statement.getResultSet();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public int getResultSetConcurrency() throws SQLException {
        return statement.getResultSetConcurrency();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public int getResultSetHoldability() throws SQLException {
        return statement.getResultSetHoldability();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public int getResultSetType() throws SQLException {
        return statement.getResultSetType();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public int getUpdateCount() throws SQLException {
        return statement.getUpdateCount();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public SQLWarning getWarnings() throws SQLException {
        return statement.getWarnings();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    public boolean isCloseOnCompletion() throws SQLException {
        try {
            return statement.isCloseOnCompletion();
        } catch (AbstractMethodError e) {
        } catch (NoSuchMethodError e) {
        }
        return false;
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public boolean isClosed() throws SQLException {
        return statement.isClosed();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public boolean isPoolable() throws SQLException {
        return statement.isPoolable();
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public void setCursorName(String arg0) throws SQLException {
        //arg0=ShemaReplacer.replaceTable(arg0,this.connection);
        statement.setCursorName(arg0);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public void setEscapeProcessing(boolean arg0) throws SQLException {
        statement.setEscapeProcessing(arg0);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public void setFetchDirection(int arg0) throws SQLException {
        statement.setFetchDirection(arg0);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public void setFetchSize(int arg0) throws SQLException {
        statement.setFetchSize(arg0);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public void setMaxFieldSize(int arg0) throws SQLException {
        statement.setMaxFieldSize(arg0);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public void setMaxRows(int arg0) throws SQLException {
        statement.setMaxRows(arg0);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public void setPoolable(boolean arg0) throws SQLException {
        statement.setPoolable(arg0);
    }

    /**
     * <p>{@code } instances should NOT be constructed in
     * standard programming. </p>
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    @Override
    public void setQueryTimeout(int arg0) throws SQLException {
        statement.setQueryTimeout(arg0);

    }

}
