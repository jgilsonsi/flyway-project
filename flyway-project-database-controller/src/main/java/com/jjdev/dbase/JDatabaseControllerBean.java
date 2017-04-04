package com.jjdev.dbase;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

/**
 *
 * @author jgilson
 */
@Singleton
@Startup
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class JDatabaseControllerBean {

    @PostConstruct
    void init() {
        System.out.println("------ DATABASE CONTROLLER MODULE ------");
        migrateDb();
    }

    private void migrateDb() {
        //*Para o correto funcionamento o banco deve estar criado. Flyway não cria o banco;    
        try {
            System.out.println("------> Starting Flyway Framework tasks");
            Flyway flyway = new Flyway();
            flyway.setDataSource("jdbc:postgresql://localhost:5432/bycar", "postgres", "master");
            flyway.setBaselineOnMigrate(true);
            flyway.migrate();
            System.out.println("------> Finishing Flyway Framework tasks");
        } catch (FlywayException ex) {
            System.out.println("------> ERROR - Flyway Framework \n" + ex);
        }
    }

    /*
        //Para usar o DS do container:
        @Resource(lookup = "java:/datasources/psql_bycar")
        private DataSource ds;
    
        private void migrateDb() {
            try {
                if (ds != null) {
                    System.out.println("------> Start Flyway Framework");
                    Flyway flyway = new Flyway();
                    flyway.setDataSource(ds);
                    flyway.setBaselineOnMigrate(true);
                    flyway.migrate();
                } else {
                    System.out.println("------> ERROR - Flyway Framework (Datasource not found)");
                }
            } catch (FlywayException ex) {
                System.out.println("------> ERROR - Flyway Framework \n" + ex);
            }
        }   
     */
}
