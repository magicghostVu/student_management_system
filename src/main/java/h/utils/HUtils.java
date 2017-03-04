package h.utils;



/**
 * Created by CPU10340_LOCAL on 2/8/2017.
 */

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.io.File;

public class HUtils {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    // Hibernate 5:
    private static SessionFactory buildSessionFactory() {
        try {
            // create ServiceRegistry object from config hibernate.cfg.xml
            File file = new File(System.getProperty("user.dir") + File.separator + "config/hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure(file).build();
            // create metadata from ServiceRegistry
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

            System.out.println("Build Session success");
            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex) {

            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // release all resource and connection pool
        getSessionFactory().close();
    }

}


