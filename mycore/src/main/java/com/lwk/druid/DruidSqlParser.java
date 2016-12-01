package com.lwk.druid;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlUpdateStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by lwk on 2016/11/4.
 */
public class DruidSqlParser {

   private static Logger logger= LoggerFactory.getLogger(DruidSqlParser.class);

    public void testInsertParser() {
        String sql="insert into tablelwk('age','name','hihi') values('11','222','333'),('44','55','66')";
//        String sql="insert into tablelwk select * from tablelwk2";
        try {

            MySqlStatementParser parser = new MySqlStatementParser(sql);
            SQLStatement statement = parser.parseStatement();
            MySqlInsertStatement insertStatement = (MySqlInsertStatement) statement;
            MySqlUpdateStatement updateStatement = (MySqlUpdateStatement) statement;



            SQLExprTableSource tableSource = insertStatement.getTableSource();

            logger.info(tableSource.getExpr().toString());

            String tableName=insertStatement.getTableName().getSimpleName();
            logger.info("表名:" + tableName);

            List<SQLExpr> columns = insertStatement.getColumns();
            for (SQLExpr sqlExpr:columns){
                logger.info(sqlExpr.toString());
            }

            List<SQLInsertStatement.ValuesClause> valuesList = insertStatement.getValuesList();
            for (SQLInsertStatement.ValuesClause valuesClause:valuesList){
                for (SQLExpr sqlExpr:valuesClause.getValues()){
                    logger.info(sqlExpr.toString());
                }
                logger.info("=============");
            }

        }catch (Exception e){

        }
    }


    public static void main(String[] args) {
        new DruidSqlParser().testInsertParser();
    }
}
