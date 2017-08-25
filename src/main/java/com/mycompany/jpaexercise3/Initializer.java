package com.mycompany.jpaexercise3;

import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Initializer
{

    public static void main(String[] args)
    {
        /////// Setup //////////////////
        HashMap puproperties = new HashMap();

        puproperties.put("javax.persistence.sql-load-script-source", "scripts/drop_all.sql");
        Persistence.generateSchema("jpaPU", puproperties);

        puproperties.remove("javax.persistence.sql-load-script-source");
        Persistence.generateSchema("jpaPU", puproperties);
        ////////////////////////////////

        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpaPU");
        EntityManager eManager = emFactory.createEntityManager();

        DiscountQuantity dq = new DiscountQuantity();
        DiscountFixed df = new DiscountFixed();

        eManager.getTransaction().begin();

        eManager.persist(dq);
        eManager.persist(df);

        eManager.getTransaction().commit();
        eManager.close();
    }
}
