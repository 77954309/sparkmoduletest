package com.lm.beam.sql;
import com.lm.beam.sql.model.TestRow;
import com.lm.beam.sql.util.MyDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.beam.runners.direct.DirectRunner;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.coders.SerializableCoder;
import org.apache.beam.sdk.io.jdbc.JdbcIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.schemas.Schema;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @Author: limeng
 * @Date: 2019/7/11 13:45
 */
public class HiveCreateDb {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, SQLException, PropertyVetoException {
        PipelineOptions options = PipelineOptionsFactory.create();
        // 显式指定PipelineRunner：DirectRunner（Local模式）
        options.setRunner(DirectRunner.class);

        Pipeline pipeline = Pipeline.create(options);

//       DruidDataSource hiveDataSource = new MyDruidDataSource();
//        hiveDataSource.setLogWriter(new PrintWriter(System.out));
//        //基本属性 url、user、password
//        hiveDataSource.setDriverClassName(driverName);
//        hiveDataSource.setUrl("jdbc:hive2://192.168.20.117:10000/a_mart");
//        hiveDataSource.setUsername("hive");
//        hiveDataSource.setPassword("hive");
//        hiveDataSource.init();


//        hiveDataSource.setUrl("jdbc:mysql://192.168.20.115:3306/test");
//        hiveDataSource.setUsername("root");
//        hiveDataSource.setPassword("root");
//        hiveDataSource.init();

       // SerializableCoder.of(ds.getClass());
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass(driverName);
        cpds.setJdbcUrl("jdbc:hive2://192.168.20.117:10000/a_mart");
        cpds.setUser("hive");
        cpds.setPassword("hive");



        MyDataSource myDataSource = new MyDataSource();
        //HikariDataSource
        Schema type =
                Schema.builder().addStringField("name").build();

        System.out.println();
        pipeline.apply(JdbcIO.<TestRow>read().
                withDataSourceConfiguration(JdbcIO.DataSourceConfiguration
                        .create(cpds)

                ).withCoder(SerializableCoder.of(TestRow.class))
                .withQuery("select * from test")
                .withRowMapper(new JdbcIO.RowMapper<TestRow>() {
                    @Override
                    public TestRow mapRow(ResultSet resultSet) throws Exception {
                       System.out.println(resultSet.getString(1));
                        return null;
                    }
                })
        );




        pipeline.run().waitUntilFinish();
    }


}