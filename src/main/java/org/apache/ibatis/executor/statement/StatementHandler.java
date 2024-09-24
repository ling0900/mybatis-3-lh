/*
 *    Copyright 2009-2023 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.executor.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.ResultHandler;

/**
 * @author Clinton Begin
 */
public interface StatementHandler {

  /**
   * 准备一个 SQL 语句的执行对象。
   *
   * @param connection 数据库连接对象
   * @param transactionTimeout 事务超时时间（以秒为单位），如果为 null，则使用默认超时时间
   * @return SQL 语句的执行对象
   * @throws SQLException 如果在准备 SQL 语句的执行对象时发生错误
   */
  Statement prepare(Connection connection, Integer transactionTimeout) throws SQLException;

  /**
   * 为 SQL 语句的执行对象设置参数。
   *
   * @param statement SQL 语句的执行对象
   * @throws SQLException 如果在设置参数时发生错误
   */
  void parameterize(Statement statement) throws SQLException;

  /**
   * 将 SQL 语句的执行对象添加到批处理中。
   *
   * @param statement SQL 语句的执行对象
   * @throws SQLException 如果在添加到批处理中时发生错误
   */
  void batch(Statement statement) throws SQLException;

  /**
   * 执行 SQL 语句并返回受影响的行数。
   *
   * @param statement SQL 语句的执行对象
   * @return 受影响的行数
   * @throws SQLException 如果在执行 SQL 语句时发生错误
   */
  int update(Statement statement) throws SQLException;

  /**
   * 执行 SQL 语句并返回结果集。
   *
   * @param statement SQL 语句的执行对象
   * @param resultHandler 结果处理器
   * @param <E> 结果集的类型
   * @return 结果集
   * @throws SQLException 如果在执行 SQL 语句时发生错误
   */
  <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException;

  /**
   * 执行 SQL 语句并返回游标。
   *
   * @param statement SQL 语句的执行对象
   * @param <E> 游标的元素类型
   * @return 游标
   * @throws SQLException 如果在执行 SQL 语句时发生错误
   */
  <E> Cursor<E> queryCursor(Statement statement) throws SQLException;

  /**
   * 获取绑定的 SQL 语句。
   *
   * @return 绑定的 SQL 语句
   */
  BoundSql getBoundSql();

  /**
   * 获取参数处理器。
   *
   * @return 参数处理器
   */
  ParameterHandler getParameterHandler();

}
