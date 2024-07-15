package sistemaAbrigo;

import javax.persistence.EntityManager;
import java.util.List;

public class AbrigoService {

    public void criarAbrigo(Abrigo abrigo) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(abrigo);
        em.getTransaction().commit();
        em.close();
    }

    public Abrigo buscarAbrigo(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Abrigo abrigo = em.find(Abrigo.class, id);
        em.close();
        return abrigo;
    }

    public List<Abrigo> listarAbrigos() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Abrigo> abrigos = em.createQuery("from Abrigo", Abrigo.class).getResultList();
        em.close();
        return abrigos;
    }

    public void atualizarAbrigo(Abrigo abrigo) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(abrigo);
        em.getTransaction().commit();
        em.close();
    }

    public void deletarAbrigo(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Abrigo abrigo = em.find(Abrigo.class, id);
        if (abrigo != null) {
            em.getTransaction().begin();
            em.remove(abrigo);
            em.getTransaction().commit();
        }
        em.close();
    }
}