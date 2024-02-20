package courseonline4399.online.service;

import java.util.List;

public interface CRUD<E,K> {
    E create(E e);
    E update(E e);
    void delete(K k);
    List<E> findAll();
    E findById(K k);
}
