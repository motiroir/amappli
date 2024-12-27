package isika.p3.amappli.repo.amap;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import isika.p3.amappli.entities.payment.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {

}
