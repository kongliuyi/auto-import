package net.riking.auto.commmon.job;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author kongLiuYi
 * @Date 2020/3/3 0003 13:45
 */
@Repository
public class EntityRepo {

    @Qualifier("primaryEntityManagerFactory")
    public EntityManagerFactory entityManagerFactory;

    public EntityRepo(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     *
     * @param list
     */
    public void batchInsert(List list) {
        int j = 0;
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        for (int i = 0; i < list.size(); i++) {
            em.persist(list.get(i));
            j++;
            if (j % 50 == 0 || j == list.size()) {
                try {
                    em.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    em.clear();
                }
            }
        }
        em.getTransaction().commit();
        em.close();//不关闭可能导致连接池异常
    }


    /**
     *
     * @param list
     */
    public void batchUpdate(List list) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        for (int i = 0; i < list.size(); i++) {
            em.merge(list.get(i));
            if (i % 100 == 0) {
                em.flush();
                em.clear();
            }
        }
        em.getTransaction().commit();
        em.close();
    }


    /**
     *
     * @param list
     */
    public void batchDelete(List list) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        for (int i = 0; i < list.size(); i++) {

            em.remove(list.get(i));
            if (i % 100 == 0) {
                em.flush();
                em.clear();
            }
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     *
     * @param clazz
     * @param dataDate
     */
    public void batchDelete(Class clazz, Date dataDate) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        em.createQuery("DELETE FROM  " + clazz.getName() + " where  dataDate = ?1 ")
                .setParameter(1, dataDate).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }


}
