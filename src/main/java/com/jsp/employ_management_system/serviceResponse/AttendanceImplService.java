package com.jsp.employ_management_system.serviceResponse;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.employ_management_system.dto.AttendanceDto;
import com.jsp.employ_management_system.entity.Attendance;
import com.jsp.employ_management_system.entity.Employee;
import com.jsp.employ_management_system.repository.AttendanceRepo;
import com.jsp.employ_management_system.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class AttendanceImplService {

	@Autowired
	private ModelMapper modelmapper1;
	@Autowired
    private AttendanceRepo attendanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

// Create attendance
       public Attendance createAttendance(int empId, Attendance attendance) {
         Optional<Employee> employee = employeeRepository.findById(empId);
         if (employee.isPresent()) {
          attendance.setEmployee(employee.get());
       return attendanceRepository.save(attendance);
    }
      throw new RuntimeException("Employee not found with id: " + empId);
}

// Get attendance by ID
//public Attendance getAttendanceById(Long id) {
//   return attendanceRepository.findById(id)
//  .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + id));
//}

// Get all attendances for an employee
//public List<Attendance> getAttendancesByEmployeeId(int empId) {
//     return  attendanceRepository.findByEmployeeId(empId);
//}

    // Update attendance
 @Transactional
    public Attendance updateAttendance(int empId, Attendance updatedAttendance) {
        // Fetch the employee to ensure they exist
        Optional<Employee> employeeOpt = employeeRepository.findById(empId);
        if (!employeeOpt.isPresent()) {
            throw new RuntimeException("Employee not found with ID: " + empId);
        }

        // Find existing attendance record
        Optional<Attendance> existingAttendanceOpt = attendanceRepository.findById(updatedAttendance.getId());
        if (!existingAttendanceOpt.isPresent()) {
            throw new RuntimeException("Attendance not found with ID: " + updatedAttendance.getId());
        }

        Attendance existingAttendance = existingAttendanceOpt.get();
        
        // Update fields
        existingAttendance.setAttendanceDate(updatedAttendance.getAttendanceDate());
        existingAttendance.setStatus(updatedAttendance.getStatus());
        existingAttendance.setCheckInTime(updatedAttendance.getCheckInTime());
        existingAttendance.setCheckOutTime(updatedAttendance.getCheckOutTime());

        // Optionally, update the employee reference if needed
        existingAttendance.setEmployee(employeeOpt.get());

        // Save updated attendance
        return attendanceRepository.save(existingAttendance);
    }
    // Delete attendance
//    public boolean deleteAttendance(Long id) {
//        if (attendanceRepository.existsById(id)) {
//            attendanceRepository.deleteById(id);
//        } else {
//            throw new RuntimeException("Attendance not found with id: " + id);
//        }
//		return false;
//    }

	
	public List<Attendance> getAllAttendances() {
		return attendanceRepository.findAll();
	}

	
	public Attendance addAttendance(Attendance attendance) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Attendance> getAttendancesByEmployeeId(int empId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
