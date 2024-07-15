package sistemaAbrigo;

import javax.persistence.EntityManager;
import java.util.List;

public class DoacaoService {

    public void criarRoupa(Roupa roupa) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(roupa);
        em.getTransaction().commit();
        em.close();
    }

    public void criarProdutoHigiene(ProdutoHigiene produtoHigiene) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(produtoHigiene);
        em.getTransaction().commit();
        em.close();
    }

    public void criarAlimento(Alimento alimento) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(alimento);
        em.getTransaction().commit();
        em.close();
    }

    public List<Roupa> listarRoupas() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Roupa> roupas = em.createQuery("from Roupa", Roupa.class).getResultList();
        em.close();
        return roupas;
    }

    public List<ProdutoHigiene> listarProdutosHigiene() {
        EntityManager em = JPAUtil.getEntityManager();
        List<ProdutoHigiene> produtosHigiene = em.createQuery("from ProdutoHigiene", ProdutoHigiene.class).getResultList();
        em.close();
        return produtosHigiene;
    }

    public List<Alimento> listarAlimentos() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Alimento> alimentos = em.createQuery("from Alimento", Alimento.class).getResultList();
        em.close();
        return alimentos;
    }

    public void atualizarRoupa(Roupa roupa) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(roupa);
        em.getTransaction().commit();
        em.close();
    }

    public void atualizarProdutoHigiene(ProdutoHigiene produtoHigiene) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(produtoHigiene);
        em.getTransaction().commit();
        em.close();
    }

    public void atualizarAlimento(Alimento alimento) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(alimento);
        em.getTransaction().commit();
        em.close();
    }

    public void deletarRoupa(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Roupa roupa = em.find(Roupa.class, id);
        if (roupa != null) {
            em.getTransaction().begin();
            em.remove(roupa);
            em.getTransaction().commit();
        }
        em.close();
    }

    public void deletarProdutoHigiene(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoHigiene produtoHigiene = em.find(ProdutoHigiene.class, id);
        if (produtoHigiene != null) {
            em.getTransaction().begin();
            em.remove(produtoHigiene);
            em.getTransaction().commit();
        }
        em.close();
    }

    public void deletarAlimento(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Alimento alimento = em.find(Alimento.class, id);
        if (alimento != null) {
            em.getTransaction().begin();
            em.remove(alimento);
            em.getTransaction().commit();
        }
        em.close();
    }
}
