package upao.edu.pe.OrderService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upao.edu.pe.OrderService.Entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
}
