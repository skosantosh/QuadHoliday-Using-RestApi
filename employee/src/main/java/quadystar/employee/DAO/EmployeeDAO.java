package quadystar.employee.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import quadystar.employee.Entity.EmployeeEntity;

@Repository
public interface EmployeeDAO extends JpaRepository<EmployeeEntity, Integer> {
	EmployeeEntity findByUniqueEmpID(String uniqueEmpID);

	// Or
	// Optional<EmployeeEntity>findByUniqueEmpID(String uniqueEmpID);

}
